package Chess;

import BoardGame.Board;
import BoardGame.BoardException;
import BoardGame.Piece;
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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) // Transforma uma posição do tabuleiro em uma posição do sistema e, então, gera um movimento e captura de peça
    {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validadeSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) // move uma peça de uma posição para um destino
    {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validadeSourcePosition(Position position)
    {
        if(!board.thereIsAPiece(position))
        {
            throw new ChessException("There is no piece on source position");
        }
    }

    private void initialSetup()
    {
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
    }
}
