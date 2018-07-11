package model.board;


import sun.font.TrueTypeFont;

import java.util.Random;

public class Dices {
    private boolean[] upSides;
    private int turns;
    private Random random = new Random();
    public Dices() {
        reset();
    }

    public void reset() {
        this.upSides = new boolean[3];
    }

    public boolean[] getUpSides() {
        return this.upSides;
    }
    public int getTurns() { return this.turns;}

    public void rollDice() {
        for(int i = 0; i < this.upSides.length; i++) {
            this.upSides[i] = random.nextBoolean();
        }
        this.calcTurns();
    }

    public void calcTurns() {
        int nrOfUps = 0;
        for(int i = 0; i < this.upSides.length; i++) {
            if (this.upSides[i]==true)
                nrOfUps++;
        }

        turns = nrOfUps == 0 ? 4: nrOfUps;

    }
}
