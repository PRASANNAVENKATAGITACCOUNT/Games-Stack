package com.example.games_app.games.sudoku.datasource

fun generateSudokuPuzzle(): List<MutableList<Int>> {
    return listOf(
        mutableListOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
        mutableListOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
        mutableListOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
        mutableListOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
        mutableListOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
        mutableListOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
        mutableListOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
        mutableListOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
        mutableListOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )
}