package target.gonzalez.lopez.ernesto.casestudy.di.module

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import target.gonzalez.lopez.ernesto.casestudy.di.networkutils.ResponseAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        const val CONNECT_TIMEOUT: Long = 60
        const val READ_TIMEOUT: Long = 60
        const val WRITE_TIMEOUT: Long = 60
        const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1"
    }
    @Provides
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            // set security headers, tokens
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addInterceptor(interceptor)
        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        return httpClient.build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(ResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient).build()
    }

}