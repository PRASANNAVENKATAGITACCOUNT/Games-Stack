package com.example.games_app.games.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.games_app.games.tictactoe.constants.TicTacToeNavConst
import com.example.games_app.ui.theme.DASHBOARD_BACKGROUND


@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClick:(moveToPath:String)->Unit) {
    Box (
        modifier.fillMaxSize()
            .background(color = DASHBOARD_BACKGROUND),
        contentAlignment = Alignment.Center,

    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val offset = Offset(0.5f,10.0f)
            val gradientColor = listOf(Cyan, Blue, Magenta)
            Row (
                Modifier.padding(top=55.dp)
            ){
                Text(
                    text = " TIC TAC  TOE", style = TextStyle(
                        fontSize = 50.sp,
                        shadow = Shadow(
                            Color.Red,
                            offset=offset,
                            blurRadius = 15f
                        ),
                        brush = Brush.linearGradient(
                            colors = gradientColor
                        )
                    ),

                    )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { onClick(TicTacToeNavConst.ComputerTicTacToePathConst.navPath)}
                ) {
                    Text(text = "Play With Computer")
                }

                Button(
                    onClick = { onClick(TicTacToeNavConst.FriendTicTacToePathConst.navPath)}
                ) {
                    Text(text = "Play With Friend")
                }
            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    Surface {
        HomeScreen {

        }
    }
}