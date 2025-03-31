package com.example.games_app.Screens.dashboard

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.games_app.games.chessgame.ChessGameActivity
import com.example.games_app.games.sudoku.SudokuGameActivity
import com.example.games_app.games.tictactoe.TicTacToeGame
import com.example.games_app.model.Game
import com.example.games_app.ui.theme.DASHBOARD_BACKGROUND


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardUI(
    context: Activity?
) {

    val listOfGames = mutableListOf<Game>().apply {
        add(Game(0L,"TIC TAC TOE"))
        add(Game(1L,"SUDOKU"))
        //add(Game(2L,"CHESS GAME"))
    }

    Scaffold (

    ) {
        Column (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
                .background(color= DASHBOARD_BACKGROUND),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val gradientColors = listOf(
                Color(0xFF64B5F6),
                Color(0xFF1976D2)
            )
            Text(
                "SELECT GAME",
                style = TextStyle(
                    fontSize = 35.sp,
                   // shadow = Shadow(color= Color.DarkGray, offset = Offset(5f,8.0f), blurRadius = 1f),
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                    )

            )
            Spacer(Modifier.height(8.dp))

            ElevatedCard(
                Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f)
                    .padding(8.dp),
            ) {

                Column (
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color(0xFF303030)),// slightly lighter bg
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    listOfGames.forEach { game->
                        EachGame(game){
                            var intent: Intent? = null
                            when(game.gameName){
                                "TIC TAC TOE"-> intent= Intent(context,TicTacToeGame::class.java)
                                "SUDOKU"-> intent= Intent(context, SudokuGameActivity::class.java)
                                "CHESS GAME"->intent = Intent(context,ChessGameActivity::class.java)
                            }
                            intent?.let{
                                context?.startActivity(it)
                            }
                        }
                    }
                }

            }
            
        }
    }

}


@Composable
fun EachGame(game: Game, onClickGame:(game:Game)->Unit) {

    Box {
        Column(
            Modifier
                .padding(5.dp)

        ) {
            Button(
                onClick = {
                onClickGame(game)
            }) {
                Text(
                    game.gameName,
                    style = TextStyle(fontSize = 15.sp, fontStyle = FontStyle.Normal),
                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDashboard() {
    TopAppBar(title = { Text(text = "Dashboard") })
}

@Preview(showBackground = true)
@Composable
fun DashboardUIPreview() {
    DashboardUI(null)
}