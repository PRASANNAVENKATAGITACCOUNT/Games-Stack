package com.example.games_app.games.chessgame.pieces

import androidx.compose.ui.unit.IntOffset


enum class StraightMovement {
    Up,Down,Left,Right
}

fun Piece.getStraightMoves(
    pieces : List<Piece>,
    movement:StraightMovement,
    maxMovements : Int=7,
) : Set<IntOffset>{
    return getMoves(
        pieces,
        getPosition={
            when(movement){
                StraightMovement.Up-> IntOffset(
                    x=position.x,
                    y=position.y+it
                )
                StraightMovement.Down-> IntOffset(
                    x=position.x,
                    y=position.y-it
                )
                StraightMovement.Left -> IntOffset(
                    x=position.x-it,
                    y=position.y
                )
                StraightMovement.Right -> IntOffset(
                    x=position.x+it,
                    y=position.y
                )

            }
        },
        maxMovements=maxMovements
    )

}

fun Piece.getMoves(
    pieces : List<Piece>,
    getPosition : (Int)-> IntOffset,
    maxMovements : Int=7
): Set<IntOffset>{
    val moves = mutableSetOf<IntOffset>()

    for(i in 1..maxMovements){

        val targetPosition = getPosition(i)

        val targetPiece = pieces.find { it.position == targetPosition }

        if(targetPiece != null){
            if(targetPiece.color != this.color){
                moves.add(targetPosition)
                break
            }else{
                moves.add(targetPosition)
            }
        }

    }
    
    return moves


}