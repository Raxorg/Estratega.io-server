package com.frontanilla.estrategaioserver.zones.console.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.estrategaioserver.zones.console.ConsoleConnector;
import com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants;
import com.frontanilla.estrategaioserver.zones.console.ConsoleScreen;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneInput;

public class ConsoleInput extends ZoneInput {

    // Structure
    private InputMultiplexer inputMultiplexer;
    private AdvancedInput advancedInput;
    // Input
    private OrthographicCamera staticCamera;
    private Vector3 unprojected;

    public ConsoleInput(ConsoleConnector consoleConnector) {
        super(consoleConnector);
        inputMultiplexer = new InputMultiplexer();
        advancedInput = new AdvancedInput(consoleConnector);
    }

    @Override
    public void initConfig() {
        // Configure Advanced Input
        float halfSquareSize = ConsoleConstants.CONSOLE_CAMERA_HEIGHT * 0.05f;
        float tapSecondsInterval = 0.55f;
        float longPressDuration = 1.25f;
        float maxFlingDelay = 0.15f;
        advancedInput.initConfig();
        GestureDetector gestureDetector = new GestureDetector(
                halfSquareSize,
                tapSecondsInterval,
                longPressDuration,
                maxFlingDelay,
                advancedInput
        );
        // Add Processors
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(gestureDetector);
        // Activate Processors
        Gdx.input.setInputProcessor(inputMultiplexer);
        // Initialize Input Related Stuff
        staticCamera = ((ConsoleScreen) connector.getScreen()).getStaticCamera();
        unprojected = new Vector3();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        advancedInput.setEnabled(enabled);
    }

    //---------
    // Android
    //---------

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected = staticCamera.unproject(new Vector3(screenX, screenY, 0));
        ((ConsoleLogic)connector.getLogic()).getInputHandler().onStaticTouchUp(unprojected.x, unprojected.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected = staticCamera.unproject(new Vector3(screenX, screenY, 0));
        ((ConsoleLogic)connector.getLogic()).getInputHandler().onStaticTouchDragged(unprojected.x, unprojected.y);
        return false;
    }
}
