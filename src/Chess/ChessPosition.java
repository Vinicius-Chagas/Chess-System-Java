package Chess;

import BoardGame.Position;

public class ChessPosition {
    private Character column;
    private Integer row;

    public ChessPosition(char column, int row)
    {
        if(column < 'a' || column > 'h' || row < 1 || row > 8)
        {
            throw new ChessException("Error instantiating ChessPosition. Valid positions are from a1 to h8");
        }
        this.column = column;
        this.row = row;
    }

    public Character getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    protected Position toPosition() // Transforma a8 para 0,0;
    {
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position position) // Transforma 0,0 para a8
    {
        return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString()
    {
        return "" + column + row;
    }

}
