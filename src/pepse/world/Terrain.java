package pepse.world;

import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.NoiseGenerator;

import java.awt.*;
import java.util.Random;

public class Terrain {

    Random random;
    private static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    private static final int TERRAIN_DEPTH = 20;

    private final GameObjectCollection gameObjects;
    private final int groundLayer;
    private final float zeroGroundAtX0;
    private final NoiseGenerator noiseGenerator;
    private final Vector2 windowDimensions;
    private float last;

    public Terrain(GameObjectCollection gameObjects,
                   int groundLayer, Vector2 windowDimensions,
                   int seed) {
        this.gameObjects = gameObjects;
        this.groundLayer = groundLayer;
        this.windowDimensions = windowDimensions;
        this.noiseGenerator = new NoiseGenerator();
        this.zeroGroundAtX0 = (float) ((0.65)*windowDimensions.y());
        noiseGenerator.setSeed(zeroGroundAtX0);
        random = new Random(seed);
        last = zeroGroundAtX0;
    }

    public float GroundHeightAt(float x) {
        if (x == 0) {
            return zeroGroundAtX0;
        }
        float next = (float) random.nextGaussian(last, random.nextDouble(10,30));
        last = next;
        return last;
    }

    public void createInRange(int minX, int maxX) {
        int firstBlockX = minX - (minX % Block.SIZE);
        int lastBlockX = maxX - (maxX % Block.SIZE);
        for (int blockX = firstBlockX; blockX <= lastBlockX; blockX+= Block.SIZE) {
            int y = (int) Math.floor((float) GroundHeightAt(blockX)/Block.SIZE)*Block.SIZE;
            y = y;
            for (int j = 0; j < TERRAIN_DEPTH; j++) {
                if (y+(j*Block.SIZE) > windowDimensions.y()) break;
                crateBlock(new Vector2(blockX,y+(j*Block.SIZE)));
            }
        }
    }


    private void crateBlock(Vector2 topLeftCorner){
        Block block = new Block( topLeftCorner,
                new RectangleRenderable(ColorSupplier.approximateColor(BASE_GROUND_COLOR)));
        block.setTag("ground");
        gameObjects.addGameObject(block);
    }
}
