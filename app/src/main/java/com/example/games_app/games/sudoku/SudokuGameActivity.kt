package com.example.games_app.games.sudoku

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

class SudokuGameActivity : ComponentActivity() {
    lateinit var context :Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        enableEdgeToEdge()
        setContent {
            Surface {
                SudokuGameUI(context = context)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuGameUI(context: Context) {
    Scaffold (
        topBar = { TopAppBarSudoku()},
        bottomBar = {}
    ){
        Column(Modifier.padding(it)) {
            SudokuGame(context = context)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSudoku() {
    TopAppBar(
        title = { Text(text = "Sudoku ", fontSize = 25.sp) },
    )
}