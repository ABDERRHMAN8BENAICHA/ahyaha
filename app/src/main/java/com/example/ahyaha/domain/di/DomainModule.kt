package com.example.ahyaha.domain.di

import com.example.ahyaha.data.repository.BloodTypeRepository
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.domain.usecase.AddDonorUseCase
import com.example.ahyaha.domain.usecase.GetBloodTypesUseCase
import com.example.ahyaha.domain.usecase.GetDonorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetDonorsUseCase(donorRepository: DonorRepository): GetDonorsUseCase {
        return GetDonorsUseCase(donorRepository)
    }



    @Provides
    @Singleton
    fun provideAddDonorUseCase(donorRepository: DonorRepository): AddDonorUseCase {
        return AddDonorUseCase(donorRepository)
    }

    @Provides
    @Singleton
    fun provideGetBloodTypesUseCase(bloodTypeRepository: BloodTypeRepository): GetBloodTypesUseCase {
        return GetBloodTypesUseCase(bloodTypeRepository)
    }
}