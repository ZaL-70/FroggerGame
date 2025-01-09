package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects;

import uk.ac.nott.cs.comp2013.froggerApp.view.world.World;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

/**
 * Model class storing information of a game object and other related
 * game objects it interacts with. Doesn't include movement aspects.
 */
public class GameObject extends ImageView {
    public World getWorld() {
        return (World) getParent();
    }

    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    /**
     * Method returning a list of GameObject classes which intersect with each other
     * @param cls Generic game object type
     * @return List of objects that intersect
     * @param <A> List type & input Game Object type
     */
    public <A extends GameObject> java.util.List<A> getIntersectingObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (A gameObject: getWorld().getObjects(cls)) {
            if (gameObject != this && gameObject.intersects(this.getBoundsInLocal())) {
                someArray.add(gameObject);
            }
        }
        return someArray;
    }
}
