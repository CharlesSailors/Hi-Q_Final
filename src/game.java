import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class game {

    public game() throws IOException {
        start_game();
    }
    public game(int size) throws IOException {

    }
    static int size;
    public void start_game() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        //System.out.println("Please enter width of bottom row:");
        size = 4; //Integer.parseInt(reader.readLine());

        Board gameBoard = new Board(size);
        //System.out.println("here");
        Bot solver = new Bot(gameBoard);
    }






}
