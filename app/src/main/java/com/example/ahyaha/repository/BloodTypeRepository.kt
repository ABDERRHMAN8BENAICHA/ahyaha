package com.example.ahyaha.repository
import android.graphics.Picture
import com.example.ahyaha.R
import com.example.ahyaha.model.BloodType

object BloodTypeRepository {
    // TODO: Add methods to manage blood type data operations
    fun getAllBloodTypes(): List<BloodType> {
        // Simulating fetching data from a data source
        return listOf(
            BloodType("1", "O", "+" , Picture= R.drawable.img),
            BloodType("2", "B", "+" , Picture= R.drawable.img_1),
            BloodType("3", "AB", "+", Picture= R.drawable.img_2),
            BloodType("4", "A", "+" , Picture= R.drawable.img_3),
            BloodType("5", "O", "-" , Picture= R.drawable.img_4),
            BloodType("6", "A", "-" , Picture= R.drawable.img_5),
            BloodType("7", "AB", "-" , Picture= R.drawable.img_6),
            BloodType("8", "B", "-" , Picture= R.drawable.img_7 )
        )
    }
}
