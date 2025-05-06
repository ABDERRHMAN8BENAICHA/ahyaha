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
import kotlinx.coroutines.flow.launchIn // Alternative to viewModelScope.launch
import kotlinx.coroutines.flow.onEach // More idiomatic for Flow collection
import kotlinx.coroutines.flow.onStart // To handle initial loading state
import kotlinx.coroutines.launch // For suspend function calls like delete
import javax.inject.Inject

// Your UIState is already well-defined for this
data class UIState(
    val isLoading: Boolean = false,
    val donors: List<Donor> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class DonorViewModel @Inject constructor(
    private val getDonorsUseCase: GetDonorsUseCase,
    // Inject other use cases as needed
    // private val deleteDonorUseCase: DeleteDonorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState(isLoading = true)) // Start with loading true
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        observeDonors() // Start observing when ViewModel is created
    }

    // Renamed from getDonors to reflect it observes a stream
    private fun observeDonors() {
        getDonorsUseCase() // Returns Flow<Result<List<Donor>>>
            .onStart {
                // You can keep the initial state loading or re-emit loading here if needed
                // _uiState.value = _uiState.value.copy(isLoading = true)
                // However, the initial state already has isLoading = true
            }
            .onEach { result -> // Process each emission from the Flow
                _uiState.value = when (result) {
                    is Result.Success -> {
                        UIState(isLoading = false, donors = result.data, error = null)
                    }
                    is Result.Error -> {
                        UIState(
                            isLoading = false,
                            donors = emptyList(), // Or keep existing donors: _uiState.value.donors
                            error = result.exception.message ?: "An unknown error occurred"
                        )
                    }
                    is Result.Loading -> { // Handle explicit Loading state if repository emits it
                        UIState(isLoading = true, donors = _uiState.value.donors, error = null)
                    }
                }
            }
            // No .catch needed here if errors are handled via Result.Error
            // .catch { exception -> ... }
            .launchIn(viewModelScope) // Collect the flow within the viewModelScope
    }

    // Example delete function using DeleteDonorUseCase
    /*
    fun deleteDonor(donorId: String) {
        viewModelScope.launch {
            // Optional: Set a specific deleting state if needed
            val result = deleteDonorUseCase(donorId)
            if (result is Result.Error) {
                // Update UIState with the delete error (maybe a temporary error message)
                 _uiState.value = _uiState.value.copy(error = "Delete failed: ${result.exception.message}")
            }
            // No explicit success handling needed for list update,
            // as the Flow from getDonorsUseCase will automatically emit the new list.
            // You might want to show a temporary success message (Snackbar).
        }
    }
    */

    // You might want a function to clear the error message from the UI state
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}