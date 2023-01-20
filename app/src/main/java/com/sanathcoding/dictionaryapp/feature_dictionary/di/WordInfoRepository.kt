package com.sanathcoding.dictionaryapp.feature_dictionary.di

import com.sanathcoding.dictionaryapp.feature_dictionary.data.repository.WordInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WordInfoRepository {
    @Binds
    @Singleton
    abstract fun bindWordInfoRepository(
        wordInfoRepositoryImpl: WordInfoRepositoryImpl
    ): WordInfoRepository
}