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

    public static GameObject create(GameObjectCollection gameObjects,
                                    int layer, Vector2 windowDimensions,
                                    float cycleLength){
        Vector2 statringlocation = new Vector2(windowDimensions.x()/1.5f, windowDimensions.y()/3);
        GameObject sun = new GameObject(Vector2.ZERO, new Vector2(200,200),new OvalRenderable(Color.YELLOW));
        sun.setCenter(statringlocation);
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag("sun");
        gameObjects.addGameObject(sun,layer);
        Transition<Vector2> transition = new Transition<>(sun,center -> sun.setCenter((Vector2) center),
                statringlocation,
                Vector2.ZERO,
                Transition.LINEAR_INTERPOLATOR_VECTOR,
                cycleLength/2,
                TransitionType.TRANSITION_BACK_AND_FORTH,
                null);
        return sun;
    }
}
