package marvel.com.br.personagensmarvel.module

import marvel.com.br.personagensmarvel.BuildConfig
import marvel.com.br.personagensmarvel.model.Service
import marvel.com.br.personagensmarvel.viewmodel.HeroesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val myModule = module {
    single { createWebService<Service>()}
    viewModel{ HeroesViewModel(get()) }
}

inline fun <reified T> createWebService(): T{

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

    val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}
