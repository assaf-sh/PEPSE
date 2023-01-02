package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import pepse.world.Sky;
import pepse.world.Terrain;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;

import java.awt.*;

public class PepseGameManager extends GameManager {
    private static final float DAYNIGHT_CYCLE = 30;
    private static final Color HALO_COLOR = new Color(255, 255, 0, 40);
    private Vector2 windowDimension;
    private UserInputListener inputListener;
    private WindowController windowController;

    public static void main(String[] args){
        new PepseGameManager().run();
    }


    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.windowController = windowController;
        this.inputListener = inputListener;
        windowDimension = windowController.getWindowDimensions();
        Sky.create(this.gameObjects(),windowDimension,Layer.BACKGROUND);
        GameObject sun = Sun.create(this.gameObjects(),Layer.BACKGROUND+1,windowDimension,DAYNIGHT_CYCLE);
        SunHalo.create(this.gameObjects(),Layer.BACKGROUND+2,windowDimension,sun,HALO_COLOR);
        Terrain terrain = new Terrain(this.gameObjects(),Layer.STATIC_OBJECTS,windowDimension,50);
        terrain.createInRange(0,(int)windowDimension.x());
        Night.create(this.gameObjects(),Layer.FOREGROUND,windowDimension,DAYNIGHT_CYCLE);
    }
}
