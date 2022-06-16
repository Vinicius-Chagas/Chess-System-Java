package Chess;

import BoardGame.Board;
import BoardGame.BoardException;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.Pieces.King;
import Chess.Pieces.Rook;

public class ChessMatch {
    private  Board board;
    private int turn;
    private Color currentPlayer;

    public ChessMatch()
    {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() // Retorna as peças que estão no tabuleiro
    {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()] ;
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] =(ChessPiece)board.piece(i, j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char colunm, int row, ChessPiece piece) // Coloca uma peça em uma posiçao do tabuleiro
    {
        board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition)
    {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) // Transforma uma posição do tabuleiro em uma posição do sistema e, então, gera um movimento e captura de peça
    {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) // move uma peça de uma posição para um destino
    {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position)
    {
        if(!board.thereIsAPiece(position))
        {
            throw new ChessException("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor())
        {
            throw new ChessException("The chosen piece is not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMove())
        {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void nextTurn()
    {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void validateTargetPosition(Position source, Position target)
    {
        if (!board.piece(source).possibleMove(target))
        {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void initialSetup()
    {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
