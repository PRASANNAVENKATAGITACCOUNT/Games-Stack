package com.example.games_app.games.chessgame.board

import Board
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.games_app.games.chessgame.pieces.Piece


val BoardXCoordinates = List(8){
    'A'.code+it
}



val BoardYCoordinates = List(8){
    8-it
}

const val InitialEncodedPiecesPosition =
    "PW01PB06PW11PB16PW21PB26PW31PB36PW41PB46PW51PB56PW61PB66PW71PB76RW00RW70RB07RB77BW20BW50BB27BB57NW10NW60NB17NB67QW30QB37KW40KB47"



fun decodePieces(context: Context, encodedPieces :String):List<Piece>{

    val pieces = mutableListOf<Piece>()

    var index =0

    while (index < encodedPieces.length){
        val encodedPiece = encodedPieces.substring(index, index  + Piece.EncodePieceLength)
        pieces.add(Piece.decode(context,encodedPiece))
        index+= Piece.EncodePieceLength
    }

    return pieces
}
