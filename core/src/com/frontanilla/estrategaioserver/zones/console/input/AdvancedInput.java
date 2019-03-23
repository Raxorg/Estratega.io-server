package com.frontanilla.estrategaioserver.zones.console.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.estrategaioserver.zones.console.ConsoleConnector;
import com.frontanilla.estrategaioserver.zones.console.ConsoleScreen;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;

public class AdvancedInput implements GestureDetector.GestureListener {

    // Structure
    private ConsoleConnector consoleConnector;
    private OrthographicCamera staticCamera;
    // Input
    private boolean enabled;
    private Vector3 unprojected;


    public AdvancedInput(ConsoleConnector consoleConnector) {
        this.consoleConnector = consoleConnector;
    }

    public void initConfig() {
        staticCamera = ((ConsoleScreen) consoleConnector.getScreen()).getStaticCamera();
        unprojected = new Vector3();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (!enabled || pointer != 0) {
            return false;
        }
        // Static
        unprojected.set(staticCamera.unproject(new Vector3(x, y, 0)));
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticTouchDown(unprojected.x, unprojected.y);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (!enabled) {
            return false;
        }
        // Static
        unprojected.set(staticCamera.unproject(new Vector3(x, y, 0)));
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticTap(unprojected.x, unprojected.y, count);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        if (!enabled) {
            return false;
        }
        unprojected.set(staticCamera.unproject(new Vector3(x, y, 0)));
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticLongPress(unprojected.x, unprojected.y);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (!enabled) {
            return false;
        }
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticFling(velocityX, velocityY);
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (!enabled) {
            return false;
        }
        unprojected.set(staticCamera.unproject(new Vector3(x, y, 0)));
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticPan(unprojected.x, unprojected.y, deltaX, deltaY);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected.set(staticCamera.unproject(new Vector3(x, y, 0)));
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticPanStop(unprojected.x, unprojected.y);
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (!enabled) {
            return false;
        }
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticZoom(initialDistance, distance);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if (!enabled) {
            return false;
        }
        unprojected.set(staticCamera.unproject(new Vector3(initialPointer1, 0)));
        Vector2 temp1 = new Vector2(unprojected.x, unprojected.y);
        unprojected.set(staticCamera.unproject(new Vector3(initialPointer2, 0)));
        Vector2 temp2 = new Vector2(unprojected.x, unprojected.y);
        unprojected.set(staticCamera.unproject(new Vector3(pointer1, 0)));
        Vector2 temp3 = new Vector2(unprojected.x, unprojected.y);
        unprojected.set(staticCamera.unproject(new Vector3(pointer2, 0)));
        Vector2 temp4 = new Vector2(unprojected.x, unprojected.y);
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticPinch(temp1, temp2, temp3, temp4);
        return false;
    }

    @Override
    public void pinchStop() {
        ((ConsoleLogic) consoleConnector.getLogic()).getInputHandler().onStaticPinchStop();
    }

    // Getters & Setters
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
