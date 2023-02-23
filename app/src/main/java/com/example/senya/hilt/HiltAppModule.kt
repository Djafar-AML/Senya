package com.example.senya.hilt

import com.example.senya.arch.AttractionsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltAppModule {
    @Provides
    @Singleton
    fun provideAttractionRepo() = AttractionsRepo()
}