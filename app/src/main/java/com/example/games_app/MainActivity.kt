package com.example.games_app


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.games_app.Screens.dashboard.DashboardActivity
import com.example.games_app.games.sudoku.SudokuGameUI
import com.example.games_app.ui.theme.GameAppTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context:Context=this;
        enableEdgeToEdge()
        setContent {
            GameAppTheme {
                Surface {
                    //SudokuGameUI(this)
                }
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameAppTheme {

    }
}


@Composable
fun SplashScreenGamesApp(context: Context) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        val intent=Intent(context,DashboardActivity::class.java)
        startActivity(context,intent,null)
    }
}