package presenter.game;

import model.Game;
import model.move.Move;
import model.player.MoveException;
import model.square.SquareType;

public class MoveManager {
    private Game game;

    public MoveManager(Game game) {
        this.game = game;
    }

    public void move(int pieceIndex, int moves) throws MoveException {

        game.setLastMove(new Move(game.getCurrentPlayer(), pieceIndex, game.getDices()));
        //check off de zet een punt scoorde, haal de pion van bord else doe zet
        if (scoresPoint(pieceIndex, moves)) {
            makePoint(pieceIndex);
        } else {
            makeNormalMove( pieceIndex,  moves);
        }
    }

    public boolean scoresPoint(int pieceIndex, int moves) {
        return (game.getCurrentPlayer().getPiecePositionByIndex(pieceIndex)+ moves)
                >= game.getCurrentPlayer().getPath().length();
    }

    public void makePoint(int pieceIndex) {
        game.getCurrentPlayer().upscore();
        game.getCurrentPlayer().addPieceFinished(pieceIndex);
        game.getCurrentPlayer().removePieceOnBoard(pieceIndex);
        game.getBoard().updateBoard();
    }

    public boolean landsOnOwnPiece(int currentPosition, int moves) {
        return game.getCurrentPlayer().getPieceManager().hasPieceWithPosition(currentPosition+moves);
    }

    public boolean landsOnOccupiedRosseta(int[] nextCoords) {
        return (game.getBoard().getSquareTypeByCoords(nextCoords) == SquareType.ROSETTA) &&
                landsOnEnemyPiece(nextCoords);
    }

    public boolean landsOnEnemyPiece(int[] nextCoords) {
        return game.getBoard().getPieceOnTypeByCoords(nextCoords) ==
                game.getCurrentPlayer().getPieceManager().getEnemyPieceType();
    }

    public void makeNormalMove(int pieceIndex, int moves)  throws MoveException{
        int currentPosition = game.getCurrentPlayer().getPiecePositionByIndex(pieceIndex);
        int[] nextCoords =game.getCurrentPlayer().coordsFromPath(currentPosition + moves);
        //kijk na of de zet niet land op één van de eigen pionnen
        if(landsOnOwnPiece(currentPosition, moves))
            throw new MoveException("Pion land op eigen pion");
        //kijk na of zet land op rosetta met een pion van de andere speler
        if (landsOnOccupiedRosseta(nextCoords)) {
            throw new MoveException("Pion land rosetta met een pion van de tegenspeler");
        }
        //kijk na of het een pion van de andere speler van het bord gooit
        if (landsOnEnemyPiece(nextCoords)) {
            game.kickOffBoard(nextCoords);
        }
        game.getCurrentPlayer().getPieceManager().movePiece(pieceIndex, moves);
    }


}
