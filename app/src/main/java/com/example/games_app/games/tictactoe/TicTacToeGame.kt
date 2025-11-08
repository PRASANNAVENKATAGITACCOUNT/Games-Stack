package com.example.games_app.games.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.games_app.R
import com.example.games_app.games.sudoku.SudokuGameUI

class TicTacToeGame : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface {
                NavigationOfTicTacToeGame(this)
            }
        }

    }
}