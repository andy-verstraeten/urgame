package utilities;

import model.path.PathType;
import model.scores.Score;
import model.scores.ScoreComparator;

import java.io.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ScoreFileHandler {
    public static String SCORE_PATH = "saved\\scores.txt";
    public static ArrayList<Score> getScoreList() throws IOException {
        ArrayList<Score> scoreList = new ArrayList<Score>();
        String line = new String();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(SCORE_PATH));

            while((line = bufferedReader.readLine())!=null) {
                String[] values = line.split("#");
                String name =  values[0];
                int moves = Integer.parseInt(values[1]);
              //TODO PATHTYPE
                Score score = new Score(name, moves, PathType.STANDARD);
                scoreList.add(score);
            }
        }
        catch(NoSuchElementException | NumberFormatException e1) {
            throw new IOException("Leesfout in regel:"+line, e1);
        }
        catch(IOException e2) {
            throw new IOException("Het bronbestand "+ SCORE_PATH + "kan niet geopend worden." + e2.getMessage(), e2);
        }


        scoreList.sort(new ScoreComparator());

        return scoreList;
    }
    public static void saveScore(Score score) throws IOException {
        ArrayList<Score> scoreList = ScoreFileHandler.getScoreList();
        scoreList.add(score);
        scoreList.sort(new ScoreComparator());


        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(SCORE_PATH)));
            for (Score writeScore : scoreList.subList(0, scoreList.size()>=5?5: scoreList.size())) {
                printWriter.printf("%s#%d#%s%n", writeScore.getPlayerName(), writeScore.getMoves(),
                        writeScore.getPathType().ordinal());
            }
            printWriter.close();
        }
        catch(IOException ioE) {
            throw new IOException("Problemen met het wegschrijven naar het doelbestand: "+ SCORE_PATH, ioE);
        }

    }
}
