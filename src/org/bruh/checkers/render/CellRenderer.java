package org.bruh.checkers.render;

import org.bruh.checkers.game.Cell;
import org.bruh.checkers.render.assets.Asset;
import processing.core.PImage;

import static org.bruh.checkers.render.BoardRenderer.CELL_SIZE;

public class CellRenderer {

    private final BoardRenderer boardRenderer;
    public final int x;
    public final int y;
    private final int screenX;
    private final int screenY;
    public boolean highlighted;

    private PImage[] checkersAsset = null;

    public CellRenderer(int x, int y, BoardRenderer boardRenderer, int screenX, int screenY) {
        this.x = x;
        this.y = y;
        this.screenX = screenX;
        this.screenY = screenY;
        this.boardRenderer = boardRenderer;
    }

    public void render() {
        var sketch = boardRenderer.sketch;
        if ((x + y) % 2 == 0) {
            sketch.fill(highlighted ? 255 : 231, 207, 169);
        } else {
            sketch.fill(highlighted ? 255 : 146, 116, 86);
        }
        sketch.square(0, 0, CELL_SIZE);
        var cell = boardRenderer.board.table[y][x];
        if (Cell.isChecker(cell)) {
            if (checkersAsset == null) {
                checkersAsset = boardRenderer.sketch.assetsManager.getTiledAsset(Asset.CHECKERS).orElseThrow();
            }
            sketch.image(checkersAsset[(Cell.isWhite(cell) ? 1 : 3) - (Cell.isKing(cell) ? 1 : 0)],
                    CELL_SIZE / 2f, CELL_SIZE / 2f, 45, 45);
        }
    }

    public boolean isColliding(int x, int y) {
        return x > screenX && x < screenX + CELL_SIZE && y > screenY && y < screenY + CELL_SIZE;
    }

}
