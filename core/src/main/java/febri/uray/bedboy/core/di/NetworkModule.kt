package febri.uray.bedboy.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import febri.uray.bedboy.core.BuildConfig
import febri.uray.bedboy.core.data.source.remote.network.ApiServicePostpaid
import febri.uray.bedboy.core.data.source.remote.network.ApiServicePrepaid
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
        ).connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()
    }

    @Provides
    fun provideApiServicePrepaid(client: OkHttpClient): ApiServicePrepaid {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASEURL_PREPAID)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        return retrofit.create(ApiServicePrepaid::class.java)
    }

    @Provides
    fun provideApiServicePostpaid(client: OkHttpClient): ApiServicePostpaid {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASEURL_POSTPAID)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        return retrofit.create(ApiServicePostpaid::class.java)
    }
}