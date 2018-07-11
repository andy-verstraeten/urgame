package model.piece;


import model.piece.PieceType;

public class Piece {
    private int index;
    private PieceType type;
    private int position;

    public Piece(int index,PieceType type) {
        this.index = index;
        this.type = type;
        this.position = 0;
    }


    public int getIndex() {
        return index;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;

    }
}

