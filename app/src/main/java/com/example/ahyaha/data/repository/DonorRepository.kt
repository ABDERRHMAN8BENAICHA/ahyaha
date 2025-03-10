package com.example.ahyaha.data.repository

import com.example.ahyaha.data.model.Donor
import kotlinx.coroutines.flow.Flow

interface DonorRepository {

    suspend fun addDonor(donor: Donor)

    fun getAllDonors(): Flow<List<Donor>>

}
