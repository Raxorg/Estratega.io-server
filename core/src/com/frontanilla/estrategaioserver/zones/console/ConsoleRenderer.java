package com.frontanilla.estrategaioserver.zones.console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneRenderer;

import static com.frontanilla.estrategaioserver.utils.globals.GlobalConstants.DEBUG;

public class ConsoleRenderer extends ZoneRenderer {

    private SpriteBatch staticSpriteBatch;
    private ShapeRenderer shapeRenderer;

    ConsoleRenderer(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initRenderers() {
        staticSpriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void render() {
        // Clear Previous Frame
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Get Console Stuff
        ConsoleStuff consoleStuff = ((ConsoleStuff) connector.getStuff());
        // Use the Static Camera
        staticSpriteBatch.setProjectionMatrix(((ConsoleScreen) connector.getScreen()).getStaticCamera().combined);
        // Draw Static Stuff
        staticSpriteBatch.begin();
        consoleStuff.getAnimatedBackground().render(staticSpriteBatch);
        consoleStuff.getPlayerList().render(staticSpriteBatch);
        consoleStuff.getRequestLog().render(staticSpriteBatch);
        consoleStuff.getMapButton().render(staticSpriteBatch);
        consoleStuff.getPlayersButton().render(staticSpriteBatch);
        consoleStuff.getRequestsButton().render(staticSpriteBatch);
        consoleStuff.getGrid().render(staticSpriteBatch);
        consoleStuff.getWhiteImage().render(staticSpriteBatch);
        staticSpriteBatch.end();
        if (DEBUG) {
            // Use the Camera
            shapeRenderer.setProjectionMatrix(((ConsoleScreen) connector.getScreen()).getStaticCamera().combined);
            // Draw Debug Stuff
            shapeRenderer.begin();
            consoleStuff.getAnimatedBackground().renderDebug(shapeRenderer);
            consoleStuff.getPlayerList().renderDebug(shapeRenderer);
            consoleStuff.getRequestLog().renderDebug(shapeRenderer);
            consoleStuff.getMapButton().renderDebug(shapeRenderer);
            consoleStuff.getPlayersButton().renderDebug(shapeRenderer);
            consoleStuff.getRequestsButton().renderDebug(shapeRenderer);
            consoleStuff.getWhiteImage().renderDebug(shapeRenderer);
            shapeRenderer.end();
        }
    }
    // TODO: DEJAR DE USAR CAMARA DINAMICA
}
