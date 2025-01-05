package uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

import static uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal.*;

public class HomeTakenAnimator implements DeathAnimator {
    int deathTime = 0;
    @Override
    public void animate(Animal animal, long now) {
        if (animal.getState() == Animal.State.endDeath) {
            if (now % 11 == 0) {
                deathTime++;
            }
            if (deathTime == 1) {
                animal.setImage(new Image(WATER_DEATH1, FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 2) {
                animal.setImage(new Image(WATER_DEATH2, FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 3) {
                animal.setImage(new Image(WATER_DEATH3, FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 4) {
                animal.setImage(new Image(WATER_DEATH4, FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 5) {
                animal.respawn();
                deathTime = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
            }
            System.out.println("End death");
            System.out.println("Death Time: " + deathTime);
            System.out.println("Animal State: " + animal.getState());
        }
    }
}
