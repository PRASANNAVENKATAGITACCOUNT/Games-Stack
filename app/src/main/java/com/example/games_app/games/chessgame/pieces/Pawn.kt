package com.example.games_app.games.chessgame.pieces

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.ui.unit.IntOffset
import com.example.games_app.R
import com.example.games_app.games.chessgame.pieces.dsl.DiagonalMovement
import com.example.games_app.games.chessgame.pieces.dsl.StraightMovement
import com.example.games_app.games.chessgame.pieces.dsl.getPieceMoves

class Pawn(context: Context, override val color: Piece.Color, override var position: IntOffset) :Piece {

    override val type: Char
        get() = TYPE

    companion object{
        val TYPE='P'
    }

    override val drawable: Drawable?=
        if(color.isWhite) {
            context.getDrawable(R.drawable.pawn_white)
        } else {
            context.getDrawable(R.drawable.pawn_black)
        }

    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> {
        val isFirstMove = position.y==2 && color.isWhite || position.y==7 && color.isBlack


        return getPieceMoves(pieces) {

            straightMoves(
                movement = if(color.isWhite) StraightMovement.Up else StraightMovement.Down,
                maxMovements = if (isFirstMove) 2 else 1,
                canCapture = false
            )

            diagonalMoves(
                movement = if(color.isWhite) DiagonalMovement.UpRight else DiagonalMovement.DownRight,
                maxMovements = 1,
                captureOnly = true
            )

            diagonalMoves(
                movement = if(color.isWhite) DiagonalMovement.UpLeft else DiagonalMovement.DownLeft,
                maxMovements = 1,
                captureOnly = true
            )

        }

    }
}