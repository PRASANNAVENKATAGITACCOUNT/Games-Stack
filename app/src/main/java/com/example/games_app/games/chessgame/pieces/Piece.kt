package com.example.games_app.games.chessgame.pieces

import android.graphics.drawable.Drawable
import androidx.compose.ui.unit.IntOffset


interface Piece {

    val color : Color

    enum class Color{
        WHITE,BLACK;

        val isWhite  get() = this == WHITE
        val isBlack get() = this == BLACK
    }

    val drawable : Drawable?

    val position : IntOffset

    fun getAvailableMoves(pieces:List<Piece>) : Set<IntOffset>
    

}