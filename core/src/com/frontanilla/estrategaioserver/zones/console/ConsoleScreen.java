package com.frontanilla.estrategaioserver.zones.console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneScreen;

public class ConsoleScreen extends ZoneScreen {

    private OrthographicCamera staticCamera;

    public ConsoleScreen(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initCameras() {
        float ratio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        // Static Camera
        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(
                false,
                ConsoleConstants.CONSOLE_CAMERA_HEIGHT * ratio,
                ConsoleConstants.CONSOLE_CAMERA_HEIGHT
        );
    }

    public OrthographicCamera getStaticCamera() {
        return staticCamera;
    }
}
