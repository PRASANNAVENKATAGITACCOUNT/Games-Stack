import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import com.example.games_app.games.chessgame.board.InitialEncodedPiecesPosition
import com.example.games_app.games.chessgame.board.decodePieces
import com.example.games_app.games.chessgame.pieces.Piece


@Composable
fun rememberBoard() : Board {
    val context=LocalContext.current
    return remember {
        Board(context)
    }
}




class Board(context: Context) {

    private val _pieces = mutableStateListOf<Piece>()
    val pieces get() = _pieces.toList()



    init {

        _pieces.addAll(
            decodePieces(
                context,
                InitialEncodedPiecesPosition
            )
        )

    }


    var selectedPiece by mutableStateOf<Piece?>(null)
        private set


    var selectedPieceMoves by mutableStateOf(emptyList<IntOffset>())
        private set

    var removedPiecesInBoard =mutableListOf<Piece>()

    var moveIncrement by mutableStateOf(0)
        private set

    var playerTurn by mutableStateOf(Piece.Color.WHITE)


    /*
    *
    * User events
    *
    */


    fun selectPiece(piece: Piece){
        if(piece.color != playerTurn){
            return
        }

        if(piece == selectedPiece){
            clearSelection()
        }else{
            selectedPiece = piece
            selectedPieceMoves = piece.getAvailableMoves(pieces= pieces).toList()
        }
    }


    fun moveSelectedPiece(x: Int,y:Int){
        selectedPiece?.let { piece->
            if(!isAvailableMove(x=x,y=y))
                return

            if(piece.color != playerTurn) return

            movePiece(
                piece= piece,
                position = IntOffset(x,y)
            )

            clearSelection()

            switchPlayerTurn()

            moveIncrement++
        }
    }


    private fun movePiece(
        piece: Piece,
        position: IntOffset
    ){
        val targetPiece = pieces.find { it.position == position }

        if(targetPiece !=null) {
            removedPiecesInBoard.add(targetPiece)
            removePiece(targetPiece)
        }

        piece.position = position

    }

    private fun removePiece(piece: Piece){
        _pieces.remove(piece)
    }


    fun isAvailableMove(x:Int, y:Int) : Boolean=
        selectedPieceMoves.any{it.x == x && it.y== y}

    fun switchPlayerTurn(){
        playerTurn = if(playerTurn == Piece.Color.WHITE)
            Piece.Color.BLACK
        else
            Piece.Color.WHITE
    }

    fun getPiece(x:Int,y:Int) : Piece?=
        _pieces.find { it.position.x == x && it.position.y ==y }



    /*
    *
    * Private methods
    *
    *
    */

    private fun clearSelection(){
        selectedPiece=null
        selectedPieceMoves = emptyList()
    }

}


@Composable
fun Board.rememberPieceAt(x:Int,y:Int): Piece? =
    remember (x,y,moveIncrement){
        getPiece(
            x = x,
            y= y
        )
    }

@Composable
fun Board.rememberIsAvailableMove(x:Int,y:Int):Boolean=
    remember(x,y,selectedPieceMoves) {
        isAvailableMove(
            x= x,
            y=y
        )
    }