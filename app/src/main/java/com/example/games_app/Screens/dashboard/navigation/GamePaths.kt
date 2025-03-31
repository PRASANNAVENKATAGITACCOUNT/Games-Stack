package com.example.games_app.Screens.dashboard.navigation

sealed class GamePaths( val route:String) {

    object LoginScreen : GamePaths("loginScreenRoute")

    object TicTacToePath : GamePaths("tictactoeRoute")

    object SudokuGame : GamePaths("sudokuGameRoute")

    object ChessGame : GamePaths("chessGameRoute")

}