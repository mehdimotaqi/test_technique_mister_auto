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

            //10 MB
            //val cacheSize = (5 * 1024 * 1024).toLong()
            //val myCache = Cache(c.cacheDir, cacheSize)

            /*
            .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (hasNetwork(c)!!)
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 120).build()
                    else
                        request.newBuilder().header(
                            "Cache-Control",
                            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                        ).build()

                    chain.proceed(request)
                }
             */


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