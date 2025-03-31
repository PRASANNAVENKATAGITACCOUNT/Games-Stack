package com.example.games_app.games.sudoku.logic



fun validateGameData(gameData: List<MutableList<Int>>) : Boolean{

    for (i in 0..8){
        for(j in 0..8){
            if(gameData[i][j].toString()=="" || !isValidInput(gameData,i, j,gameData[i][j])){
                return false
            }
        }
    }

    return true
}

// Filtering userSelection Numbers
fun getValidNumbersForCell(userRequiredNumbers : MutableList<Int>,  gameData: List<MutableList<Int>>, row: Int, col: Int): MutableList<Int> {
    return userRequiredNumbers.filter { isValidInput(gameData, row, col, it) }.toMutableList()
}

//AI generated
fun isValidInput(board: List<List<Int>>, row: Int, col: Int, value: Int): Boolean {
    // Check row
    if (board[row].contains(value)) return false
    // Check column
    if (board.any { it[col] == value }) return false
    // Check 3x3 grid
    val startRow = (row / 3) * 3
    val startCol = (col / 3) * 3
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (board[startRow + i][startCol + j] == value) return false
        }
    }
    return true
}

fun solveSudoku(board: MutableList<MutableList<Int>>): Boolean {
    for (row in 0 until 9) {
        for (col in 0 until 9) {
            if (board[row][col] == 0) {
                for (num in 1..9) {
                    if (isValidInput(board, row, col, num)) {
                        board[row][col] = num
                        if (solveSudoku(board)) return true
                        board[row][col] = 0
                    }
                }
                return false
            }
        }
    }
    return true
}
