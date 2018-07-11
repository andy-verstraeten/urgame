package model;

import model.board.Board;
import model.board.Dices;
import model.move.Move;
import model.path.PathType;
import model.player.Player;
import model.player.PlayerID;

public class Game {
    private Player playerA;
    private Player playerB;
    private Player currentPlayer;
    private Dices dices;
    private Board board;
    private Move lastMove;

    public Game(PathType pathType) {
        dices = new Dices();
        board = new Board(this);
        playerA = new Player(PlayerID.A,  this,pathType);
        playerB = new Player(PlayerID.B, this, pathType);
        currentPlayer = playerA;
        dices = new Dices();

    }

    public void setStartValues(String playerAName, String PlayerBName) {
        playerA.reset();
        playerB.reset();
        playerA.setName(playerAName);
        playerB.setName(PlayerBName);
        dices.reset();
        board.reset();

    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public Dices getDices() {
        return dices;
    }


    public void changeCurrentPlayer() {
        this.currentPlayer = this.currentPlayer==playerA? this.playerB: this.playerA;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getEnemyPlayer() {
        return currentPlayer==playerA?playerB:playerA;
    }

    public Player getOtherPlayer() {
        return  this.currentPlayer==playerA? this.playerB: this.playerA;
    }

    public Board getBoard() {
        return board;
    }


    public int getNumberOfPieces() {
        return currentPlayer.getPieceManager().getPieces().length;
    }



    public void kickOffBoard(int[] coords) {
        Player otherPlayer = currentPlayer == playerA ? playerB: playerA;
        otherPlayer.getPieceManager().kickOffBoard(coords);
    }

}
