package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.User
import com.example.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class GameState {
    object Login : GameState()
    data class Playing(val user: User, val tutorialStep: Int = 0) : GameState()
}

class GameViewModel(private val repository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<GameState>(GameState.Login)
    val uiState: StateFlow<GameState> = _uiState

    fun login(username: String) {
        viewModelScope.launch {
            val user = repository.getUser(username)
            if (user != null) {
                _uiState.value = GameState.Playing(user)
            }
        }
    }

    fun register(username: String) {
        viewModelScope.launch {
            val newUser = User(username = username, password = "password") // Simple
            repository.registerUser(newUser)
            _uiState.value = GameState.Playing(newUser)
        }
    }
}
