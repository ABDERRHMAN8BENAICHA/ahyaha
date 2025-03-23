package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.data.model.Donor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DonorViewModel : ViewModel() {
    private val _donors = MutableStateFlow<List<Donor>>(emptyList())
    val donors: StateFlow<List<Donor>> = _donors.asStateFlow()

    fun addDonor(donor: Donor) {
        viewModelScope.launch {
            _donors.value += donor
        }
    }

}
