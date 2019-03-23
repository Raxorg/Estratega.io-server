package com.frontanilla.estrategaioserver.zones.console.logic.helpers;

import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.gui.buttons.AdvancedButton;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;

public class InputHandler {

    private ConsoleLogic consoleLogic;
    private ConsoleStuff consoleStuff;

    public InputHandler(ConsoleLogic consoleLogic, ConsoleStuff consoleStuff) {
        this.consoleLogic = consoleLogic;
        this.consoleStuff = consoleStuff;
    }

    public void onDynamicDrag(float x, float y) {
        //consoleLogic.onDynamicDrag(x, y);
    }

    public void onStaticTouchDown(float x, float y) {
        // Check for Requests Button
        AdvancedButton requestsButton = consoleStuff.getRequestsButton();
        if (requestsButton.contains(x, y) && requestsButton.isUsingTransparentColor()) {
            requestsButton.useMiddleColor();
        }
        // Check for Players Button
        AdvancedButton playersButton = consoleStuff.getPlayersButton();
        if (playersButton.contains(x, y) && playersButton.isUsingTransparentColor()) {
            playersButton.useMiddleColor();
        }
        // Check for Map Button
        AdvancedButton mapButton = consoleStuff.getMapButton();
        if (mapButton.contains(x, y) && mapButton.isUsingTransparentColor()) {
            mapButton.useMiddleColor();
        }
    }

    public void onDynamicTouchDown(float x, float y) {

    }

    public void onStaticTouchUp(float x, float y) {
        AdvancedButton requestsButton = consoleStuff.getRequestsButton();
        AdvancedButton playersButton = consoleStuff.getPlayersButton();
        AdvancedButton mapButton = consoleStuff.getMapButton();
        // Check for Requests Button
        if (requestsButton.contains(x, y) && !requestsButton.isUsingOpaqueColor()) {
            consoleLogic.showRequestLog();
            consoleLogic.hidePlayerList();
            consoleLogic.hideMap();
        }
        // Check for Players Button
        if (playersButton.contains(x, y) && !playersButton.isUsingOpaqueColor()) {
            consoleLogic.hideRequestLog();
            consoleLogic.showPlayerList();
            consoleLogic.hideMap();
        }
        // Check for Map Button
        if (mapButton.contains(x, y) && !mapButton.isUsingOpaqueColor()) {
            consoleLogic.hideRequestLog();
            consoleLogic.hidePlayerList();
            consoleLogic.showMap();
        }
    }

    public void onStaticTouchDragged(float x, float y) {
        AdvancedButton[] buttons = {
                consoleStuff.getRequestsButton(),
                consoleStuff.getPlayersButton(),
                consoleStuff.getMapButton()
        };
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].contains(x, y)) {
                if (buttons[i].isUsingTransparentColor()) {
                    buttons[i].useMiddleColor();
                }
            } else {
                if (!buttons[i].isUsingOpaqueColor()) {
                    buttons[i].useTransparentColor();
                }
            }
        }
    }

    public void onDynamicTap(float x, float y, int count) {
        //consoleLogic.onDynamicTap(x, y, count);
    }

    public void onStaticTap(float x, float y, int count) {
    }

    public void onStaticLongPress(float x, float y) {

    }

    public void onStaticFling(float velocityX, float velocityY) {
        if (Math.abs(velocityX) > Math.abs(velocityY) && Math.abs(velocityX) > 1000) {
            if (velocityX > 0) {
                //consoleLogic.getHudLogic().flingRight();
            } else {
                //consoleLogic.getHudLogic().flingLeft();
            }
        } else if (Math.abs(velocityY) > 1000) {
            System.out.println(velocityY);
            //consoleLogic.getHudLogic().onStaticFling(velocityY > 0);
        }
    }

    public void onStaticPan(float x, float y, float deltaX, float deltaY) {
        consoleLogic.getMapHandler().pan(deltaX, deltaY);
    }

    public void onStaticPanStop(float x, float y) {

    }

    public void onStaticZoom(float initialDistance, float distance) {

    }

    public void onStaticPinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        //consoleLogic.getCameraHandler().pinch(initialPointer1, initialPointer2, pointer1, pointer2);
    }

    public void onStaticPinchStop() {
        //consoleLogic.getCameraHandler().pinchStop();
    }
}
