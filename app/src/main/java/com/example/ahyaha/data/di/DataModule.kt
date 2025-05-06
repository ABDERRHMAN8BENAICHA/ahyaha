package com.example.ahyaha.data.di

import com.example.ahyaha.data.repository.BloodTypeRepository
import com.example.ahyaha.data.repository.BloodTypeRepositoryImpl
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.DonorRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
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
    fun provideFirestore(): FirebaseFirestore {
        val firestore = Firebase.firestore
        // Enable offline persistence
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        firestore.firestoreSettings = settings
        return firestore
    }


    @Provides
    @Singleton
    fun provideDonorRepository(firestore: FirebaseFirestore): DonorRepository {
        return DonorRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideBloodTypeRepository(): BloodTypeRepository {
        return BloodTypeRepositoryImpl()
    }

}