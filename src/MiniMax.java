import org.bruh.checkers.game.Cell;

public class MiniMax {

    public static void main(String[] args) {
        var board = new int[8][8];
    }

    public static int boardAvr(final int[][] board) {
        int avr = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                avr += board[i][j];
            }
        }
        return avr;
    }

}