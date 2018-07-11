package model.board;


import model.Game;
import model.piece.Piece;
import model.piece.PieceType;
import model.player.Player;
import model.square.Square;
import model.square.SquareType;


public class Board {
    private Square[][] squares;
    private Game game;

    public Board(Game game) {
        this.squares = new Square[3][8];
        this.game = game;
        reset();

    }

    public void reset() {
        for(int x=0; x < this.squares.length; x++) {
            for(int y=0; y< this.squares[x].length; y++) {
                this.squares[x][y] = new Square();
            }
        }
        this.populateRosettas();
        this.squares[0][4].setType(SquareType.INOUT);
        this.squares[0][5].setType(SquareType.INOUT);
        this.squares[2][4].setType(SquareType.INOUT);
        this.squares[2][5].setType(SquareType.INOUT);
    }

    public void populateRosettas() {
        int[][] rosettaLocations  = {{0,0}, {2,0}, {1,3}, {2,6}, {0,6}};
        for(int i = 0; i< rosettaLocations.length; i++) {
            this.squares[rosettaLocations[i][0]][rosettaLocations[i][1]].setType(SquareType.ROSETTA);
        }
    }



    public Square[][] getSquares() {
        return squares;
    }

    public SquareType getSquareTypeByCoords(int[] coords) {
        return this.squares[coords[0]][coords[1]].getType();
    }

    public PieceType getPieceOnTypeByCoords(int[] coords) {
        return this.squares[coords[0]][coords[1]].getPieceOn();
    }

    public void clearSquare(int[] coords) {
        this.squares[coords[0]][coords[1]].setPieceOn(PieceType.NONE);
    }


    public void updateBoard() {
       reset();

       updatePiecesOfPlayer(game.getPlayerA());
       updatePiecesOfPlayer(game.getPlayerB());
    }

    private void updatePiecesOfPlayer(Player player) {
        for(Piece piece: player.getPieceManager().getPieces()) {
            if(player.getPieceManager().getPiecesOnBoard().contains(piece.getIndex())) {
                int[] coords = player.getPath().getCoords(piece.getPosition());
                squares[coords[0]][coords[1]].setPieceOn(player.getPieceManager().getPieceType());
            }
        }
    }
}
