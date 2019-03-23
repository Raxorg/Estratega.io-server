package com.frontanilla.estrategaioserver.zones.splash;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.estrategaioserver.zones.console.ConsoleAssets;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneAssets;

import static com.frontanilla.estrategaioserver.zones.splash.SplashConstants.SPLASH_ATLAS_FILE;
import static com.frontanilla.estrategaioserver.zones.splash.SplashConstants.TIMES_SQUARE_FILE_1;

public class SplashAssets extends ZoneAssets {

    private TextureRegion pixel, loading;
    private BitmapFont timesSquare;
    // Pass to Console Connector
    private ConsoleAssets consoleAssets;

    SplashAssets() {
        consoleAssets = new ConsoleAssets();
    }

    @Override
    public void queueAssetLoading() {
        // Immediately Load Splash Assets
        assetManager = new AssetManager();
        assetManager.load(SPLASH_ATLAS_FILE, Texture.class);
        assetManager.load(TIMES_SQUARE_FILE_1, BitmapFont.class);
        assetManager.finishLoading(); // Special Case, these Assets must be Loaded Blocking the Thread
    }

    @Override
    public void instantiateAssets() {
        // Splash Atlas
        Texture splashAtlas = assetManager.get(SPLASH_ATLAS_FILE, Texture.class);
        pixel = new TextureRegion(splashAtlas, 800, 0, 1, 1);
        loading = new TextureRegion(splashAtlas, 0, 0, 800, 800);
        timesSquare = assetManager.get(TIMES_SQUARE_FILE_1, BitmapFont.class);
    }

    // Getters
    TextureRegion getPixel() {
        return pixel;
    }

    TextureRegion getLoading() {
        return loading;
    }

    BitmapFont getTimesSquare() {
        return timesSquare;
    }

    // Pass to Console Connector
    ConsoleAssets getConsoleAssets() {
        return consoleAssets;
    }
}
