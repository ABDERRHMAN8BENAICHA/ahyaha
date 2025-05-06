package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.data.repository.Result
import com.example.ahyaha.domain.usecase.GetDonorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

data class UIState(
    val isLoading: Boolean = false,
    val donors: List<Donor> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class DonorViewModel @Inject constructor(
    private val getDonorsUseCase: GetDonorsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState(isLoading = true))
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        observeDonors()
    }


    private fun observeDonors() {
        getDonorsUseCase() //
            .onStart {
            }
            .onEach { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        UIState(isLoading = false, donors = result.data, error = null)
                    }
                    is Result.Error -> {
                        UIState(
                            isLoading = false,
                            donors = emptyList(),
                            error = result.exception.message ?: "An unknown error occurred"
                        )
                    }
                    is Result.Loading -> {
                        UIState(isLoading = true, donors = _uiState.value.donors, error = null)
                    }
                }
            }

            .launchIn(viewModelScope)
    }




    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}