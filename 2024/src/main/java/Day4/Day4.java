package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {
    static final int ROWS = 140;
    static String[] xmasArray = new String[ROWS];

    public static void main(String[] args) {
        String[] xmasArray = createArray();
        // System.out.println(xmasArray[0].length());
        // System.out.println(checkHorizontally(xmasArray));
        // System.out.println(checkVertically(xmasArray));
        // System.out.println(checkDiagonally(xmasArray));
        System.out.println(checkHorizontally(xmasArray) + checkVertically(xmasArray) + checkDiagonally(xmasArray));
        System.out.println(checkXes(xmasArray));
    }

    public static String[] createArray() {
        String filePath = "./inputs/Day4Table.txt";
        //String filePath = "./inputs/Day4TableTest.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                xmasArray[counter] = line;
                counter++;
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
        return xmasArray;
    }

    public static int checkXes(String[] xmasArray){
        int counter = 0;

        for(int i = 1; i < xmasArray.length - 1; i++){
            for(int j = 1; j < xmasArray[i].length() -1; j++){
                if(xmasArray[i].charAt(j) == 'A'){
                    /*
                     * M S
                     *  A
                     * M S
                     */
                    if(xmasArray[i-1].charAt(j-1) == 'M' && xmasArray[i+1].charAt(j+1) == 'S' && xmasArray[i + 1].charAt(j-1) == 'M' && xmasArray[i-1].charAt(j+1)== 'S'){
                        counter++;
                    }
                    /*
                     * M M
                     *  A
                     * S S
                     */
                    if(xmasArray[i-1].charAt(j-1) == 'M' && xmasArray[i+1].charAt(j+1) == 'S' && xmasArray[i + 1].charAt(j-1) == 'S' && xmasArray[i-1].charAt(j+1)== 'M'){
                        counter++;
                    }
                    /*
                     * S M
                     *  A
                     * S M
                     */
                    if(xmasArray[i-1].charAt(j-1) == 'S' && xmasArray[i+1].charAt(j+1) == 'M' && xmasArray[i + 1].charAt(j-1) == 'S' && xmasArray[i-1].charAt(j+1)== 'M'){
                        counter++;
                    }
                    /*
                     * S S
                     *  A
                     * M M
                     */
                    if(xmasArray[i-1].charAt(j-1) == 'S' && xmasArray[i+1].charAt(j+1) == 'M' && xmasArray[i + 1].charAt(j-1) == 'M' && xmasArray[i-1].charAt(j+1)== 'S'){
                        counter++;
                    }
                   
                }
            }
        }

        return counter;
    }

    public static int checkHorizontally(String[] xmasArray) {
        int counter = 0;
        for (int i = 0; i < xmasArray.length; i++) {
            for (int j = 0; j < xmasArray[i].length() - 3; j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i].charAt(j + 1) == 'M') {
                        if (xmasArray[i].charAt(j + 2) == 'A') {
                            if (xmasArray[i].charAt(j + 3) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < xmasArray.length; i++) {
            for (int j = 3; j < xmasArray[i].length(); j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i].charAt(j - 1) == 'M') {
                        if (xmasArray[i].charAt(j - 2) == 'A') {
                            if (xmasArray[i].charAt(j - 3) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }

        // System.out.println("So many horizontal XMASes " + counter);
        return counter;
    }

    public static int checkVertically(String[] xmasArray) {
        int counter = 0;
        for (int i = 0; i < xmasArray.length - 3; i++) {
            for (int j = 0; j < xmasArray[i].length(); j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i + 1].charAt(j) == 'M') {
                        if (xmasArray[i + 2].charAt(j) == 'A') {
                            if (xmasArray[i + 3].charAt(j) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i < xmasArray.length; i++) {
            for (int j = 0; j < xmasArray[i].length(); j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i - 1].charAt(j) == 'M') {
                        if (xmasArray[i - 2].charAt(j) == 'A') {
                            if (xmasArray[i - 3].charAt(j) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }

        // System.out.println("So many horizontal XMASes " + counter);
        return counter;

    }

    public static int checkDiagonally(String[] xmasArray) {
        int counter = 0;
        for (int i = 0; i < xmasArray.length - 3; i++) {
            for (int j = 0; j < xmasArray[i].length() - 3; j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i + 1].charAt(j + 1) == 'M') {
                        if (xmasArray[i + 2].charAt(j + 2) == 'A') {
                            if (xmasArray[i + 3].charAt(j + 3) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i < xmasArray.length; i++) {
            for (int j = 3; j < xmasArray[i].length(); j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i - 1].charAt(j - 1) == 'M') {
                        if (xmasArray[i - 2].charAt(j - 2) == 'A') {
                            if (xmasArray[i - 3].charAt(j - 3) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < xmasArray.length-3; i++) {
            for (int j = 3; j < xmasArray[i].length(); j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i + 1].charAt(j - 1) == 'M') {
                        if (xmasArray[i + 2].charAt(j - 2) == 'A') {
                            if (xmasArray[i + 3].charAt(j - 3) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i < xmasArray.length; i++) {
            for (int j = 0; j < xmasArray[i].length() - 3; j++) {
                if (xmasArray[i].charAt(j) == 'X') {
                    if (xmasArray[i - 1].charAt(j + 1) == 'M') {
                        if (xmasArray[i - 2].charAt(j + 2) == 'A') {
                            if (xmasArray[i - 3].charAt(j + 3) == 'S') {
                                System.out.println(i + "  " + j);
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        // System.out.println("So many horizontal XMASes " + counter);
        return counter;
    }
}
