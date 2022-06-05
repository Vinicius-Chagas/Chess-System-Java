package Chess;

import BoardGame.Board;
import BoardGame.Position;
import Chess.Pieces.Rook;

public class ChessMatch {
    private  Board board;

    public ChessMatch()
    {
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieces() // Retorna as peças que estão no tabuleiro
    {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()] ;
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] =(ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char colunm, int row, ChessPiece piece) // Coloca uma peça em uma posiçao do tabuleiro
    {
        board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
    }

    private void initialSetup()
    {
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
    }
}
