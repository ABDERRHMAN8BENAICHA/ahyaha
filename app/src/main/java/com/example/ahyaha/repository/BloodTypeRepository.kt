package com.example.ahyaha.repository

import com.example.ahyaha.model.BloodType

object BloodTypeRepository {
    // TODO: Add methods to manage blood type data operations
    fun getAllBloodTypes(): List<BloodType> {
        // Simulating fetching data from a data source
        return listOf(
            BloodType("1", "A", "+"),
            BloodType("2", "B", "+"),
            BloodType("3", "AB", "+"),
            BloodType("4", "O", "+"),
            BloodType("5", "A", "-"),
            BloodType("6", "B", "-"),
            BloodType("7", "AB", "-"),
            BloodType("8", "O", "-")
        )
    }
}
