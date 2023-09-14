package com.lorrane.animelist.data.di

import android.content.Context
import com.lorrane.animelist.data.repository.AnimeRepository
import com.lorrane.animelist.data.api.AnimeService
import com.lorrane.animelist.util.ARQUIVO_PREFERENCIA
import com.lorrane.animelist.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideAnimeService(retrofit: Retrofit): AnimeService = retrofit.create(AnimeService::class.java)

    @Singleton
    @Provides
    fun provideAnimeRepository(animeService: AnimeService): AnimeRepository = AnimeRepository(animeService)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder().callTimeout(1, TimeUnit.MINUTES).build()

    @Singleton
    @Provides
    fun provideSharefPreferences(@ApplicationContext context: Context) = context.getSharedPreferences(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE)

}
