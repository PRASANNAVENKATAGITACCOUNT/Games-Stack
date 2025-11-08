package com.example.games_app.games.chessgame.pieces

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.ui.unit.IntOffset
import com.example.games_app.games.chessgame.board.BoardXCoordinates
import com.example.games_app.games.chessgame.board.BoardYCoordinates



interface Piece {

    val color: Color

    var position: IntOffset

    val type :Char


    enum class Color {
        WHITE, BLACK;
        val isBlack get() = this == BLACK
        val isWhite get() = this == WHITE
    }

    val drawable: Drawable?

    fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset>


    companion object{

        fun decode(context: Context, encodedPiece: String) : Piece{
            val(type,color,x,y) = encodedPiece.toCharArray()

            val pieceColor = Color.entries.find { it.name.first() == color } ?: throw IllegalArgumentException("Invalid piece color")


            val position = IntOffset(
                x = x.digitToInt()+ BoardXCoordinates.min(),
                y= y.digitToInt()+ BoardYCoordinates.min()
            )
            return when(type){
                Pawn.TYPE-> Pawn(context,pieceColor,position)
                Knight.TYPE->Knight(context,pieceColor,position)
                King.TYPE-> King(context,pieceColor,position)
                Queen.TYPE-> Queen(context,pieceColor,position)
                Rook.TYPE->Rook(context,pieceColor,position)
                Bishop.TYPE-> Bishop(context,pieceColor,position)
                else ->
                    throw IllegalArgumentException("Invalid piece type")
            }

        }

        const val EncodePieceLength=4
    }

}