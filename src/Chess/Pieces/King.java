package Chess.Pieces;

import BoardGame.Board;
import Chess.ChessPiece;
import Chess.Color;

// Cria a peça rei
public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }



    @Override
    public String toString()
    {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
