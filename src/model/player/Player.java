package model.player;

import model.board.Board;
import model.Game;
import model.path.Path;
import model.path.PathType;
import model.piece.PieceType;

public class Player {
    private Game game;
    private String name;
    private PlayerID playerID;
    private int score;
    private int moves;
    private PathType pathType;

    private PieceManager pieceManager;
    private Path path;


    private Board board;


    public Player(PlayerID playerID, Game game, PathType pathType) {
        this.playerID = playerID;
        this.game = game;
        this.pathType = pathType;
        reset();
    }

    public void reset() {
        this.name = "";
        this.score = 0;
        this.moves = 0;
        this.path = new Path(playerID, pathType);
        this.board = this.game.getBoard();
        this.pieceManager = new PieceManager(
                this,
                this.playerID == PlayerID.A?PieceType.WHITE: PieceType.BLACK,
                game);
    }


    public int getMoves() {
        return this.moves;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }


    public Path getPath() {
        return path;
    }

    public PieceManager getPieceManager() {
        return pieceManager;
    }

    public void incrementMoves() {
        this.moves++;
    }

    public void upscore() {
        System.out.println(score);
        score++;
    }

    public PathType getPathType() {
        return pathType;
    }

    public int numberOfPiecesFinished() {
        return pieceManager.getPiecesFinished().size();
    }


    public int getPiecePositionByIndex(int index) {
        return pieceManager.getPieces()[index].getPosition();
    }

    public int[] coordsFromPath(int position) {
        return path.getCoords(position);
    }

    public void addPieceFinished(int pieceIndex) {
        pieceManager.getPiecesFinished().add(pieceIndex);
    }

    public void removePieceOnBoard(int pieceIndex) {
        pieceManager.getPiecesOnBoard().remove(new Integer(pieceIndex));
    }


}

