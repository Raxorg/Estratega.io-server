package com.frontanilla.estrategaioserver.utils.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.utils.structs.CellableData;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.Cellable;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.buildings.Base;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.buildings.Wall;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.units.Tank;

import java.util.Map;

import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.*;

public class Transform {

    public static Vector2 rotationToVector2(float rotation) {
        return new Vector2(MathUtils.cosDeg(rotation), MathUtils.sinDeg(rotation));
    }

    public static Polygon rectangleToPolygon(float x, float y, float w, float h) {
        Polygon polygon = new Polygon(new float[]{
                0, 0,
                w, 0,
                w, h,
                0, h,
        });
        polygon.setOrigin(w / 2f, h / 2f);
        polygon.setPosition(x, y);
        return polygon;
    }

    public static Polygon rectangleToPolygon(Rectangle rectangle) {
        return rectangleToPolygon(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static Player snapshotDataToPlayer(String phoneID, Map<String, Object> snapshotData) {
        String name = (String) snapshotData.get("name");
        int money = ((Long) snapshotData.get("money")).intValue();
        Color color = stringToColor((String) snapshotData.get("color"));
        int turn = ((Long) snapshotData.get("turn")).intValue();
        return new Player(phoneID, name, color, money, turn);
    }

    public static Color stringToColor(String string) {
        switch (string) {
            case "R":
                return Color.RED;
            case "B":
                return Color.BLUE;
            case "Y":
                return Color.YELLOW;
            case "L":
                return Color.LIME;
            case "O":
                return Color.ORANGE;
            case "P":
                return Color.PURPLE;
            case "C":
                return Color.CYAN;
            case "S":
                return Color.SALMON;
        }
        return null;
    }

    public static Cellable cellableDataToCellable(CellableData cellableData, ConsoleStuff consoleStuff) {
        Cellable cellable;
        switch (cellableData.getRepresentation()) {
            case WALL_REPRESENTATION:
                cellable = new Wall(cellableData.getOwner());
                cellable.setRegionsMap(consoleStuff.getWallRegionsMap());
                break;
            case TANK_REPRESENTATION:
                cellable = new Tank(cellableData.getOwner());
                cellable.setRegionsMap(consoleStuff.getTankRegionsMap());
                break;
            case BASE_REPRESENTATION:
                cellable = new Base(cellableData.getOwner());
                cellable.setRegionsMap(consoleStuff.getBaseRegionsMap());
                break;
            default:
                cellable = null;
        }
        if (cellable != null) {
            cellable.setRepresentation(cellableData.getRepresentation());
            cellable.setHealth(cellableData.getHealth());
            cellable.setRotation(cellableData.getRotation());
        }
        return cellable;
    }
}
