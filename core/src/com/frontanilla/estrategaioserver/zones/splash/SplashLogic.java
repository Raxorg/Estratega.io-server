package com.frontanilla.estrategaioserver.zones.splash;

import com.badlogic.gdx.utils.Timer;
import com.frontanilla.estrategaioserver.core.ServerApp;
import com.frontanilla.estrategaioserver.zones.console.ConsoleAssets;
import com.frontanilla.estrategaioserver.zones.console.ConsoleConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneLogic;

import static com.frontanilla.estrategaioserver.utils.globals.GlobalConstants.FADING_DURATION;

public class SplashLogic extends ZoneLogic {

    // Splash
    private boolean splashAssetsLoaded, splashStuffInitialized;
    // Console
    private ConsoleAssets consoleAssets;
    private boolean consoleAssetsQueued, goToConsole;

    public SplashLogic(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initState() {
        splashAssetsLoaded = false;
        splashStuffInitialized = false;
        consoleAssetsQueued = false;
        goToConsole = false;
        consoleAssets = ((SplashAssets) connector.getAssets()).getConsoleAssets();
        connector.getAssets().queueAssetLoading();
    }

    @Override
    public void update(float delta) {
        if (!splashAssetsLoaded) {
            loadSplashAssets();
        }
        if (splashStuffInitialized) {
            updateComponents(delta);
        }
        // Console Assets
        if (consoleAssetsQueued) {
            if (consoleAssets.getAssetManager().update()) {
                consoleAssets.instantiateAssets();
                queueEnterConsoleZone();
                consoleAssetsQueued = false;
            }
        }
        // For Thread Safety
        if (goToConsole) {
            ServerApp.instance.enterZone(new ConsoleConnector(consoleAssets));
        }
    }

    private void loadSplashAssets() {
        if (connector.getAssets().getAssetManager().update()) {
            connector.getAssets().instantiateAssets();
            splashAssetsLoaded = true;
            // Assets are now Loaded, Initialize Stuff
            setInitialStuffBehavior();
        }
    }

    private void setInitialStuffBehavior() {
        // Get Splash Stuff
        final SplashStuff splashStuff = (SplashStuff) connector.getStuff();
        // Initialize Stuff
        connector.getStuff().initStuff();
        splashStuff.getWhiteImage().fadeOut(FADING_DURATION);
        // Schedule Asset Loading Queuing After the White Image Disappears
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ((SplashAssets) connector.getAssets()).getConsoleAssets().queueAssetLoading();
                consoleAssetsQueued = true;
            }
        }, FADING_DURATION);
        splashStuffInitialized = true;
    }

    private void queueEnterConsoleZone() {
        ((SplashStuff) connector.getStuff()).getWhiteImage().fadeIn(FADING_DURATION);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                goToConsole = true;
            }
        }, FADING_DURATION);
    }

    private void updateComponents(float delta) {
        SplashStuff splashStuff = (SplashStuff) connector.getStuff();
        splashStuff.getWhiteImage().update(delta);
        splashStuff.getLoadingImage().update(delta);
    }
}
