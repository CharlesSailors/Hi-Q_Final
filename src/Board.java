import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class Board {
    int base;
    char[][] boardArray;
    public int[][] allPossibleLocations = new int[10][];


    public Board(int base) throws IOException {
        base = base;
        makeBoard(base);
        checkAllIndices();
        chooseOpening();
        //print2D(boardArray);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        /*
        while(true) {
            print2D(boardArray);
            // Reading data using readLine
            System.out.println("Please enter start row");
            int response1 = Integer.parseInt(reader.readLine());
            System.out.println("Please enter start column");
            int response2 = Integer.parseInt(reader.readLine());
            System.out.println("Please enter finish row");
            int response3 = Integer.parseInt(reader.readLine());
            System.out.println("Please enter finish column");
            int response4 = Integer.parseInt(reader.readLine());
            //boolean goodMove = isValidMove(response1, response2, response3, response4);
            if(true) {
                makeMove(response1, response2, response3, response4);
            }
            else{
                System.out.println("might have worked");
            }

        }
*/
        //print2D(boardArray);

    }




    public void makeBoard(int base){
        boardArray = new char[base][];
        for (int i = 0; i < (boardArray.length); i++){
            int temp = i + 1;
            char[] row = new char[base*2-1];
            int center_index = (base / 2) + 1;
            char[] line;
            //for (int j = 0; j < temp; j++) {
            //row i should have 'temp' dashes
            //    line
            //}
            int dashCounter = i;
            Arrays.fill(row, ' ');
            if ((i % 2) == 0) {
                //Even
                row[center_index] = 'o';

                while (dashCounter != 0) {
                    row[center_index + dashCounter] = 'o';
                    row[center_index - dashCounter] = 'o';
                    dashCounter--;
                    dashCounter--;
                }
                //dashCounter -= 1;

            } else {
                //Odd
                dashCounter++;

                while (dashCounter != 0) {
                    row[center_index + dashCounter - 1] = 'o';
                    row[center_index - dashCounter + 1] = 'o';
                    dashCounter--;
                    dashCounter--;
                }

            }

            //Arrays.fill(row, '-');
            boardArray[i] = row;


        }
        //print2D(boardArray);


        //char[][] board = new char[][];
    }

    public void print2D(char[][] mat)
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++) {
            System.out.println("\n");
            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
        }
    }

    public void print2D(int[][] mat)
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++) {
            System.out.println("\n");
            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
        }
    }

    public void makeMove(int startRow, int startColumn, int finishRow, int finishColumn) {

        if (((Math.abs(startRow-finishRow) == 2) && (Math.abs(startColumn-finishColumn) == 2)) || (((Math.abs(startRow-finishRow) == 0) && (Math.abs(startColumn-finishColumn) == 4)))) {
            if ((boardArray[startRow][startColumn] == 'o') && (boardArray[finishRow][finishColumn] == '-')) {
                //Valid move//todo add check that middle index is a peg and that the index exists in all possible locations for start and end place
                boardArray[startRow][startColumn] = '-';
                boardArray[finishRow][finishColumn] = 'o';
                if(startRow>finishRow){
                    if(startColumn>finishColumn){
                        boardArray[startRow-1][startColumn-1] = '-';
                    }
                    else {
                        boardArray[startRow-1][finishColumn-1] = '-';
                    }
                }else if (startRow==finishRow) {
                    if(startColumn>finishColumn){
                        boardArray[finishRow][startColumn-2] = '-';
                    }
                    else {
                        boardArray[finishRow][finishColumn-2] = '-';
                    }
                }else{
                    if(startColumn>finishColumn){
                        boardArray[finishRow-1][startColumn-1] = '-';
                    }
                    else {
                        boardArray[finishRow-1][finishColumn-1] = '-';
                    }
                }
            }


        }
        else {
            //Not valid move
            System.out.println("invalid" + startRow + startColumn + finishRow + finishColumn);
        }
    }

    public void checkAllIndices() {
        int foundIndex = 0;
        for (int i =0; i < boardArray.length; i++) {
            for (int j =0; j < boardArray[0].length; j++) {
                if ((boardArray[i][j] == 'o') || (boardArray[i][j] == '-')) {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    allPossibleLocations[foundIndex] = temp;
                    foundIndex++;
                }
            }
        }
        //print2D(allPossibleLocations);
    }

    public void chooseOpening(){
        Random rand = new Random();
        int randOpening = rand.nextInt(allPossibleLocations.length);
        int[] opening = allPossibleLocations[randOpening];
        boardArray[opening[0]][opening[1]] = '-';
    }

}
