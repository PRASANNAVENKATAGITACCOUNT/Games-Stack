package com.example.games_app.games.chessgame.UI

import Board
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.games_app.R
import com.example.games_app.games.chessgame.board.BoardXCoordinates
import com.example.games_app.games.chessgame.pieces.Piece



@Composable
fun BoardCell(
    x:Int,
    y:Int,
    piece: Piece?,
    board: Board,
    isAvailableMove : Boolean,
    modifier : Modifier = Modifier
) {
    val context = LocalContext.current

    val backgroundColor = when{
        piece!=null && piece == board.selectedPiece-> ActiveColor

        (x+y)%2==0 -> DarkColor

        else-> LightColor

    }

    val textcolor = when{
        piece!=null && piece == board.selectedPiece->
            Color.White

        (x+y)%2==0 -> LightColor

        else-> DarkColor

    }

    Box(
        modifier = modifier.fillMaxSize()
            .background(backgroundColor)
    ) {

        if(x == BoardXCoordinates.first()){
            Text(
                text = y.toString(),
                color = textcolor,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.TopStart)
                    .padding(3.dp)

            )
        }
        if(y == 1){
            Text(
                text = x.toChar().toString(),
                color = textcolor,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.BottomEnd)
                    .padding(3.dp)

            )
        }

        piece?.let {
            val chessDrawable: Drawable = remember {
                it.drawable ?: ContextCompat.getDrawable(context, android.R.drawable.ic_menu_help)!!
            }
            Image(
                painter =  BitmapPainter(chessDrawable.toBitmap().asImageBitmap()),
                contentDescription = null,
                modifier= Modifier
                    .clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ){
                        board.selectPiece(it)
                    }
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }

        if(isAvailableMove){
            Box(
                modifier= Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ){
                        board.moveSelectedPiece(x=x,y= y)
                    }
                    .drawBehind {
                        drawCircle(
                            color= ActiveColor,
                            radius = size.width/ 6f,
                            center = center
                        )
                    }
            ){}
        }

    }
}