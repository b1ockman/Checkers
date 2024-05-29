package org.bruh.checkers;

import org.bruh.checkers.game.Game;
import org.bruh.checkers.render.GameSketch;
import processing.core.PApplet;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        var sketch = new GameSketch(game);
        var sketchClassName = sketch.getClass().getName();
        String[] sketchArgs = { sketchClassName };
        PApplet.runSketch(sketchArgs, sketch);
    }
}