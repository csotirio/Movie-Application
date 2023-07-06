package com.movieApplication.framework.movie.api

import com.movieApplication.data.movie.catalog.model.RemoteMoviesCatalogResponse
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsCastItemsResponse
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): RemoteMoviesCatalogResponse

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): RemoteMoviesCatalogResponse

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): RemoteMoviesCatalogResponse

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): RemoteMoviesCatalogResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(@Path(value = "movieId") movieId: String, @Query("api_key") apiKey: String): RemoteMovieDetailsResponse

    @GET("3/movie/{movieId}/credits")
    suspend fun getMovieDetailsCast(@Path(value = "movieId") movieId: String, @Query("api_key") apiKey: String): RemoteMovieDetailsCastItemsResponse
}