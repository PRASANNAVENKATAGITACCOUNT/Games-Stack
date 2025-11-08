package com.example.games_app.games.chessgame.pieces.dsl

import androidx.compose.ui.unit.IntOffset
import com.example.games_app.games.chessgame.pieces.Piece

enum class StraightMovement{
    Up,Down,Left,Right
}



fun Piece.getStraightMoves(
    pieces : List<Piece>,
    movement:StraightMovement,
    maxMovements:Int=7,
    canCapture:Boolean=true,
    captureOnly:Boolean = false
):Set<IntOffset>{

    return getMoves(
        pieces,
        getPosition = {
            when(movement){
                StraightMovement.Up->IntOffset(
                    x=position.x,
                    y=position.y+it
                )
                StraightMovement.Down->IntOffset(
                    x=position.x,
                    y=position.y-it
                )
                StraightMovement.Left->IntOffset(
                    x=position.x-it,
                    y=position.y
                )
                StraightMovement.Right->IntOffset(
                    x=position.x+it,
                    y=position.y
                )
            }
        },
        maxMovements=maxMovements,
        canCapture=canCapture,
        captureOnly= captureOnly
    )


}