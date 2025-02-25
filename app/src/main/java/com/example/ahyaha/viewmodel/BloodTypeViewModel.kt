package com.example.ahyaha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.model.BloodType
import com.example.ahyaha.repository.BloodTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BloodTypeState(
    val bloodTypes: List<BloodType> = emptyList()
)

class BloodTypeViewModel : ViewModel() {

    private val _bloodTypeState = MutableStateFlow(BloodTypeState())
    val bloodTypeState: StateFlow<BloodTypeState> = _bloodTypeState.asStateFlow()


    init {
        getBloodTypes()
    }

    private fun getBloodTypes() {
        viewModelScope.launch {
            val bloodTypes = BloodTypeRepository.getAllBloodTypes()
            _bloodTypeState.value = _bloodTypeState.value.copy(bloodTypes = bloodTypes)
        }
    }
}
