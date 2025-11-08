package com.example.games_app.games.chessgame

import Board
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.games_app.R
import com.example.games_app.games.chessgame.UI.BoardUI
import rememberBoard

class ChessGameActivity : AppCompatActivity() {

    lateinit var composeView:ComposeView

    lateinit var board: Board

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chess_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        composeView = findViewById(R.id.composeView)

        composeView.setContent {
            board = rememberBoard()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                BoardUI(
                    board =board
                )
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        board?.let {

        }
    }



}