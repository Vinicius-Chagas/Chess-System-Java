package Application;

import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    public static void printBoard(ChessPiece[][] pieces) //impressão do tabuleiro
    {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8-i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println(" a b c d e f g h");
    }

    public static void printPiece(ChessPiece piece)// Impressão das peças e verificação se há uma peça na posição
    {
        if(piece == null)
        {
            System.out.print("-");
        }
        else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }

    public static ChessPosition readChessPosition(Scanner sc) // Le uma posiçao do tabuleiro e retorna uma ChessPosition
    {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        catch(RuntimeException e)
        {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8");
        }
    }
}
