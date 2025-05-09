import java.util.Arrays;

public class Bot {
    static Board board;
    char[][][] allStates;// = new char[100][board.base][];
    int statesFound;

    public Bot(Board board2){
        board = board2;
        allStates = new char[1000][board2.base][];
        board.checkAllIndices();
        solveBoard(board);
    }

    public void solveBoard(Board Board) {
        statesFound = 0;
        allStates[statesFound] = Board.boardArray;
        findStates(Board);
        //System.out.println("length" + allStates.length);
        for(char[][] e : allStates){
            //System.out.println("printing from here " + statesFound);
            //print2D(e);
        }
    }

    public void findStates(Board anotherFakeBoard) {
        //Board anotherFakeBoard = anotherFakeBoard2;
        //allStates[statesFound] = anotherFakeBoard.boardArray;
        print2D(anotherFakeBoard.boardArray);
        int[][] temp = anotherFakeBoard.allPossibleLocations;
        //System.out.println("goat:" + Arrays.toString(anotherFakeBoard.allPossibleLocations));
        for (int[] array : temp){
            for (int[] array2 : temp) {

                if (isValidMove(anotherFakeBoard, array[0], array[1], array2[0], array2[1])) {
                    System.out.println("Move: " + array[0] + array[1] + array2[0] + array2[1]);
                    makeFakeMove(anotherFakeBoard, array[0], array[1], array2[0], array2[1]);
                }else{
                    //System.out.println("not making it to if" + array[0] + array[1] + array2[0] + array2[1]);
                }
            }
        }
    }

    public void makeFakeMove(Board fakeBoard1, int startRow, int startColumn, int finishRow, int finishColumn) {
        Board fakeBoard = fakeBoard1;
        if (((Math.abs(startRow-finishRow) == 2) && (Math.abs(startColumn-finishColumn) == 2)) || (((Math.abs(startRow-finishRow) == 0) && (Math.abs(startColumn-finishColumn) == 4)))) {
            if ((fakeBoard.boardArray[startRow][startColumn] == 'o') && (fakeBoard.boardArray[finishRow][finishColumn] == '-')) {
                //Valid move//todo add check that middle index is a peg and that the index exists in all possible locations for start and end place
                fakeBoard.boardArray[startRow][startColumn] = '-';
                fakeBoard.boardArray[finishRow][finishColumn] = 'o';
                if(startRow>finishRow){
                    if(startColumn>finishColumn){
                        fakeBoard.boardArray[startRow-1][startColumn-1] = '-';
                    }
                    else {
                        fakeBoard.boardArray[startRow-1][finishColumn-1] = '-';
                    }
                }else if (startRow==finishRow) {
                    if(startColumn>finishColumn){
                        fakeBoard.boardArray[finishRow][startColumn-2] = '-';
                    }
                    else {
                        fakeBoard.boardArray[finishRow][finishColumn-2] = '-';
                    }
                }else{
                    if(startColumn>finishColumn){
                        fakeBoard.boardArray[finishRow-1][startColumn-1] = '-';
                    }
                    else {
                        fakeBoard.boardArray[finishRow-1][finishColumn-1] = '-';
                    }
                }
                boolean found = false;
                for (char[][] element : allStates) {
                    if (element == fakeBoard.boardArray) {
                        found = true;
                        break;
                    }
                }


                if(!found){
                    statesFound++;
                    allStates[statesFound] = fakeBoard.boardArray;
                }
                findStates(fakeBoard);
            }


        }
        else {
            //Not valid move
            //System.out.println("invalid" + startRow + startColumn + finishRow + finishColumn);
        }
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
    /*
    public char[][] getPossibleBoardState(Board potentialBoards, int startRow, int startColumn, int finishRow, int finishColumn){

        makeFakeMove(potentialBoards, startRow, startColumn, finishRow, finishColumn);
        return resultingBoardArray;
    }*/

    public boolean isValidMove(Board boardFake, int startRow, int startColumn, int finishRow, int finishColumn) {
        int[][] listOfPossibleLocations = boardFake.allPossibleLocations;
        boolean existsStart = false;
        boolean existsFinish = false;

        int[] tempStart = new int[2];
        tempStart[0] = startRow;
        tempStart[1] = startColumn;
        for (int i = 0; i < listOfPossibleLocations.length; i++) {
            if (listOfPossibleLocations[i][0] == tempStart[0]) {
                if (listOfPossibleLocations[i][1] == tempStart[1]) {
                    existsStart = true;
                }
            }
        }

        int[] tempFinish = new int[2];
        tempFinish[0] = finishRow;
        tempFinish[1] = finishColumn;
        for (int i = 0; i < listOfPossibleLocations.length; i++) {
            //System.out.println(listOfPossibleLocations + ":" + tempFinish);
            if (listOfPossibleLocations[i][0] == tempFinish[0]) {
                if (listOfPossibleLocations[i][1] == tempFinish[1]) {
                    existsFinish = true;
                }

            }
        }
        //System.out.println("got here ");
        if(existsStart && existsFinish){//check if entry exists
            //System.out.println("entries exist");
            if (((Math.abs(startRow - finishRow) == 2) && (Math.abs(startColumn - finishColumn) == 2)) || (((Math.abs(startRow - finishRow) == 0) && (Math.abs(startColumn - finishColumn) == 4)))) {
                //System.out.println("math checks out");
                if ((boardFake.boardArray[startRow][startColumn] == 'o') && (boardFake.boardArray[finishRow][finishColumn] == '-')) {
                    //System.out.println("start and finish match ");
                    boolean check = false;
                    if (startRow > finishRow) {
                        if (startColumn > finishColumn) {
                            if (boardFake.boardArray[startRow - 1][startColumn - 1] == 'o') {
                                check = true;
                            }
                        } else {
                            if (boardFake.boardArray[startRow - 1][finishColumn - 1] == 'o') {
                                check = true;
                            }
                        }
                    } else if (startRow==finishRow) {
                        if (startColumn > finishColumn) {
                            if(boardFake.boardArray[finishRow][startColumn - 2] == 'o'){
                                check = true;
                            }
                        } else {
                            if(boardFake.boardArray[finishRow][finishColumn - 2] == 'o'){
                                check = true;
                            }
                        }
                    }else {
                        if (startColumn > finishColumn) {
                            if (boardFake.boardArray[finishRow - 1][startColumn - 1] == 'o') {
                                check = true;
                            }
                        } else {
                            if (boardFake.boardArray[finishRow - 1][finishColumn - 1] == 'o') {
                                check = true;
                            }
                        }
                    }
                    if (check) {
                        //System.out.println("middle worked");
                        return true;
                    }
                }
            }
        }


        return false;
    }
}
