package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

/**
 * Interface for behaviour of different {@link Actor} interactions. Updates
 * {@link Animal} state & attributes depending on the type of interaction
 */
public interface ObjectInteractionHandler {
    /**
     * Checks for an interaction with a set of related {@link GameObject}.
     * Update animal state & score attribute accordingly.
     * @param animal Player being managed
     */
    void interact(Animal animal);
}
