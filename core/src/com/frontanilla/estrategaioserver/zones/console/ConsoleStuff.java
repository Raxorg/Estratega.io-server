package com.frontanilla.estrategaioserver.zones.console;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.estrategaioserver.gui.buttons.AdvancedButton;
import com.frontanilla.estrategaioserver.gui.fancy.AnimatedBackground;
import com.frontanilla.estrategaioserver.gui.images.FadingImage;
import com.frontanilla.estrategaioserver.zones.console.components.PlayerList;
import com.frontanilla.estrategaioserver.zones.console.components.RequestLog;
import com.frontanilla.estrategaioserver.zones.console.components.map.GridRepresentation;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneStuff;

import java.util.HashMap;
import java.util.Map;

import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.*;

public class ConsoleStuff extends ZoneStuff {

    private FadingImage whiteImage;
    private AnimatedBackground animatedBackground;
    private GridRepresentation gridRepresentation;
    private PlayerList playerList;
    private RequestLog requestLog;
    private AdvancedButton requestsButton, playersButton, mapButton;
    // Cellable Regions Maps
    private Map<Integer, TextureRegion> tankRegionsMap, wallRegionsMap, baseRegionsMap;

    ConsoleStuff(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initStuff() {
        OrthographicCamera staticCamera = ((ConsoleScreen) connector.getScreen()).getStaticCamera();
        ConsoleAssets consoleAssets = (ConsoleAssets) connector.getAssets();
        // Fading White Image
        whiteImage = new FadingImage(0, 0, staticCamera.viewportWidth, staticCamera.viewportHeight);
        whiteImage.setTextureRegion(consoleAssets.getPixel());
        // Animated Background
        animatedBackground = new AnimatedBackground(
                0, 0, staticCamera.viewportWidth, staticCamera.viewportHeight,
                Color.ROYAL, Color.ROYAL.cpy().mul(Color.GRAY),
                consoleAssets.getSquare(), consoleAssets.getPixel(),
                staticCamera,
                11,
                50f);
        initInfoDisplayers(staticCamera, consoleAssets);
        initConsoleButtons(staticCamera, consoleAssets);
        // Cellable Regions Maps
        initCellableRegionsMaps(consoleAssets);
    }

    private void initInfoDisplayers(OrthographicCamera staticCamera, ConsoleAssets consoleAssets) {
        // The Player List
        playerList = new PlayerList(
                staticCamera.viewportWidth / 2f - PLAYER_LIST_SIZE / 2f, PLAYER_LIST_Y,
                PLAYER_LIST_SIZE, PLAYER_LIST_SIZE,
                consoleAssets.getPixel(),
                consoleAssets.getTimesSquare1());
        playerList.getBackgroundImage().setTextureRegion(consoleAssets.getPixel());
        playerList.getBackgroundImage().setColor(Color.BLUE.cpy().lerp(Color.CLEAR, 0.5f));
        // The Request Log
        requestLog = new RequestLog(
                staticCamera.viewportWidth / 2f - REQUEST_LOG_SIZE / 2f, REQUEST_LOG_Y,
                REQUEST_LOG_SIZE, REQUEST_LOG_SIZE,
                consoleAssets.getPixel(),
                consoleAssets.getTimesSquare1(), consoleAssets.getTimesSquare2());
        requestLog.getBackgroundImage().setTextureRegion(consoleAssets.getPixel());
        requestLog.getBackgroundImage().setColor(Color.GREEN.cpy().lerp(Color.CLEAR, 0.5f));
        // The Map
        gridRepresentation = new GridRepresentation(
                consoleAssets.getPixel(), consoleAssets.getPixel(), consoleAssets.getMapButton(),
                staticCamera, consoleAssets.getPixel());
    }

    private void initConsoleButtons(OrthographicCamera staticCamera, ConsoleAssets consoleAssets) {
        // The Requests Button
        requestsButton = new AdvancedButton(
                staticCamera.viewportWidth - CONSOLE_BUTTON_SIZE - 100, 400,
                CONSOLE_BUTTON_SIZE, CONSOLE_BUTTON_SIZE);
        requestsButton.setTextureRegion(consoleAssets.getRequestsButton());
        requestsButton.setColor(Color.LIME);
        // The Players Button
        playersButton = new AdvancedButton(
                staticCamera.viewportWidth - CONSOLE_BUTTON_SIZE - 100, 250,
                CONSOLE_BUTTON_SIZE, CONSOLE_BUTTON_SIZE);
        playersButton.setTextureRegion(consoleAssets.getPlayersButton());
        playersButton.setColor(Color.ROYAL);
        // The Map Button
        mapButton = new AdvancedButton(
                staticCamera.viewportWidth - CONSOLE_BUTTON_SIZE - 100, 100,
                CONSOLE_BUTTON_SIZE, CONSOLE_BUTTON_SIZE);
        mapButton.setTextureRegion(consoleAssets.getMapButton());
        mapButton.setColor(Color.ORANGE);
    }

    private void initCellableRegionsMaps(ConsoleAssets consoleAssets) {
        // Tank
        tankRegionsMap = new HashMap<>();
        tankRegionsMap.put(1, consoleAssets.getTank());
        // Wall
        wallRegionsMap = new HashMap<>();
        wallRegionsMap.put(3, consoleAssets.getWall3());
        wallRegionsMap.put(2, consoleAssets.getWall2());
        wallRegionsMap.put(1, consoleAssets.getWall1());
        // Base
        baseRegionsMap = new HashMap<>();
        baseRegionsMap.put(3, consoleAssets.getBase3());
        baseRegionsMap.put(2, consoleAssets.getBase2());
        baseRegionsMap.put(1, consoleAssets.getBase1());
    }

    //---------
    // Getters
    //---------
    public FadingImage getWhiteImage() {
        return whiteImage;
    }

    public AnimatedBackground getAnimatedBackground() {
        return animatedBackground;
    }

    public GridRepresentation getGrid() {
        return gridRepresentation;
    }

    public RequestLog getRequestLog() {
        return requestLog;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public AdvancedButton getRequestsButton() {
        return requestsButton;
    }

    public AdvancedButton getPlayersButton() {
        return playersButton;
    }

    public AdvancedButton getMapButton() {
        return mapButton;
    }

    // Cellable Regions Maps
    public Map<Integer, TextureRegion> getTankRegionsMap() {
        return tankRegionsMap;
    }

    public Map<Integer, TextureRegion> getWallRegionsMap() {
        return wallRegionsMap;
    }

    public Map<Integer, TextureRegion> getBaseRegionsMap() {
        return baseRegionsMap;
    }
}
