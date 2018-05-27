package marvel.com.br.personagensmarvel.module

import android.content.Context
import dagger.Module
import dagger.Provides
import marvel.com.br.personagensmarvel.BuildConfig
import marvel.com.br.personagensmarvel.model.Service
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(val context: Context) {
    @Provides
    fun getAmbiente(): String = BuildConfig.SERVER_URL

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val ambiente = getAmbiente()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor {
                    val requestBuilder = it.request().newBuilder()
                    requestBuilder.header("Accept", "application/json")
                    it.proceed(requestBuilder.build())
                }
                .build()

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ambiente)
                .client(httpClient)
                .build()
    }

    @Provides
    fun getClient(): Service = getRetrofit().create(Service::class.java)

}