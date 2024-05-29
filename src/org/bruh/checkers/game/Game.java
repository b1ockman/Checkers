package org.bruh.checkers.game;

public class Game {

    public Board board;

    public int turn = 1;

    public Game() {
        board = new Board();
        board.table[4][5] = Cell.BLACK_KING;
        board.table[5][6] = Cell.WHITE_MAN;
        board.table[1][2] = Cell.BLACK_MAN;
        board.table[6][3] = Cell.WHITE_KING;
    }

    public void makeMove(int x, int y, int tx, int ty) {}

}
