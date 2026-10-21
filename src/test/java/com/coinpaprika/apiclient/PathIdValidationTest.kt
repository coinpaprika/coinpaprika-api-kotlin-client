package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.EventEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Test

private class DelegateCalled : RuntimeException("delegate called")

private class StubCoinsService : CoinsService {
    override suspend fun getCoin(id: String) = throw DelegateCalled()
    override suspend fun getCoins(additionalFields: String?) = throw DelegateCalled()
    override suspend fun getEvents(id: String) = throw DelegateCalled()
    override suspend fun getExchanges(id: String) = throw DelegateCalled()
    override suspend fun getMarkets(id: String, quotes: String) = throw DelegateCalled()
    override suspend fun getTweets(id: String) = throw DelegateCalled()
    override suspend fun addEvent(cryptoId: String, event: EventEntity) = throw DelegateCalled()
}

private class StubTagsService : TagsService {
    override suspend fun getTag(id: String) = throw DelegateCalled()
    override suspend fun getTags() = throw DelegateCalled()
}

private class StubTickersService : TickersService {
    override suspend fun getTicker(
        id: String,
        quotes: String,
        options: Map<String, String>
    ) = throw DelegateCalled()

    override suspend fun getTickers(
        quotes: String,
        page: Int?,
        options: Map<String, String>
    ) = throw DelegateCalled()
}

private class StubPeopleService : PeopleService {
    override suspend fun getPerson(id: String) = throw DelegateCalled()
    override suspend fun getTweets(id: String) = throw DelegateCalled()
}

// Ids that must be rejected before the delegate is reached. None of these
// names an internal API path; they are generic traversal and delimiter
// payloads only.
private val badIds = listOf(
    "",
    ".",
    "..",
    "a/b",
    "../key/info",
    "..%2fkey%2finfo",
    "a%2fb",
    "a?b",
    "a#b",
    "a%b",
    "a b",
    "a\tb"
)

class PathIdValidationTest {

    @Test
    fun `PathId rejects bad ids`() {
        for (id in badIds) {
            try {
                PathId.require(id)
                fail("expected IllegalArgumentException for id [$id]")
            } catch (e: IllegalArgumentException) {
                // expected
            }
        }
    }

    @Test
    fun `PathId accepts an ordinary id`() {
        PathId.require("btc-bitcoin")
    }

    @Test
    fun `CoinsService rejects bad ids before calling the delegate`() = runBlocking {
        val service: CoinsService = StubCoinsService().withIdValidation()
        for (id in badIds) {
            try {
                service.getCoin(id)
                fail("expected IllegalArgumentException for id [$id]")
            } catch (e: IllegalArgumentException) {
                // expected: rejected before the delegate was reached
            } catch (e: DelegateCalled) {
                fail("delegate was called for a bad id [$id]")
            }
        }
    }

    @Test
    fun `CoinsService forwards a valid id to the delegate`() = runBlocking {
        val service: CoinsService = StubCoinsService().withIdValidation()
        try {
            service.getCoin("btc-bitcoin")
            fail("expected the stub delegate to be reached")
        } catch (e: DelegateCalled) {
            // expected: a valid id reaches the delegate
        }
    }

    @Test
    fun `TagsService rejects bad ids before calling the delegate`() = runBlocking {
        val service: TagsService = StubTagsService().withIdValidation()
        for (id in badIds) {
            try {
                service.getTag(id)
                fail("expected IllegalArgumentException for id [$id]")
            } catch (e: IllegalArgumentException) {
                // expected
            } catch (e: DelegateCalled) {
                fail("delegate was called for a bad id [$id]")
            }
        }
    }

    @Test
    fun `TickersService rejects bad ids before calling the delegate`() = runBlocking {
        val service: TickersService = StubTickersService().withIdValidation()
        for (id in badIds) {
            try {
                service.getTicker(id, "USD")
                fail("expected IllegalArgumentException for id [$id]")
            } catch (e: IllegalArgumentException) {
                // expected
            } catch (e: DelegateCalled) {
                fail("delegate was called for a bad id [$id]")
            }
        }
    }

    @Test
    fun `PeopleService rejects bad ids before calling the delegate`() = runBlocking {
        val service: PeopleService = StubPeopleService().withIdValidation()
        for (id in badIds) {
            try {
                service.getPerson(id)
                fail("expected IllegalArgumentException for id [$id]")
            } catch (e: IllegalArgumentException) {
                // expected
            } catch (e: DelegateCalled) {
                fail("delegate was called for a bad id [$id]")
            }
        }
    }
}
