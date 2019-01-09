/*
 * Created by Piotr Kostecki on 09.01.19 16:38
 * kontakt@piotrkostecki.pl
 *
 * Last modified 09.01.19 16:38
 */

package com.coinpaprika.apiclient.repository.people

import android.content.Context
import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PeopleApiTest {
    @Mock private lateinit var mockApi: PeopleApiContract
    @Mock private lateinit var mockPerson: PersonEntity
    @Mock private lateinit var mockContext: Context

    @Test
    fun `get person happy case`() {
        val person = mockPerson
        val response = Response.success(person)

        `when`(mockApi.getPerson(FAKE_PERSON_ID))
            .thenReturn(Observable.just(response))

        val client = PeopleApi(mockContext, mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .map { it.body() }
            .test()
            .assertResult(person)
            .assertComplete()
    }

    @Test
    fun `get person too many requests error`() {
        val response = Response.error<PersonEntity>(429,
            ResponseBody.create(MediaType.parse("application/json"), "\"error\":\"too many requests\")"))

        `when`(mockApi.getPerson(FAKE_PERSON_ID))
            .thenReturn(Observable.just(response))

        val client = PeopleApi(mockContext, mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get person server error`() {
        val response = Response.error<PersonEntity>(404,
            ResponseBody.create(MediaType.parse("text/html"), ""))

        `when`(mockApi.getPerson(FAKE_PERSON_ID))
            .thenReturn(Observable.just(response))

        val client = PeopleApi(mockContext, mockApi)
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

        val client = PeopleApi(mockContext, mockApi)
        client.getPerson(FAKE_PERSON_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_PERSON_ID = "matthew-roszak"
    }
}