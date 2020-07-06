package datas.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class APIGenerator(baseUrl: String) {
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val gson: Gson by lazy { configGson().create() }
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))

    open fun configGson(): GsonBuilder {
        return GsonBuilder()
    }

    open fun addRetrofitBuilder(): Retrofit.Builder {
        retrofitBuilder.client(addOkHttpBuilder().build())
        return retrofitBuilder
    }

    open fun addOkHttpBuilder(): OkHttpClient.Builder {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.readTimeout(configTimeout().toLong(), TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(configTimeout().toLong(), TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)
        getInterceptor().map { httpClientBuilder.addInterceptor(it) }
        return httpClientBuilder
    }

    open fun getInterceptor(): List<Interceptor> {
        return listOf()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = addRetrofitBuilder().build()
        return retrofit.create(serviceClass)
    }

    open fun configTimeout() = 30
}
