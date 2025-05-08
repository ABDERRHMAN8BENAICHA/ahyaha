package com.example.ahyaha.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)

            if (email.isBlank() || password.isBlank()) {
                _loginState.value = LoginState(error = "Email and password cannot be empty")
                return@launch
            }

            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                _loginState.value = LoginState(isSuccess = true)
            } catch (e: Exception) {
                _loginState.value = LoginState(error = e.message ?: "Login failed")
            }
        }
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
