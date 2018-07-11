package model.move;

import model.board.Dices;
import model.player.Player;

import static java.lang.Math.abs;

public class Move {
    private Player player;
    private int piece;
    private int moves;
    private Dices dices;
    private int killedPieceIndex;
    private int killedPiecePosition;

    public Move(Player player, int piece, Dices dices) {
        this.player = player;
        this.piece = piece;
        this.moves = abs(dices.getTurns());
        this.killedPiecePosition = -1;
        this.killedPieceIndex = -1;
        this.dices = dices;
    }

    public void setKilledPiece(int index, int position) {
        killedPieceIndex = index;
        killedPiecePosition = position;
    }

    public int getKilledPiecePosition() {
        return killedPiecePosition;
    }

    public int getKilledPieceIndex() {
        return killedPieceIndex;
    }

    public int getPiece() {
        return piece;
    }

    public int getMoves() {
        return moves;
    }

    public Dices getDices() {
        return dices;
    }
}
