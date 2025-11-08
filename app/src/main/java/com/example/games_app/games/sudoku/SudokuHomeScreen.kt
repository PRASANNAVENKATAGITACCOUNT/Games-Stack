package com.example.games_app.games.sudoku

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.games_app.games.sudoku.datasource.generateSudokuPuzzle
import com.example.games_app.games.sudoku.logic.getValidNumbersForCell
import com.example.games_app.games.sudoku.logic.isValidInput
import com.example.games_app.games.sudoku.logic.solveSudoku
import com.example.games_app.games.sudoku.logic.validateGameData
import com.example.games_app.ui.theme.BabyBlue
import com.example.games_app.ui.theme.DASHBOARD_BACKGROUND
import com.example.games_app.ui.theme.Pink40


@Composable
fun SudokuGame(context: Context) {

    var gameData by remember { mutableStateOf(generateSudokuPuzzle()) }

    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }


    var userRequiredNumbers by remember {
        mutableStateOf((1..9).toMutableList())
    }

    var isRequiredNumbersVisible by remember { mutableStateOf(false) }

    val gradientColors = listOf(Color.Cyan, Color.Blue, Color.Magenta)


    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = DASHBOARD_BACKGROUND),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("SUDOKU",
            fontSize = 30.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
            )

        // Render Sudoku Grid
        for (i in 0..8) {
            Row {
                for (j in 0..8) {
                    val num = gameData[i][j]
                    val number = if (num == 0) "" else num.toString()


                    Column {

                        Row {
                            Cell(number = number, isSelected = selectedCell == Pair(i, j)) {
                                selectedCell = Pair(i, j) // Setting selected cell
                                userRequiredNumbers = (1..9).toMutableList()

                                userRequiredNumbers= getValidNumbersForCell(userRequiredNumbers,gameData,i,j)


                                if(userRequiredNumbers.isEmpty()) {
                                   if(validateGameData(gameData)){
                                       Toast.makeText(context," You Won", Toast.LENGTH_LONG).show()
                                   }else{
                                       Toast.makeText(context," Retry Game", Toast.LENGTH_LONG).show()
                                   }
                                }
                                isRequiredNumbersVisible = true // Show number selection
                            }
                            if((j+1)%3==0) {
                                Spacer(modifier = Modifier.width(4.dp).background(Color.LightGray))
                            }
                        }
                        if((i+1)%3==0) {
                            Spacer(modifier = Modifier.height(4.dp).background(Color.LightGray))
                        }
                    }

                }
            }
        }

        if (isRequiredNumbersVisible) {
            LazyRow(Modifier.padding(top = 55.dp)) {
                items(userRequiredNumbers) { number ->
                    UserSelectedNumber(userRequiredNumber = number) {

                        selectedCell?.let { (row, col) ->

                            gameData = gameData.toMutableList().apply {
                                this[row][col] = number // Update cell value
                            }
                        }
                        selectedCell = null // Deselect cell
                        isRequiredNumbersVisible = false // Hide number selection
                    }
                }
            }
        }
    }
}

@Composable
fun UserSelectedNumber(userRequiredNumber: Int, onClick: (Int) -> Unit) {

    ElevatedCard(
        Modifier
            .width(35.dp)
            .height(55.dp)
            .padding(1.dp)
            .background(Color.White)
            .clickable { onClick(userRequiredNumber) },
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userRequiredNumber.toString(),
                fontSize = 15.sp,
                modifier = Modifier.padding(2.dp),
            )
        }
    }
}


@Composable
fun Cell(number: String, isSelected: Boolean, onClick: () -> Unit) {
    ElevatedCard(
        Modifier
            .width(38.dp)
            .height(55.dp)
            .padding(1.dp)
            .background(Color.White)
            .clickable { onClick() },
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                fontSize = 15.sp,
                modifier = Modifier.padding(2.dp),
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun UIPreview() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        //UserSelectedNumber(userRequiredNumber = 5){}
        SudokuGame(context = LocalContext.current)
    }

}


