package org.bruh.checkers.render.assets;

import org.jetbrains.annotations.NotNull;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.*;

public class AssetsManager {

    private final Map<Asset, Optional<PImage[]>> assets_ = new HashMap<>();
    private final PApplet sketch_;

    public AssetsManager(PApplet sketch) {
        sketch_ = sketch;
    }

    private Optional<PImage[]> loadAsset(@NotNull Asset asset) {
        var image = sketch_.loadImage(asset.getFilepath());
        if (image == null) return Optional.empty();
        if (asset.isTiled()) {
            var width = asset.getTileWidth();
            var height = asset.getTileHeight();
            var images = new ArrayList<PImage>();
            for (int y = 0; y < image.height; y += height) {
                for (int x = 0; x < image.width; x += width) {
                    var tile = image.get(x, y, width, height);
                    images.add(tile);
                }
            }
            return Optional.of(images.toArray(PImage[]::new));
        } else {
            return Optional.of(new PImage[]{image});
        }
    }

    private Optional<PImage[]> getOrLoadAsset(@NotNull Asset asset) {
        return assets_.computeIfAbsent(asset, this::loadAsset);
    }

    public Optional<PImage> getAsset(@NotNull Asset asset) {
        if (asset.isTiled()) throw new IllegalArgumentException();
        return getOrLoadAsset(asset).map(pImages -> pImages[0].copy());
    }

    public Optional<PImage[]> getTiledAsset(@NotNull Asset asset) {
        if (!asset.isTiled()) throw new IllegalArgumentException();
        return getOrLoadAsset(asset).map(pImages -> Arrays.copyOf(pImages, pImages.length));
    }

}