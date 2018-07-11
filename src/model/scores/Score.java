package model.scores;

import model.path.PathType;

public class Score {
    private String playerName;
    private int moves;
    private String loserName;
    private PathType pathType;

    public Score(String playerName, int moves, PathType pathType) {
        this.playerName = playerName;
        this.moves = moves;
        this.pathType = pathType;
    }

    public Score(String playerName, int moves, String loserName,PathType pathType) {
        this.playerName = playerName;
        this.moves = moves;
        this.loserName = loserName;
        this.pathType = pathType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMoves() {
        return moves;
    }

    public String getLoserName() {
        return loserName;
    }

    public PathType getPathType() {
        return pathType;
    }
}
