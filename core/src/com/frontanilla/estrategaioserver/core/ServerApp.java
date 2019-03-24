package com.frontanilla.estrategaioserver.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.frontanilla.estrategaioserver.interfacing.firebase.FirestoreDBInterface;
import com.frontanilla.estrategaioserver.interfacing.firebase.RealtimeDBInterface;
import com.frontanilla.estrategaioserver.interfacing.DebugLoggerInterface;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.splash.SplashConnector;

public class ServerApp extends Game {

    public static ServerApp instance;
    private FirestoreDBInterface firestoreDBInterface;
    private RealtimeDBInterface realtimeDBInterface;
    private DebugLoggerInterface debugLoggerInterface;

    public ServerApp(FirestoreDBInterface firestoreDBInterface, RealtimeDBInterface realtimeDBInterface,
                     DebugLoggerInterface debugLoggerInterface) {
        this.firestoreDBInterface = firestoreDBInterface;
        this.realtimeDBInterface = realtimeDBInterface;
        this.debugLoggerInterface = debugLoggerInterface;
        instance = this;
    }

    @Override
    public void create() {
        // Catch all fucking errors?
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t + " throws exception: " + e);
                e.printStackTrace();
            }
        });

        enterZone(new SplashConnector());
        //resetGrid();
    }

    private void resetGrid() {
        String[] rows = new String[20];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000," +
                    "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000," +
                    "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000," +
                    "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000";
        }
        firestoreDBInterface.saveGridRows(rows, new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                System.out.println("THE GRID HAS BEEN RESET");
                Gdx.app.exit();
            }
        });
    }

    public void enterZone(ZoneConnector zoneConnector) {
        zoneConnector.getInitializer().initialize();
    }

    // Getters & Setters
    public FirestoreDBInterface getFirestoreDBInterface() {
        return firestoreDBInterface;
    }

    public RealtimeDBInterface getRealtimeDBInterface() {
        return realtimeDBInterface;
    }

    public DebugLoggerInterface getDebugLoggerInterface() {
        return debugLoggerInterface;
    }
}
