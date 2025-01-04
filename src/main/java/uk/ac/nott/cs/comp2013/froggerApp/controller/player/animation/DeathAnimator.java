package uk.ac.nott.cs.comp2013.froggerApp.controller.player.animation;

import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

public interface DeathAnimator {
    void animate(Animal animal, long now);
}
