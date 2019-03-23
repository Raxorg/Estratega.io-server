package com.frontanilla.estrategaioserver.zones.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneRenderer;

import static com.frontanilla.estrategaioserver.utils.globals.GlobalConstants.DEBUG;

public class SplashRenderer extends ZoneRenderer {

    private SpriteBatch staticSpriteBatch;
    private ShapeRenderer staticShapeRenderer;

    public SplashRenderer(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initRenderers() {
        staticSpriteBatch = new SpriteBatch();
        staticShapeRenderer = new ShapeRenderer();
        staticShapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void render() {
        // Clear Previous Frame
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Get Splash Stuff
        SplashStuff splashStuff = ((SplashStuff) connector.getStuff());
        // Use the Camera
        staticSpriteBatch.setProjectionMatrix(((SplashScreen) connector.getScreen()).getStaticCamera().combined);
        // Render Stuff
        staticSpriteBatch.begin();
        splashStuff.getLoadingImage().render(staticSpriteBatch);
        splashStuff.getLoadingText().render(staticSpriteBatch);
        splashStuff.getWhiteImage().render(staticSpriteBatch);
        staticSpriteBatch.end();
        if (DEBUG) {
            // Use the Camera
            staticShapeRenderer.setProjectionMatrix(((SplashScreen) connector.getScreen()).getStaticCamera().combined);
            // Render Debug Stuff
            staticShapeRenderer.begin();
            splashStuff.getLoadingImage().renderDebug(staticShapeRenderer);
            splashStuff.getLoadingText().renderDebug(staticShapeRenderer);
            splashStuff.getWhiteImage().renderDebug(staticShapeRenderer);
            staticShapeRenderer.end();
        }
    }
}
