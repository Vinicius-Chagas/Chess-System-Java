package Application;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] Args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();
        while(!chessMatch.isCheckMate())
        {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, capturedPieces);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null)
                {
                    capturedPieces.add(capturedPiece);
                }
            }
            catch (ChessException e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, capturedPieces);

    }


}
