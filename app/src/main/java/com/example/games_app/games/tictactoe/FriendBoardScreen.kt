package com.example.games_app.games.tictactoe

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.games_app.games.tictactoe.logic.TicTacToeGameLogic
import com.example.games_app.games.tictactoe.constants.GameStatus
import com.example.games_app.games.tictactoe.datasource.TicTacToeGameData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendGameView(context: Context) {
    var friendPressed=false

    var gameData by remember {
        mutableStateOf(TicTacToeGameData.data.map { it.toMutableList() }.toMutableList())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Tic Tac Toe App")
                }
            )
        }
    ) { paddingValue ->
        Column(
            Modifier.padding(paddingValue)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var text by remember {
                mutableStateOf(GameStatus.GAME_START.toString())
            }
            GameBoardUI(boardData = gameData) { row, col ->
                gameData= TicTacToeGameLogic.insertDataFriendsGame(gameData,row,col,friendPressed)
                friendPressed = !friendPressed
                val gameState= TicTacToeGameLogic.findWinnerInGame(gameData)
                text = gameState.toString()
            }
            Text(
                text = text,
                style = TextStyle(color = Color.Black,
                    fontSize = 45.sp)
            )

            Button(modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),
                onClick = {
                    gameData = TicTacToeGameLogic.resetGameData().map { it.toMutableList() }.toMutableList()
                })
            {
                Text(text = "RESTART GAME" )
            }
        }
    }


}