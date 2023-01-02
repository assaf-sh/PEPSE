package pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.*;

public class SunHalo {

    public static GameObject create(GameObjectCollection gameObjects,
                                    int layer, Vector2 windowDimensions,
                                    GameObject sun,
                                    Color color){
        Renderable circle = new OvalRenderable(color);
        Vector2 startingLocation = new Vector2(windowDimensions.x()/1.5f, windowDimensions.y()/3);
        GameObject sunHalo = new GameObject(Vector2.ZERO,new Vector2(300,300),circle);
        sunHalo.setCenter(startingLocation);
        sunHalo.setTag("sun halo");
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects.addGameObject(sunHalo,layer);
        sunHalo.addComponent(delta -> sunHalo.setCenter(sun.getCenter()));
        return sunHalo;
    }
}
