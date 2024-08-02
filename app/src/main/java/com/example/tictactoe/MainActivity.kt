package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.Screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                TicTacToe()
            }
        }
    }
}

@Composable
fun TicTacToe() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "GettingStarted" ) {
        composable("GettingStarted") {
            GettingStarted {
                navController.navigate("playerSelection")
            }
        }
        composable("playerSelection") {
            playerSelection(NavigateToOneNameScreen = { navController.navigate("EnterOnePlayerName") },
                NavigateToTwoNameScreen = {navController.navigate("EnterTwoPlayerName")})
        }
        composable("EnterTwoPlayerName") {
            EnterTwoPlayerName{playerOne, playerTwo ->
                navController.navigate("PlayerScreen/$playerOne/$playerTwo")
            }

        }
        composable("EnterOnePlayerName") {
            EnterOnePlayerName{ name ->
                navController.navigate("AiScreen/$name")
            }
        }
        composable("AiScreen/{name}",
            arguments = listOf(navArgument("name") {type = NavType.StringType})) {
            val name = it.arguments?.getString("name") ?: "no name"
            AiScreen(name = name)
        }

        composable("PlayerScreen/{playerOne}/{playerTwo}",
            arguments = listOf(navArgument("playerOne"){type = NavType.StringType},
                navArgument("playerTwo"){type = NavType.StringType})
        ) {
            val playerOne = it.arguments?.getString("playerOne") ?: "no name"
            val playerTwo = it.arguments?.getString("playerTwo") ?: "no name"

            PlayerScreen(playerOne = playerOne, playerTwo=playerTwo)
        }
    }
}

