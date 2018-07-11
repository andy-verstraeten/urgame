package model.player;

import model.Game;
import model.piece.Piece;
import model.piece.PieceType;

import java.util.ArrayList;
import java.util.Arrays;

public class PieceManager {

    private Player player;
    private Game game;
    private Piece[] pieces;
    private PieceType pieceType;
    private ArrayList<Integer> piecesOnBoard;
    private ArrayList<Integer> piecesOffBoard;
    private ArrayList<Integer> piecesFinished;
    private PieceType enemyPieceType;


    public PieceManager(Player player,PieceType pieceType, Game game) {
        this.player = player;
        this.game = game;
        this.pieces = new Piece[7];
        this.piecesOnBoard = new ArrayList<Integer>();
        this.piecesOffBoard = new ArrayList<Integer>();
        this.piecesFinished = new ArrayList<Integer>();
        for(int i =0; i< this.pieces.length; i++) {
            this.pieces[i] = new Piece(i,this.pieceType);
        }
        this.pieceType = pieceType;
        this.enemyPieceType = pieceType == PieceType.WHITE?pieceType.BLACK: pieceType.WHITE;
        updatePiecesOnBoardList();
    }

    public void updatePiecesOnBoardList() {
        piecesOffBoard.clear();
        piecesOnBoard.clear();
        for(int i= 0; i < pieces.length; i++) {

            if(pieces[i].getPosition() == 0)
                piecesOffBoard.add(i);
            else if (!piecesFinished.contains(i))
                piecesOnBoard.add(i);

        }
    }


    public ArrayList<Integer> getPiecesOffBoard() {
        return piecesOffBoard;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public ArrayList<Integer> getPiecesOnBoard() {
        return piecesOnBoard;
    }

    public PieceType getEnemyPieceType() {
        return enemyPieceType;
    }

    public ArrayList<Integer> getPiecesFinished() {
        return piecesFinished;
    }

    public int getPieceIndexByCoords(int[] coords) {
        int result = -1;
        for(int currentIndex: piecesOnBoard) {
            if(Arrays.equals(player.getPath().getCoords(pieces[currentIndex].getPosition()),coords))
                result = currentIndex;
        }
        return result;
    }

    //wordt gecalled als andere speler je pion op de gegevens coordinaten van het bord gooit
    public void kickOffBoard(int[] coords) {
        //zoek de juiste piece op de coordinaten
        int targetPieceIndex =  getPieceIndexByCoords(coords);
        game.getLastMove().setKilledPiece(targetPieceIndex,pieces[targetPieceIndex].getPosition());
        if(targetPieceIndex>-1) {
            pieces[targetPieceIndex].setPosition(0);
            updatePiecesOnBoardList();
        }

    }

    public int getPiecePosition(int pieceIndex) {
        return pieces[pieceIndex].getPosition();
    }

    public void movePiece(int pieceIndex, int moves) {
        pieces[pieceIndex].setPosition(this.pieces[pieceIndex].getPosition() + moves);
        //update de lijst me indexes van de stukken op het bord in huidige klasse
        updatePiecesOnBoardList();
        //update het bord zodat het kan geviewed worden
        game.getBoard().updateBoard();
    }

    public void setPieceToPos(int pieceIndex, int pos) {
        pieces[pieceIndex].setPosition(pos);
        updatePiecesOnBoardList();
    }

    public boolean hasPieceWithPosition(int position) {
        for (Integer onBoardPieceIndex : piecesOnBoard) {
            if ((position) == pieces[onBoardPieceIndex].getPosition())
                return true;
        }
        return false;
    }
}
