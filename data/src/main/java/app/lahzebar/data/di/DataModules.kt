package app.lahzebar.data.di

import android.content.Context
import app.lahzebar.data.AuthDataSourceImpl
import app.lahzebar.data.LocalDataSourceImpl
import app.lahzebar.data.RemoteDataSourceImpl
import app.lahzebar.data.remote.ApiInfo
import app.lahzebar.data.remote.AuthRestApi
import app.lahzebar.data.remote.RemoteRestApi
import app.lahzebar.domain.api.AuthDataSource
import app.lahzebar.domain.api.LocalDataSource
import app.lahzebar.domain.api.RemoteDataSource
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class DataModule {
    @Provides
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module
internal class RemoteModule {

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    @Anonymous
    fun anonymousOkHttpClient(
        context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()

    @Provides
    @Api
    fun apiOkHttpClient(
        @Anonymous okHttpClient: OkHttpClient,
        // refreshAuthenticator: RefreshAuthenticator,
        // authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            // .authenticator(refreshAuthenticator)
            // .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Anonymous
    fun anonymousRetrofit(
        @Anonymous okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiInfo.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Api
    fun apiRetrofit(
        @Api okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiInfo.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun remoteRestApi(
        @Api retrofit: Retrofit
    ): RemoteRestApi {
        return retrofit.create(RemoteRestApi::class.java)
    }

    @Provides
    @Singleton
    fun authRestApi(
        @Anonymous retrofit: Retrofit
    ): AuthRestApi {
        return retrofit.create(AuthRestApi::class.java)
    }
}

@Module
internal interface NetworkModule {
    @Binds
    @Singleton
    fun remoteDataSource(remoteDataSourceIml: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    fun authDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource


}
