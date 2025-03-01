package com.example.ahyaha.data.repository

import com.example.ahyaha.data.model.BloodType
import kotlinx.coroutines.flow.Flow

interface BloodTypeRepository {

    fun getAllBloodTypes(): Flow<List<BloodType>>

}
