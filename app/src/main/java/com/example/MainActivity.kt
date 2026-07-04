package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.UserRepository
import com.example.ui.GameState
import com.example.ui.GameViewModel
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  private lateinit var db: AppDatabase
  private lateinit var viewModel: GameViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    db = Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java, "game-database"
    ).build()
    
    val repository = UserRepository(db.userDao())
    viewModel = GameViewModel(repository)

    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background,
        ) {
          val state by viewModel.uiState.collectAsState()
          Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when(state) {
              is GameState.Login -> LoginScreen(viewModel, modifier = Modifier.padding(innerPadding))
              is GameState.Playing -> PlayingScreen((state as GameState.Playing).user, modifier = Modifier.padding(innerPadding))
            }
          }
        }
      }
    }
  }
}

@Composable
fun LoginScreen(viewModel: GameViewModel, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text("Login / Register (Placeholder for simplicity)", color = MaterialTheme.colorScheme.onBackground)
  }
}

@Composable
fun PlayingScreen(user: com.example.data.User, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(
      text = "Welcome ${user.username}! Game Tutorial...",
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Light
      )
    )
  }
}
