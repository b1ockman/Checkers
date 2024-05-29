package org.bruh.checkers.render;

import org.bruh.checkers.game.Game;
import org.bruh.checkers.render.assets.Asset;
import org.bruh.checkers.render.assets.AssetsManager;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class GameSketch extends PApplet {

    public static final int WIDTH = 497;
    public static final int HEIGHT = 626;
    public static final String TITLE = "Checkers";

    public final Game game;
    public final AssetsManager assetsManager;
    public final BoardRenderer boardRenderer;

    public GameSketch(Game game) {
        this.game = game;
        assetsManager = new AssetsManager(this);
        boardRenderer = new BoardRenderer(this.game.board, this);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        windowTitle(TITLE);
        surface.setIcon(assetsManager.getAsset(Asset.ICON).orElse(null));
    }

    @Override
    public void draw() {
        image(assetsManager.getAsset(Asset.BACK).orElse(null), 0, 0, width, height);
        boardRenderer.render();
        push();
        fill(0);
        text(mouseX + " " + mouseY, 10, 10);
        pop();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (boardRenderer.isColliding(event.getX(), event.getY())) {
            boardRenderer.mousePressed(event);
        }
    }
}
