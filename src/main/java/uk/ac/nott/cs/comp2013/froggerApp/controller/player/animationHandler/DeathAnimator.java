package uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

/**
 * Interface for handling different types of deaths occurring by checking
 * the {@link Animal} state
 */
public interface DeathAnimator {
    /**
     * Animate the death an {@link Animal} was put into. Update the score, lives
     * & position attributes accordingly
     * @param animal Player death to handle
     * @param now Time to begin the animation
     */
    void animate(Animal animal, long now);
}
