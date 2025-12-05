package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args) {

        String filePath = "./inputs/Day3Input.txt";

        boolean enabled = true;
        int sum = 0;

        String mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        String doRegex = "do\\(\\)";
        String dontRegex = "don't\\(\\)";

        Pattern mulPattern = Pattern.compile(mulRegex);
        Pattern doPattern = Pattern.compile(doRegex);
        Pattern dontPattern = Pattern.compile(dontRegex);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = 0;
                while (index < line.length()) {
                   //dont
                    Matcher dontMatcher = dontPattern.matcher(line.substring(index));
                    if (dontMatcher.lookingAt()) {
                        enabled = false;
                        index += dontMatcher.end();
                        continue;
                    }
                    //do
                    Matcher doMatcher = doPattern.matcher(line.substring(index));
                    if (doMatcher.lookingAt()) {
                        enabled = true;
                        index += doMatcher.end();
                        continue;
                    }
                    //mul
                    Matcher mulMatcher = mulPattern.matcher(line.substring(index));
                    if (mulMatcher.lookingAt()) {
                        if (enabled) {
                            
                            int x = Integer.parseInt(mulMatcher.group(1));
                            int y = Integer.parseInt(mulMatcher.group(2));
                            sum += x * y;
                        }
                        index += mulMatcher.end();
                        continue;
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
        System.out.println(sum);
    }
}