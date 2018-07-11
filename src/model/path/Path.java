package model.path;

import model.player.PlayerID;

public class Path {
    private int[][] STANDARD_A = new int[][]{{0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}, {1, 0}, {1, 1}, {1, 2},
            {1,3}, {1,4}, {1,5}, {1,6}, {1,7}, {0,7}, {0,6}};

    private final int[][] STANDARD_B = new int[][]{{2, 4}, {2, 3}, {2, 2}, {2, 1}, {2, 0}, {1, 0}, {1, 1}, {1, 2},
            {1,3}, {1,4}, {1,5}, {1,6}, {1,7}, {2,7}, {2,6}};

    private final int[][] MURRAY_A = new int[][]{{0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}, {1, 0}, {1, 1}, {1, 2},
            {1,3}, {1,4}, {1,5}, {1,6}, {0,6}, {0,7}, {1,7}, {2,7}, {2,6}, {1,6}, {1,5}, {1,4},{1,3},{1,2},{1,1},{1,0},
            {0,0}, {0,1}, {0,2}, {0,3}, {0,4}};

    private final int[][] MURRAR_B = new int[][]{{2, 4}, {2, 3}, {2, 2}, {2, 1}, {2, 0}, {1, 0}, {1, 1}, {1, 2},
    {1,3}, {1,4}, {1,5}, {1,6}, {2,6},{2,7}, {1,7}, {0,7}, {0,6}, {1,6}, {1,5}, {1,4},{1,3},{1,2},{1,1},{1,0},
            {2,0}, {2,1}, {2,2}, {2,3}, {2,4}};

    private final int[][] MASTER_A = new int[][]{{0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}, {1, 0}, {1, 1}, {1, 2},
    {1,3}, {1,4}, {1,5}, {1,6},{2,6},{2,7}, {1,7}, {1,6}};

    private final int[][] MASTER_B = new int[][]{{2, 4}, {2, 3}, {2, 2}, {2, 1}, {2, 0}, {1, 0}, {1, 1}, {1, 2},
            {1,3}, {1,4}, {1,5}, {1,6},{0,6}, {0,7}, {1,7}, {2,7}, {2,6}};

    private int[][] boardReferences;

    public Path(PlayerID player, PathType pathType) {
        switch(pathType) {
            case MURRAY:
                if (player == PlayerID.A)
                    this.boardReferences = MURRAY_A;
                else if (player == PlayerID.B)
                    this.boardReferences = MURRAR_B;
                break;
            case MASTER:
                if (player == PlayerID.A)
                    this.boardReferences = MASTER_A;
                else if (player == PlayerID.B)
                    this.boardReferences = MASTER_B;
                break;
            default:
                if (player == PlayerID.A)
                    this.boardReferences = STANDARD_A;
                else if (player == PlayerID.B)
                    this.boardReferences = STANDARD_B;
                break;
        }

    }

    public int length() {
        return boardReferences.length;
    }

    public int[] getCoords(int position) {
        return this.boardReferences[position];
    }
}
