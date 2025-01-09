package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;

/**
 * Abstract model class storing information of a movable {@link GameObject}
 * and other related GameObjects it interacts with.
 */
public abstract class Actor extends GameObject {
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * The action & state of the movable {@link GameObject} to be rendered:
     * @param now Current time to render
     */
    public abstract void act(long now);
}
