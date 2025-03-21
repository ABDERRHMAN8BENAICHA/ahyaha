package com.example.ahyaha.data.di

import com.example.ahyaha.data.repository.BloodTypeRepository
import com.example.ahyaha.data.repository.BloodTypeRepositoryImpl
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.DonorRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideDonorRepository(): DonorRepository {
        return DonorRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideBloodTypeRepository(): BloodTypeRepository {
        return BloodTypeRepositoryImpl()
    }

}