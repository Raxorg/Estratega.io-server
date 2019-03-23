package com.frontanilla.estrategaioserver.zones.console;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneAssets;

import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.*;

public class ConsoleAssets extends ZoneAssets {

    // Entities
    private TextureRegion wall3, wall2, wall1, base3, base2, base1, tank;
    // Buttons
    private TextureRegion mapButton, playersButton, requestsButton;
    // Fonts
    private BitmapFont timesSquare1, timesSquare2;
    // Other
    private TextureRegion pixel, square;

    @Override
    public void queueAssetLoading() {
        assetManager = new AssetManager();
        assetManager.load(CONSOLE_ATLAS_FILE, Texture.class);
        assetManager.load(TIMES_SQUARE_FILE_1, BitmapFont.class);
        assetManager.load(TIMES_SQUARE_FILE_2, BitmapFont.class);
    }

    @Override
    public void instantiateAssets() {
        Texture consoleAtlas = assetManager.get(CONSOLE_ATLAS_FILE, Texture.class);
        // Entities
        wall3 = new TextureRegion(consoleAtlas, 0, 600, 15, 15);
        wall2 = new TextureRegion(consoleAtlas, 15, 600, 15, 15);
        wall1 = new TextureRegion(consoleAtlas, 30, 600, 15, 15);
        base3 = new TextureRegion(consoleAtlas, 0, 615, 15, 15);
        base2 = new TextureRegion(consoleAtlas, 15, 615, 15, 15);
        base1 = new TextureRegion(consoleAtlas, 30, 615, 15, 15);
        tank = new TextureRegion(consoleAtlas, 0, 630, 15, 15);
        // Buttons
        mapButton = new TextureRegion(consoleAtlas, 0, 0, 300, 300);
        playersButton = new TextureRegion(consoleAtlas, 300, 0, 300, 300);
        requestsButton = new TextureRegion(consoleAtlas, 0, 300, 300, 300);
        // Fonts
        timesSquare1 = assetManager.get(TIMES_SQUARE_FILE_1, BitmapFont.class);
        timesSquare2 = assetManager.get(TIMES_SQUARE_FILE_2, BitmapFont.class);
        timesSquare2.getData().setScale(0.5f);
        // Other
        pixel = new TextureRegion(consoleAtlas, 600, 0, 1, 1);
        square = new TextureRegion(consoleAtlas, 600, 0, 1, 1);
    }

    // Entities
    public TextureRegion getWall3() {
        return wall3;
    }

    public TextureRegion getWall2() {
        return wall2;
    }

    public TextureRegion getWall1() {
        return wall1;
    }

    public TextureRegion getBase3() {
        return base3;
    }

    public TextureRegion getBase2() {
        return base2;
    }

    public TextureRegion getBase1() {
        return base1;
    }

    public TextureRegion getTank() {
        return tank;
    }

    // Buttons
    public TextureRegion getMapButton() {
        return mapButton;
    }

    public TextureRegion getPlayersButton() {
        return playersButton;
    }

    public TextureRegion getRequestsButton() {
        return requestsButton;
    }

    // Fonts
    public BitmapFont getTimesSquare1() {
        return timesSquare1;
    }

    public BitmapFont getTimesSquare2() {
        return timesSquare2;
    }

    // Other
    public TextureRegion getPixel() {
        return pixel;
    }

    public TextureRegion getSquare() {
        return square;
    }
}