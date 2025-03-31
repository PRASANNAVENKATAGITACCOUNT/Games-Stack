package com.example.games_app.games.chessgame.pieces

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.IntOffset
import androidx.core.content.ContextCompat
import com.example.games_app.R

class Pawn (
    context: Context,
    override val color: Piece.Color,
    override val position: IntOffset
):Piece {
    override val drawable: Drawable ? =
        if(color.isWhite) {
            context.getDrawable(R.drawable.pawn_white)
        }else {
            context.getDrawable(R.drawable.pawn_black)
        }

    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> {
        val isFirstMove = position.y==2 && color.isWhite || position.y==7 && color.isBlack
        return  getStraightMoves(
            pieces,
            movement = if(color.isWhite) StraightMovement.Up else StraightMovement.Down,
            maxMovements = if (isFirstMove) 2 else 1,
        )
    }


}