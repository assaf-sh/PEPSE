package pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.components.Transition.TransitionType;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import java.awt.*;


public class Sun {

    private static float RADIUS;

    public static GameObject create(GameObjectCollection gameObjects,
                                    int layer, Vector2 windowDimensions,
                                    float cycleLength){
        GameObject sun = new GameObject(Vector2.ZERO, new Vector2(200,200),new OvalRenderable(Color.YELLOW));
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag("sun");
        RADIUS = windowDimensions.y()/2f;
        gameObjects.addGameObject(sun,layer);
        Vector2 center = new Vector2(windowDimensions.x()/2,windowDimensions.y()/1.5f);
        Transition<Float> transition = new Transition<>(sun,
                theta -> sun.setCenter(center.add(new Vector2((float) (RADIUS* Math.sin((double) theta)),
                        (float) (RADIUS*Math.cos((double) theta))))),
                -(float)Math.PI,
                (float)Math.PI,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength,
                TransitionType.TRANSITION_LOOP,
                null);
        return sun;
    }
}
