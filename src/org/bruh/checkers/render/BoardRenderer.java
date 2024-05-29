package org.bruh.checkers.render;

import org.bruh.checkers.game.Board;
import org.bruh.checkers.game.Cell;
import org.bruh.checkers.game.Point;
import org.jetbrains.annotations.NotNull;
import processing.core.PConstants;
import processing.event.MouseEvent;

import static org.bruh.checkers.render.GameSketch.HEIGHT;
import static org.bruh.checkers.render.GameSketch.WIDTH;

public class BoardRenderer {

    public static final int CELL_SIZE = 50;

    public final Board board;
    public final GameSketch sketch;

    private final CellRenderer[][] cellRenderers;

    public BoardRenderer(Board board, GameSketch sketch) {
        this.board = board;
        this.sketch = sketch;
        cellRenderers = new CellRenderer[8][8];
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 8; ++x) {
                cellRenderers[y][x] = new CellRenderer(x, y, this, WIDTH / 2 + (x - 4) * CELL_SIZE, HEIGHT / 2 + (y - 4) * CELL_SIZE);
            }
        }
    }

    public void render() {
        sketch.push();
        sketch.translate(WIDTH / 2f - 4 * CELL_SIZE, HEIGHT / 2f - 4 * CELL_SIZE);
        sketch.stroke(0);
        sketch.noFill();
        sketch.square(-1, -1, 8 * CELL_SIZE + 1);
        sketch.noStroke();
        sketch.imageMode(PConstants.CENTER);
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 8; ++x) {
                sketch.push();
                sketch.translate(x * CELL_SIZE, y * CELL_SIZE);
                cellRenderers[y][x].render();
                sketch.pop();
            }
        }
        sketch.pop();
    }

    public boolean isColliding(int x, int y) {
        return x > WIDTH / 2 - 4 * CELL_SIZE && x < WIDTH / 2 + 4 * CELL_SIZE && y > HEIGHT / 2 - 4 * CELL_SIZE && y < HEIGHT / 2 + 4 * CELL_SIZE;
    }

    CellRenderer selected = null;
    Board.CheckerMove[] moves = null;

    public void mousePressed(@NotNull MouseEvent event) {
        CellRenderer target = null;
        for (var row: cellRenderers) {
            for (var cellRenderer: row) {
                if (cellRenderer.isColliding(event.getX(), event.getY())) {
                    target = cellRenderer;
                    break;
                }
            }
        }
        if (target != null && target.highlighted && selected != null && moves != null) {
            Point cellToTake = null;
            for (var move: moves) {
                var pos = move.move();
                if (target.x == pos.x && target.y == pos.y) {
                    cellToTake = move.checkerToTake();
                    break;
                }
            }
            if (cellToTake != null) {
                board.table[cellToTake.y][cellToTake.x] = Cell.EMPTY;
            }
            board.table[target.y][target.x] = board.table[selected.y][selected.x];
            board.table[selected.y][selected.x] = Cell.EMPTY;
            selected = null;
            moves = null;
        }
        for (var row: cellRenderers) {
            for (var cellRenderer: row) {
                cellRenderer.highlighted = false;
            }
        }
        if (target == null) return;
        moves = board.computeMoves(target.x, target.y);
        selected = target;
        for (var move: moves) {
            var pos = move.move();
            cellRenderers[pos.y][pos.x].highlighted = true;
        }
    }

    public GameSketch getSketch() {
        return sketch;
    }

    public Board getBoard() {
        return board;

    }

}
