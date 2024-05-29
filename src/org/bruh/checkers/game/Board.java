package org.bruh.checkers.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    public static final int BOARD_SIZE = 8;
    public static final int PBLE_CLS_CNT = (BOARD_SIZE * BOARD_SIZE) / 2;

    public Color turn = Color.WHITE;

    public static Board starting() {
        var board = new Board();
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                var cell = Cell.EMPTY;
                if ((row + col) % 2 != 0) {
                    if (row < 3) cell = Cell.BLACK_MAN;
                    if (row > 4) cell = Cell.WHITE_MAN;
                }
                board.table[row][col] = cell;
            }
        }
        return board;
    }

    public final Cell[][] table;

    public Board() {
        table = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; ++row) {
            Arrays.fill(table[row], Cell.EMPTY);
        }
    }

    public Board(Cell[][] table) {
        this.table = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; ++row) {
            System.arraycopy(table[row], 0, this.table[row], 0, BOARD_SIZE);
        }
    }

    public record CheckerMove(boolean mustTake, Point checkerToTake, Point move) {

    }

    public CheckerMove[] computeMoves(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) throw new IllegalArgumentException();
        var target = table[y][x];
        if (Cell.isEmpty(target)) return new CheckerMove[]{};
        var moves = new ArrayList<CheckerMove>();
        var takes = new ArrayList<CheckerMove>();
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};
        for (int i = 0; i < 4; ++i) {
            var cx = x + dx[i];
            var cy = y + dy[i];
            var it = 0;
            var take = false;
            Point checkerToTake = null;
            while (cx >= 0 && cx < BOARD_SIZE && cy >= 0 && cy < BOARD_SIZE) {
                ++it;
                if (it > 2 && !Cell.isKing(target)) break;
                var cell = table[cy][cx];
                if (Cell.isEmpty(cell)) {
                    if (take) {
                        takes.add(new CheckerMove(true, checkerToTake, new Point(cx, cy)));
                    } else {
                        if (it == 1 && !Cell.isKing(target)) {
                            if (Cell.isBlack(target) && dy[i] != 1) break;
                            if (Cell.isWhite(target) && dy[i] != -1) break;
                        }
                        moves.add(new CheckerMove(false, null, new Point(cx, cy)));
                    }
                    if (it == 1 && !Cell.isKing(target)) break;
                } else {
                    if (Cell.isSameColor(target, cell)) break;
                    if (take) break;
                    take = true;
                    checkerToTake = new Point(cx, cy);
                }
                cx += dx[i];
                cy += dy[i];
            }
        }
        if (takes.isEmpty()) {
            return moves.toArray(CheckerMove[]::new);
        } else {
            return takes.toArray(CheckerMove[]::new);
        }
    }

    public boolean takeInPath(int x, int y, int tx, int ty) {
        if (x < 0 || x > 7 || y < 0 || y > 7) throw new IllegalArgumentException();
        if (tx < 0 || tx > 7 || ty < 0 || ty > 7) throw new IllegalArgumentException();
        var sx = Integer.signum(tx - x);
        var sy = Integer.signum(ty - y);
        var cx = x;
        var cy = y;
        while (cx != tx - sx && cy != ty - sy) {
            cx += sx;
            cy += sy;
            var cur = table[cy][cx];
            if (Cell.isChecker(cur)) {
                table[cy][cx] = Cell.EMPTY;
                return true;
            }
        }
        return false;
    }

    public boolean moveCheckerTo(int x, int y, int tx, int ty) {
        if (x < 0 || x > 7 || y < 0 || y > 7) throw new IllegalArgumentException();
        if (tx < 0 || tx > 7 || ty < 0 || ty > 7) throw new IllegalArgumentException();
        table[y][x] = table[ty][tx];
        table[ty][tx] = Cell.EMPTY;
        return takeInPath(x, y, tx, ty);
    }

}