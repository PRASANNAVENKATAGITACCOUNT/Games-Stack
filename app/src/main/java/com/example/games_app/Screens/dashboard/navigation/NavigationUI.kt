package com.example.games_app.Screens.dashboard.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.games_app.Screens.dashboard.DashboardActivity
import com.example.games_app.Screens.login.LoginScreen
import com.example.games_app.games.sudoku.SudokuGame
import com.example.games_app.games.tictactoe.NavigationOfTicTacToeGame

@Composable
fun NavigationGame(context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GamePaths.LoginScreen.route) {
        composable(GamePaths.LoginScreen.route){
            LoginScreen {email,password->
                navController.navigate(GamePaths.TicTacToePath.route)
            }
        }
        composable(GamePaths.TicTacToePath.route){
            NavigationOfTicTacToeGame(context = context)
        }

        composable(GamePaths.SudokuGame.route){
            SudokuGame(context)
        }

        composable(GamePaths.ChessGame.route){

        }

    }

}