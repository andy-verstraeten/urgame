package model.square;

import model.piece.PieceType;

public class Square {
    private SquareType type;
    private PieceType pieceOn;

    public Square() {

        this.type = SquareType.BLANK;
        this.pieceOn = PieceType.NONE;
    }

    public Square(SquareType type, PieceType pieceOn) {
        this.type = type;
        this.pieceOn = pieceOn;
    }

    public SquareType getType() {
        return type;
    }

    public PieceType getPieceOn() {
        return pieceOn;
    }

    public void removePiece() {
        this.pieceOn = PieceType.NONE;
    }
    public void setPieceOn(PieceType pieceOn) {
        this.pieceOn = pieceOn;
    }

    public void setType(SquareType type) {
        this.type = type;
    }
}
