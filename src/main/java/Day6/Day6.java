package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day6 {

    static int Xpos = 0;
    static int Ypos = 0;
    static char up = '^';
    static char right = '>';
    static char down = 'v';
    static char left = '<';
    public static void main(String[] args) {
        String filePath = "./inputs/Day6Input.txt";
        List<char[]> lines = new ArrayList<>(); // Generiert eine Liste für jede Zeile, welche als CharArray dargestellt wird
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray()); // Speichert jede Zeile als CharArray
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Bestimmt dynamisch die größe des 2D Arrays
        int rows = lines.size();
        int cols = rows > 0 ? lines.get(0).length : 0;
        char[][] charArray = new char[rows][cols];

        // Kopiert die Liste in den Array
        for (int i = 0; i < rows; i++) {
            charArray[i] = lines.get(i);
        }

        // Printet den 2D Array (zum testen)
        /*
        System.out.println("Gelesener 2D-Array:");
        for (char[] row : charArray) {
            System.out.println(Arrays.toString(row));
        }
        */

        findGuard(charArray);
        System.out.println(Ypos + "   " + Xpos);
        System.out.println(moveGuard(charArray));

    }

    public static void findGuard(char[][] guardMap){
        for(int i = 0; i < guardMap.length; i++){
            for(int j = 0; j < guardMap[i].length; j++){
                if(guardMap[i][j] == '^' || guardMap[i][j] == '>' || guardMap[i][j] == 'v' || guardMap[i][j] == '<'){
                    Ypos = i;
                    Xpos = j;
                }
            }
        }
    }

    static public int moveGuard(char[][] guardMap){
        int uniquePositions = 0;
        boolean insideMap = true;
        while(insideMap){
            char curentDir = guardMap[Ypos][Xpos];
            if(curentDir == up && Ypos > 0 && (guardMap[Ypos - 1][Xpos] == '.' || guardMap[Ypos - 1][Xpos] == 'x')){
                if(guardMap[Ypos - 1][Xpos] == '.' && insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                guardMap[Ypos - 1][Xpos] = up;
                Ypos--;
            }else if(curentDir == up && Ypos == 0){
                if(insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                insideMap = false;
                System.out.println(uniquePositions);
                break;
            } else if(curentDir == up && guardMap[Ypos - 1][Xpos] == '#'){
                guardMap[Ypos][Xpos] = right;
            }
            /* 
            for (char[] row : guardMap) {
                System.out.println(Arrays.toString(row));
            }
            */
            System.out.println(Ypos + "   " + Xpos + "   " + curentDir);
            //--------------------------------------------------------------------------
            if(curentDir == right && Xpos < guardMap[Ypos].length - 1 && (guardMap[Ypos][Xpos + 1] == '.'|| guardMap[Ypos][Xpos + 1] == 'x')){
                if(guardMap[Ypos][Xpos + 1] == '.' && insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                guardMap[Ypos][Xpos + 1] = right;
                Xpos++;
            }else if(curentDir == right && Xpos == guardMap[Ypos].length - 1){
                if(insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                insideMap = false;
                System.out.println(uniquePositions);
                break;
            } else if(curentDir == right && guardMap[Ypos][Xpos + 1] == '#'){
                guardMap[Ypos][Xpos] = down;
            }
           /* 
            for (char[] row : guardMap) {
                System.out.println(Arrays.toString(row));
            }
            */
            System.out.println(Ypos + "   " + Xpos + "   " + curentDir);
            //--------------------------------------------------------------------------
            if(curentDir == down && Ypos < guardMap.length - 1 && (guardMap[Ypos + 1][Xpos] == '.' ||guardMap[Ypos + 1][Xpos] == 'x')){
                if(guardMap[Ypos + 1][Xpos] == '.' && insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                guardMap[Ypos + 1][Xpos] = down;
                Ypos++;
            }else if(curentDir == down && Ypos == guardMap.length - 1){
                if(insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                insideMap = false;
                System.out.println(uniquePositions);
                break;
            } else if(curentDir == down && guardMap[Ypos + 1][Xpos] == '#'){
                guardMap[Ypos][Xpos] = left;
            }
            /* 
            for (char[] row : guardMap) {
                System.out.println(Arrays.toString(row));
            }
            */
            System.out.println(Ypos + "   " + Xpos + "   " + curentDir);
             //--------------------------------------------------------------------------
             if(curentDir == left && Xpos > 0 && (guardMap[Ypos][Xpos - 1] == '.' || guardMap[Ypos][Xpos - 1] == 'x')){
                if(guardMap[Ypos][Xpos - 1] == '.' && insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                guardMap[Ypos][Xpos - 1] = left;
                Xpos--;
            }else if(curentDir == left && Xpos == 0){
                if(insideMap){
                    uniquePositions++;
                }
                guardMap[Ypos][Xpos] = 'x';
                insideMap = false;
                System.out.println(uniquePositions);
                break;
            } else if(curentDir == left && guardMap[Ypos][Xpos - 1] == '#'){
                guardMap[Ypos][Xpos] = up;
            }
            /* 
            for (char[] row : guardMap) {
                System.out.println(Arrays.toString(row));
            }
            */
            System.out.println(Ypos + "   " + Xpos + "   " + curentDir);
            
        }
        return uniquePositions;
    }

}