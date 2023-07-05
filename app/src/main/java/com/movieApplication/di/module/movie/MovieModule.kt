package com.movieApplication.di.module.movie

import com.movieApplication.BuildConfig
import com.movieApplication.data.movie.datasource.MovieDataSource
import com.movieApplication.data.movie.repository.MovieRepositoryImpl
import com.movieApplication.di.module.qualifier.GeneralApiOkHttpClient
import com.movieApplication.domain.movie.repository.MovieRepository
import com.movieApplication.framework.movie.api.MovieApi
import com.movieApplication.framework.movie.datasource.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Singleton
    @Provides
    @GeneralApiOkHttpClient
    fun provideMovieApiOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectionSpec: ConnectionSpec
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectionSpecs(listOf(connectionSpec))

        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(
        converterFactory: MoshiConverterFactory,
        @GeneralApiOkHttpClient okHttpClient: OkHttpClient
    ): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_HOST_NAME)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(MovieApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface MovieBindsModule {

    @Binds
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindMovieDataSourceImpl(dataSource: MovieDataSourceImpl): MovieDataSource
}