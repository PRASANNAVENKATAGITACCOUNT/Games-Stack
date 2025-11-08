package com.example.games_app.games.chessgame.pieces

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.ui.unit.IntOffset
import com.example.games_app.R
import com.example.games_app.games.chessgame.pieces.dsl.getPieceMoves

class Knight(context: Context, override val color: Piece.Color, override var position: IntOffset) : Piece {

    override val type: Char= TYPE
    companion object{
        val TYPE='N'
    }

    override val drawable: Drawable?= if(color.isWhite){
        context.getDrawable(R.drawable.knight_white)
    }else{
        context.getDrawable(R.drawable.knight_black)
    }

    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> {
        return getPieceMoves(pieces){
            getLMoves()
        }
    }


}