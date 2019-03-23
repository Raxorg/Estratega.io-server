package com.frontanilla.estrategaioserver.zones.console.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;
import com.frontanilla.estrategaioserver.gui.texts.AdvancedText;
import com.frontanilla.estrategaioserver.gui.texts.BackgroundAdvancedText;

public class PlayerList {

    // Core
    private DelayedRemovalArray<Player> players;
    // Rendering
    private TextureRegion textBackground;
    private float margin;
    private BitmapFont font;
    // Components
    private RectangleImage backgroundImage;
    private AdvancedText title;
    private DelayedRemovalArray<BackgroundAdvancedText> playerTexts;

    public PlayerList(float x, float y, float w, float h, TextureRegion textBackground, BitmapFont font) {
        // Core
        players = new DelayedRemovalArray<>();
        // Rendering
        this.textBackground = textBackground;
        margin = (h - 8.5f * (h / 9)) / 2;
        this.font = font;
        // Components
        backgroundImage = new RectangleImage(x, y, w, h);
        title = new AdvancedText(x, y + h - margin, font, "Player List", w, Align.center, false);
        playerTexts = new DelayedRemovalArray<>();
    }

    // Rendering
    public void render(SpriteBatch spriteBatch) {
        backgroundImage.render(spriteBatch);
        title.render(spriteBatch);
        for (int i = 0; i < playerTexts.size; i++) {
            playerTexts.get(i).render(spriteBatch);
        }
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        backgroundImage.renderDebug(shapeRenderer);
        title.renderDebug(shapeRenderer);
        for (int i = 0; i < playerTexts.size; i++) {
            playerTexts.get(i).renderDebug(shapeRenderer);
        }
    }

    public void setPosition(float x, float y) {
        backgroundImage.setPosition(x, y);
        Rectangle bounds = backgroundImage.getBounds();
        title.setPosition(x, y + bounds.height - margin);
        updateTexts();
    }

    // Player Updating
    public void addPlayer(Player player) {
        players.add(player);
        updateTexts();
    }

    public void modifyPlayer(Player player) {
        for (int i = 0; i < players.size; i++) {
            if (players.get(i).getPhoneID().equals(player.getPhoneID())) {
                players.get(i).set(player);
                break;
            }
        }
        updateTexts();
    }

    public void removePlayer(Player player) {
        players.begin();
        players.removeValue(player, false);
        players.end();
        updateTexts();
    }

    private void updateTexts() {
        playerTexts.begin();
        playerTexts.clear();
        Rectangle bounds = backgroundImage.getBounds();
        for (int i = 0; i < players.size; i++) {
            BackgroundAdvancedText newAdvancedText = new BackgroundAdvancedText(
                    bounds.x, bounds.y + bounds.height - (bounds.height / 9) * (i + 1) - margin,
                    font, "", bounds.width, Align.center, false, Color.BLACK.cpy().lerp(Color.CLEAR, 0.35f));
            newAdvancedText.getBackground().setTextureRegion(textBackground);
            newAdvancedText.setText(players.get(i).getName() + " T" +
                    players.get(i).getTurn() + " " +
                    players.get(i).getMoney() + "$");
            newAdvancedText.setColor(players.get(i).getColor());
            playerTexts.add(newAdvancedText);
        }
        playerTexts.end();
    }

    // Getters & Setters
    public DelayedRemovalArray<Player> getPlayers() {
        return players;
    }

    public RectangleImage getBackgroundImage() {
        return backgroundImage;
    }
}
