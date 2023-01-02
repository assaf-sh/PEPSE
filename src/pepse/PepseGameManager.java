package pepse;

import danogl.GameManager;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import pepse.world.Sky;
import pepse.world.Terrain;

public class PepseGameManager extends GameManager {
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
        Terrain terrain = new Terrain(this.gameObjects(),Layer.STATIC_OBJECTS,windowDimension,50);
        terrain.createInRange(0,(int)windowDimension.x());
    }
}
