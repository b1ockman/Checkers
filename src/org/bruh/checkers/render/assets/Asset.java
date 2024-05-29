package org.bruh.checkers.render.assets;
public enum Asset {

    ICON("icon.png"),
    BACK("back.png"),
    CHECKERS("checkers.png", true, 50, 50); // да. Кстати я увидел твой фикс на движение шашек, типо чтоб шашки не двигались назад, и он не работает когда шашка бьёт назад а, брух

    private final String filepath_;
    private final boolean tiled_;
    private final int tileWidth_;
    private final int tileHeight_;

    Asset(String filepath) {
        this(filepath, false, 0, 0);
    }

    Asset(String filepath, boolean tiled, int tileWidth, int tileHeight) {
        filepath_ = filepath;
        tiled_ = tiled;
        tileWidth_ = tileWidth;
        tileHeight_ = tileHeight;
    }

    public String getFilepath() {
        return filepath_;
    }

    public boolean isTiled() {
        return tiled_;
    }

    public int getTileWidth() {
        return tileWidth_;
    }

    public int getTileHeight() {
        return tileHeight_;
    }

}
