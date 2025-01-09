package uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

/**
 * Interface for behaviour of different deaths. Updates {@link Animal}
 * state & attributes depending on the type of death
 */
public interface DeathChecker {
    /**
     * Checks for a specific type of death.
     * Update animal state & score attribute accordingly.
     * @param animal Player being managed
     */
    void check(Animal animal);
}
