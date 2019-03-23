package com.frontanilla.estrategaioserver.zones.console.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.firebase.Request;
import com.frontanilla.estrategaioserver.utils.globals.Enums;
import com.frontanilla.estrategaioserver.utils.globals.GlobalConstants;
import com.frontanilla.estrategaioserver.zones.console.ConsoleFirebase;
import com.frontanilla.estrategaioserver.zones.console.ConsoleScreen;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;
import com.frontanilla.estrategaioserver.zones.console.components.map.GridRow;
import com.frontanilla.estrategaioserver.zones.console.logic.helpers.*;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneLogic;

import static com.frontanilla.estrategaioserver.utils.globals.Enums.ConsoleTab.*;
import static com.frontanilla.estrategaioserver.utils.globals.GlobalConstants.FADING_DURATION;
import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.*;

public class ConsoleLogic extends ZoneLogic {

    // For ease of use
    private ConsoleStuff consoleStuff;
    // Helpers
    private AdditionRequestHandler additionRequestHandler;
    private PassTurnRequestHandler passTurnRequestHandler;
    private PlacementRequestHandler placementRequestHandler;
    private MapHandler mapHandler;
    private InputHandler inputHandler;
    // Logic
    private Enums.ConsoleTab currentTab;

    public ConsoleLogic(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initState() {
        consoleStuff = (ConsoleStuff) connector.getStuff();
        ConsoleFirebase consoleFirebase = (ConsoleFirebase) connector.getFirebase();

        initialStuffBehavior(consoleFirebase);
        startListeningToDatabases(consoleFirebase);
    }

    private void initialStuffBehavior(ConsoleFirebase consoleFirebase) {
        // Initialize Stuff - Console Assets Loaded in Splash Zone
        consoleStuff.initStuff();
        // Initialize Helpers
        additionRequestHandler = new AdditionRequestHandler(consoleStuff, consoleFirebase);
        passTurnRequestHandler = new PassTurnRequestHandler();
        placementRequestHandler = new PlacementRequestHandler();
        mapHandler = new MapHandler(this, consoleStuff);
        inputHandler = new InputHandler(this, consoleStuff);
        // Initial Tab
        currentTab = PLAYERS;
        hideRequestLog();
        hideMap();
        // Transition via White Image Fading Out
        consoleStuff.getWhiteImage().fadeOut(FADING_DURATION);
        // Enable Input After Transition
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                connector.getInput().setEnabled(true);
            }
        }, FADING_DURATION);
    }

    private void startListeningToDatabases(ConsoleFirebase consoleFirebase) {
        // Start Listening to Changes in Players
        consoleFirebase.fetchPlayersInRealtime();
        // Start Listening to Changes in the Grid Rows
        consoleFirebase.fetchGridRowsInRealtime();
        // Start Listening to Changes in the Requests
        consoleFirebase.fetchAdditionRequestInRealtime();
        consoleFirebase.fetchPassTurnRequestInRealtime();
        consoleFirebase.fetchPlacementRequestInRealtime();
        // Start Listening to Changes in the Turn
        consoleFirebase.fetchTurnInRealtime();
    }

    @Override
    public void update(float delta) {
        consoleStuff.getWhiteImage().update(delta);
        consoleStuff.getAnimatedBackground().update(delta);
    }

    //----------------------------
    // Called by Console Firebase
    //----------------------------

    public void addPlayer(Player player) {
        consoleStuff.getPlayerList().addPlayer(player);
    }

    public void modifyPlayer(Player player) {
        consoleStuff.getPlayerList().modifyPlayer(player);
    }

    public void removePlayer(Player player) {
        consoleStuff.getPlayerList().removePlayer(player);
    }

    public void updateGridRow(GridRow gridRow) {
        // TODO
    }

    public void addRequest(Request request) {
        consoleStuff.getRequestLog().log(request.getRequestType() + request.getPlayerPhoneID() + request.getData());
        switch (request.getRequestType()) {
            case ADDITION:
                additionRequestHandler.handleRequest(request);
                break;
            case PASS_TURN:
                passTurnRequestHandler.handleRequest(request);
                break;
            case PLACEMENT:
                placementRequestHandler.handleRequest(request);
                break;
        }
    }

    public void turnChanged(int newTurn) {
        // TODO
    }

    //-------------------------
    // Called by Input Handler
    //-------------------------

    public void showRequestLog() {
        consoleStuff.getAnimatedBackground().setColor(GlobalConstants.LIME_ROYAL);
        consoleStuff.getRequestsButton().useOpaqueColor();
        consoleStuff.getRequestLog().setPosition(
                ((ConsoleScreen) connector.getScreen()).getStaticCamera().viewportWidth / 2f - REQUEST_LOG_SIZE / 2f,
                REQUEST_LOG_Y);
        currentTab = REQUESTS;
    }

    public void showPlayerList() {
        consoleStuff.getAnimatedBackground().setColor(Color.ROYAL);
        consoleStuff.getPlayersButton().useOpaqueColor();
        consoleStuff.getPlayerList().setPosition(
                ((ConsoleScreen) connector.getScreen()).getStaticCamera().viewportWidth / 2f - PLAYER_LIST_SIZE / 2f,
                PLAYER_LIST_Y);
        currentTab = PLAYERS;
    }

    public void showMap() {
        consoleStuff.getAnimatedBackground().setColor(GlobalConstants.GOLD_ROYAL);
        consoleStuff.getMapButton().useOpaqueColor();
        consoleStuff.getGrid().setPosition(
                ((ConsoleScreen) connector.getScreen()).getStaticCamera().viewportWidth / 2f - CONSOLE_MAP_SIZE / 2f,
                0);
        currentTab = MAP;
    }

    public void hideRequestLog() {
        consoleStuff.getRequestsButton().useTransparentColor();
        consoleStuff.getRequestLog().setPosition(-5000, -5000);
    }

    public void hidePlayerList() {
        consoleStuff.getPlayersButton().useTransparentColor();
        consoleStuff.getPlayerList().setPosition(-5000, -5000);
    }

    public void hideMap() {
        consoleStuff.getMapButton().useTransparentColor();
        consoleStuff.getGrid().hide();
    }

    //-------------------
    // Getters & Setters
    //-------------------
    // Helpers
    public AdditionRequestHandler getAdditionRequestHandler() {
        return additionRequestHandler;
    }

    public PassTurnRequestHandler getPassTurnRequestHandler() {
        return passTurnRequestHandler;
    }

    public PlacementRequestHandler getPlacementRequestHandler() {
        return placementRequestHandler;
    }

    public MapHandler getMapHandler() {
        return mapHandler;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    // Tab
    public Enums.ConsoleTab getCurrentTab() {
        return currentTab;
    }
}