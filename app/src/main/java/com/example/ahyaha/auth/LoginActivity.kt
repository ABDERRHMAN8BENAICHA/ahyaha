package com.example.ahyaha.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ahyaha.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AhyahaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = HospitalWhite
                ) {
                    val navController = rememberNavController()
                    LoginScreen(navController)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isCreatingAccount by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState.isSuccess) {
        if (loginState.isSuccess) {
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            navController.navigate("mainScreenLoggedIn") {
                popUpTo("mainScreen") { inclusive = true }
            }
        }
    }

    LaunchedEffect(loginState.error) {
        loginState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isCreatingAccount) "Create Account" else "Welcome Back",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp),
            color = SteelGray
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = TrustBlue) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email", tint = TrustBlue) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        OutlinedTextField(

            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = TrustBlue) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password", tint = TrustBlue) },
            trailingIcon = {
                TextButton(
                    onClick = { passwordVisible = !passwordVisible },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = if (passwordVisible) "Hide" else "Show",
                        style = MaterialTheme.typography.bodySmall,
                        color = PlasmaOrange
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        if (isCreatingAccount) {
            Button(
                onClick = {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                                isCreatingAccount = false
                            } else {
                                Toast.makeText(context, "Account creation failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BloodRed)
            ) {
                Text("Create Account")
            }
        } else {
            Button(
                onClick = { viewModel.login(email, password) },
                enabled = !loginState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BloodRed)
            ) {
                if (loginState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = HospitalWhite
                    )
                } else {
                    Text("Login")
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isCreatingAccount) {
                Text("Don't have an account?", color = SteelGray)
                TextButton(onClick = { isCreatingAccount = true }) {
                    Text("Create one", color = PlasmaOrange)
                }
            } else {
                Text("Already have an account?", color = SteelGray)
                TextButton(onClick = { isCreatingAccount = false }) {
                    Text("Login", color = PlasmaOrange)
                }
            }
        }
    }
}
