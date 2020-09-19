package ma.test.mister.auto.data.apiservice

import android.content.Context
import io.reactivex.Observable
import ma.test.mister.auto.data.models.TaskItem
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.hasNetwork
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface Api {

    @GET("users")
    fun getUsers(): Observable<List<UserItem>>

    @GET("todos")
    fun getTasksByUserId(@Query("userId") userId: String): Observable<List<TaskItem>>

    companion object {
        operator fun invoke(): Api {

            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
        }


    }
}