package com.coinpaprika.apiclient.repository.people

import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import com.coinpaprika.apiclient.repository.notFoundError
import com.coinpaprika.apiclient.repository.tooManyRequestsError
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PeopleApiTest {
    @Mock
    private lateinit var mockApi: PeopleApiContract
    @Mock
    private lateinit var mockPerson: PersonEntity

    @Test
    fun `get person happy case`() {
        val person = mockPerson
        val response = Response.success(person)

        `when`(mockApi.getPerson(FAKE_PERSON_ID))
            .thenReturn(Observable.just(response))

        val client = PeopleApi(mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .map { it.body() }
            .test()
            .assertResult(person)
            .assertComplete()
    }

    @Test
    fun `get person too many requests error`() {
        val response = tooManyRequestsError<PersonEntity>()

        `when`(mockApi.getPerson(FAKE_PERSON_ID))
            .thenReturn(Observable.just(response))

        val client = PeopleApi(mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get person server error`() {
        val response = notFoundError<PersonEntity>()

        `when`(mockApi.getPerson(FAKE_PERSON_ID))
            .thenReturn(Observable.just(response))

        val client = PeopleApi(mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get person network connection error`() {
        given(mockApi.getPerson(FAKE_PERSON_ID))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = PeopleApi(mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_PERSON_ID = "matthew-roszak"
    }
}