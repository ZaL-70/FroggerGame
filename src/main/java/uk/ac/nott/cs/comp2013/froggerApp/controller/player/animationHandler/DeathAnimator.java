package uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

public interface DeathAnimator {
    void animate(Animal animal, long now);
}
