package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.data.repository.DonorRepositoryImpl
import com.example.ahyaha.domain.usecase.GetDonorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UIState(
    val isLoading: Boolean = false,
    val donors: List<Donor> = emptyList(),
    val error: String? = null
)


@HiltViewModel
class DonorViewModel @Inject constructor(private val getDonorsUseCase: GetDonorsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        getDonors()
    }

    fun getDonors() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)


            getDonorsUseCase().catch {
                _uiState.value = _uiState.value.copy(error = "Failed to fetch donors" ,isLoading = false , donors = emptyList())
            }.collect { donors ->
                _uiState.value = _uiState.value.copy(donors = donors, isLoading = false, error = null)
            }
        }
    }
}
