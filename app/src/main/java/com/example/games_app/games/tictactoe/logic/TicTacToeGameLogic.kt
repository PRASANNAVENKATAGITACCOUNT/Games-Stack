package com.example.games_app.games.tictactoe.logic

import android.util.Log
import com.example.games_app.games.tictactoe.constants.GameStatus


class TicTacToeGameLogic {

    companion object{
        fun insertDataComputerGame(gameData:MutableList<MutableList<String>>, row:Int,col:Int) : MutableList<MutableList<String>> {
            var oRow =0;
            var oCol=0
            val newData: MutableList<MutableList<String>> = mutableListOf()
            try{
                gameData.forEach {
                    val rowData:MutableList<String> = mutableListOf()
                    it.forEach {  s->
                        rowData.add(s)
                    }
                    newData.add(rowData)
                }
                if(row<=2 && col<=2) newData[row][col] = "X"
                Log.d("Row and Col", "( $row, $col ) Data : $newData")

                val gameState= findWinnerInGame(gameData)
                if(gameState == GameStatus.X_WIN || gameState == GameStatus.O_WIN) return newData
                var setO:Boolean = false
                for (i in 0..2){
                    for (j in 0..2){
                        if(newData[i][j]==" "){
                            newData[i][j]="O"
                            setO=true
                            break
                        }
                    }
                    if (setO) break
                }


            }catch (e:Exception){
                Log.d("ErrorInLogic", ""+e+", "+e.message+", $row, $col, $oRow, $oCol ")
            }

            return newData
        }



        fun insertDataFriendsGame(gameData: List<MutableList<String>>, row:Int, col:Int, friendPressed:Boolean) :  MutableList<MutableList<String>> {
            val newData : MutableList<MutableList<String>> = mutableListOf()
            gameData.forEach {  listOfString->
                val eachRow : MutableList<String> = mutableListOf()
                listOfString.forEach { s->
                    eachRow.add(s);
                }
                newData.add(eachRow)
            }
            val gameStatus= findWinnerInGame(gameData)
            if(gameStatus== GameStatus.X_WIN || gameStatus== GameStatus.O_WIN) return newData

            newData[row][col] = if(friendPressed) "O" else "X"
            return  newData
        }


        // Find winner in game

        fun findWinnerInGame(gameData: List<MutableList<String>>) : GameStatus {

            if(isRowSame(gameData,0)) {
                val str = gameData[0][0]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }
            if(isRowSame(gameData,gameData.size-1)) {
                val str = gameData[gameData.size-1][gameData.size-1]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }
            if(isColumnSame(gameData,0)) {
                val str = gameData[0][0]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }
            if(isColumnSame(gameData,gameData.size-1)) {
                val str = gameData[gameData.size-1][gameData.size-1]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }

            if(isLeftDiagonalElementsSame(gameData)) {
                val str = gameData[0][0]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }

            if(isRightDiagonalElementsSame(gameData)) {
                val str = gameData[0][2]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }
            if(isMiddleColumnSame(gameData)) {
                val str = gameData[0][1]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }

            if(isMiddleRowSame(gameData)) {
                val str = gameData[1][1]
                if(str== "X") return GameStatus.X_WIN
                else if(str=="O") return GameStatus.O_WIN
            }


            for (i in 0..2){
                for (j in 0..2){
                    if(gameData[i][j]==" ") return GameStatus.GAME_RUNNING
                }
            }

            return GameStatus.GAME_DRAW

        }

        private fun  isRowSame(data:List<List<String>>, row:Int) : Boolean {
            for (i in 0..1){
                if(data[row][i] != data[row][i+1]) return false
            }
            return true
        }

        private fun  isColumnSame(data:List<List<String>>, column:Int) : Boolean {
            for (i in 0..1) {
                if(data[i][column] != data[i+1][column]) return false
            }
            return true
        }

        private fun  isLeftDiagonalElementsSame(data:List<List<String>>) : Boolean {
            for (i in 0..1){
                if(data[i][i] != data[i+1][i+1]){
                    return  false
                }
            }
            return true
        }

        private fun  isRightDiagonalElementsSame(data:List<List<String>>) : Boolean {
            for (i in 0..1){
                if(data[i][2-i] != data[i+1][2-(i+1)]) return false
            }
            return true
        }

        private fun  isMiddleColumnSame(data:List<List<String>>) : Boolean {
            for (i in 0..1){
                if(data[i][1] != data[i+1][1]) return false
            }
            return true
        }

        private fun  isMiddleRowSame(data:List<List<String>>) : Boolean {
            for (i in 0..1){
                if(data[1][i] != data[1][i+1]) return false
            }
            return true
        }

        fun resetGameData() : List<List<String>> {
            return listOf(
                mutableListOf(" "," "," "),
                mutableListOf(" "," "," "),
                mutableListOf(" "," "," ")
            )
        }

    }

}
