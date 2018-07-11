package utilities;

import model.path.PathType;
import model.scores.Score;
import model.scores.ScoreComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RulesFileHandler {
    public static String RULES_PATH = "saved\\rules.txt";
    public static String getRules() throws IOException {
        String rulesString = new String();
        String line = new String();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(RULES_PATH));
            while((line = bufferedReader.readLine())!=null) {
                rulesString += line+ "\n";
            }
        }
        catch(NoSuchElementException | NumberFormatException e1) {
            throw new IOException("Leesfout in regel:"+line, e1);
        }
        catch(IOException e2) {
            throw new IOException("Het bronbestand "+ RULES_PATH + "kan niet geopend worden." + e2.getMessage(), e2);
        }

        return rulesString;
    }
}
