package com.frontanilla.estrategaioserver.zones.splash;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Align;
import com.frontanilla.estrategaioserver.gui.texts.AdvancedText;
import com.frontanilla.estrategaioserver.gui.images.FadingImage;
import com.frontanilla.estrategaioserver.gui.images.RotatingImage;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneStuff;

import static com.frontanilla.estrategaioserver.zones.splash.SplashConstants.ROTATING_IMAGE_REVOLUTIONS_PER_SECOND;

public class SplashStuff extends ZoneStuff {

    private FadingImage whiteImage;
    private RotatingImage loadingImage;
    private AdvancedText loadingText;

    public SplashStuff(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initStuff() {
        // Get the Assets
        SplashAssets splashAssets = ((SplashAssets) connector.getAssets());
        // Get the Camera
        OrthographicCamera staticCamera = ((SplashScreen) connector.getScreen()).getStaticCamera();
        // White Image
        whiteImage = new FadingImage(0, 0, staticCamera.viewportWidth, staticCamera.viewportHeight);
        whiteImage.setTextureRegion(splashAssets.getPixel());
        // Loading Image
        float loadingImageSize = staticCamera.viewportHeight * 0.5f;
        loadingImage = new RotatingImage(
                staticCamera.viewportWidth / 2f - loadingImageSize / 2f,
                staticCamera.viewportHeight / 2f - loadingImageSize / 2f,
                loadingImageSize,
                loadingImageSize,
                ROTATING_IMAGE_REVOLUTIONS_PER_SECOND,
                true);
        loadingImage.setTextureRegion(splashAssets.getLoading());
        // Loading Text
        splashAssets.getTimesSquare().getData().setScale(2);
        loadingText = new AdvancedText(
                0,
                staticCamera.viewportHeight * 0.15f,
                splashAssets.getTimesSquare(),
                "Loading",
                staticCamera.viewportWidth,
                Align.center,
                true);
    }

    public FadingImage getWhiteImage() {
        return whiteImage;
    }

    public RotatingImage getLoadingImage() {
        return loadingImage;
    }

    public AdvancedText getLoadingText() {
        return loadingText;
    }
}
