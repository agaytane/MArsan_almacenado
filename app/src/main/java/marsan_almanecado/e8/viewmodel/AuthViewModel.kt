// AuthViewModel.kt
package marsan_almanecado.e8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUser = MutableStateFlow<String?>(null)
    val currentUser: StateFlow<String?> = _currentUser.asStateFlow()

    fun login(username: String, password: String): Boolean {
        // Lógica de autenticación simple (solo para ejemplo)
        val loginExitoso = username.isNotBlank() && password.isNotBlank()

        viewModelScope.launch {
            _isLoggedIn.value = loginExitoso
            if (loginExitoso) {
                _currentUser.value = username
            }
        }

        return loginExitoso
    }

    fun logout() {
        viewModelScope.launch {
            _isLoggedIn.value = false
            _currentUser.value = null
        }
    }
}