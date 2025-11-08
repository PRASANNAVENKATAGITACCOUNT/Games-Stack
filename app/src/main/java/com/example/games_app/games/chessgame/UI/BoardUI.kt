package com.example.games_app.games.chessgame.UI

import Board
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.games_app.games.chessgame.board.BoardXCoordinates
import com.example.games_app.games.chessgame.board.BoardYCoordinates
import rememberIsAvailableMove
import rememberPieceAt


@Composable
fun BoardUI(board: Board,
            modifier: Modifier = Modifier
) {
    Column (
        modifier= Modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .border(
                width=8.dp,
                color = Color.White
            )
            .padding(8.dp)
    ){

        BoardYCoordinates.forEach { y->
            Row (
                modifier= Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ){
                BoardXCoordinates.forEach { x->
                    val piece = board.rememberPieceAt(x,y)
                    val isAvailableMove =board.rememberIsAvailableMove(x,y)

                    BoardCell(x,y,piece,board,isAvailableMove, Modifier.weight(1f).fillMaxHeight())

                }

            }
        }

    }
}