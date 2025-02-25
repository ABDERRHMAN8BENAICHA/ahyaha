package com.example.ahyaha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.model.Donor
import com.example.ahyaha.repository.DonorRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DonorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DonorUiState())
    val uiState: StateFlow<DonorUiState> = _uiState

    private val allDonors = DonorRepository.getAllDonors() // قائمة المتبرعين الأصلية

    init {
        _uiState.value = DonorUiState(donors = allDonors)
    }

    fun filterDonorsByBloodType(bloodType: String) {
        viewModelScope.launch {
            val filteredList = if (bloodType.isEmpty()) {
                allDonors
            } else {
                allDonors.filter { it.bloodGroup + it.Rh == bloodType }
            }
            _uiState.value = DonorUiState(donors = filteredList)
        }
    }

    fun filterDonorsByName(query: String) {
        viewModelScope.launch {
            val filteredList = if (query.isEmpty()) {
                allDonors
            } else {
                allDonors.filter { it.name.contains(query, ignoreCase = true) }
            }
            _uiState.value = DonorUiState(donors = filteredList)
        }
    }
}


data class DonorUiState(
    val donors: List<Donor> = emptyList(),
    val isLoading: Boolean = false,
    val error : String? = null
)
