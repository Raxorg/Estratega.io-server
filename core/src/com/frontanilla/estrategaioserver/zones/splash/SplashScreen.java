package com.frontanilla.estrategaioserver.zones.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneScreen;

import static com.frontanilla.estrategaioserver.zones.splash.SplashConstants.SPLASH_CAMERA_HEIGHT;

public class SplashScreen extends ZoneScreen {

    private OrthographicCamera staticCamera;

    public SplashScreen(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initCameras() {
        float ratio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        // Static Camera
        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(
                false,
                SPLASH_CAMERA_HEIGHT * ratio,
                SPLASH_CAMERA_HEIGHT
        );
    }

    public OrthographicCamera getStaticCamera() {
        return staticCamera;
    }
}
