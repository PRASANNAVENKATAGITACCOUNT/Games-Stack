package com.example.games_app.games.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.games_app.ui.theme.GameAppTheme

@Composable
fun GameBoardUI(
    modifier: Modifier = Modifier,
    boardData:List<List<String>>,
    onClickCell:(row:Int,col:Int)->Unit) {
    
    Column (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .background(Color.White)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        boardData.forEachIndexed { row, listOfStrings:List<String> ->  
            Row {
                listOfStrings.forEachIndexed { col, cellData ->

                    Cell(value = cellData,row,col,onClickCell)
                }
            }
        }
    }

}

@Composable
fun Cell(value:String,
          row:Int, col:Int,onClickCell:(row:Int,col:Int)->Unit,) {
    Box(
        modifier = Modifier
            .height(90.dp)
            .width(90.dp)
            .padding(16.dp)
            .border(2.dp, Color.Black, RectangleShape)
        ,
        ){
        TextButton(onClick = {
            onClickCell(row,col)
        }) {
            Text(
                text = value, style = TextStyle(
                color = Color.Black,
                fontSize = 25.sp
            ) )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GameBoardPreview() {
    GameAppTheme {
        val data = listOf(
            mutableListOf("","X",""),
            mutableListOf("","",""),
            mutableListOf("","","X")
        )
        Column (Modifier.fillMaxSize()){
            GameBoardUI(boardData = data){ row,col->
                data[row][col]="X"
            }
        }

    }
}