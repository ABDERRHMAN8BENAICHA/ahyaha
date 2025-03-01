package com.example.ahyaha.data.repository

import com.example.ahyaha.data.model.BloodType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class BloodTypeRepositoryImpl : BloodTypeRepository {
    // TODO: Add methods to manage blood type data operations
    private val _bloodTypes = mutableListOf(
    BloodType("1", "A", "+"),
    BloodType("2", "B", "+"),
    BloodType("3", "AB", "+"),
    BloodType("4", "O", "+"),
    BloodType("5", "A", "-"),
    BloodType("6", "B", "-"),
    BloodType("7", "AB", "-"),
    BloodType("8", "O", "-")
    )

    private val _bloodTypeFlow = MutableSharedFlow<List<BloodType>>(replay = 1)

    init {
        _bloodTypeFlow.tryEmit(_bloodTypes.toList())
    }
    override fun getAllBloodTypes(): Flow<List<BloodType>> = _bloodTypeFlow
}
