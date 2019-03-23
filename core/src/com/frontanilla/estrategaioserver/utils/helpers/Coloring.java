package com.frontanilla.estrategaioserver.utils.helpers;

import com.badlogic.gdx.graphics.Color;

public class Coloring {

    public static Color colorWithAlpha(Color color, float alpha) {
        return new Color(color.r, color.g, color.b, alpha);
    }
}
