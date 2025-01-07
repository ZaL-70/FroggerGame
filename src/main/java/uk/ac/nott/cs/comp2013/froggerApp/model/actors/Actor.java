package uk.ac.nott.cs.comp2013.froggerApp.model.actors;

import uk.ac.nott.cs.comp2013.froggerApp.model.GameObject;

public abstract class Actor extends GameObject {
    public void move(double dx, double dy) {
        setX(getX() + dx); setY(getY() + dy);
    }
    public abstract void act(long now);
}
