package com.example.games_app.games.tictactoe.constants

sealed class TicTacToeNavConst(val navPath:String)  {
    object HomeScreenPath : TicTacToeNavConst("HomeScreenPath")

    object ComputerTicTacToePathConst: TicTacToeNavConst("ComputerScreenPath")

    object FriendTicTacToePathConst : TicTacToeNavConst("FriendScreenPath")

}