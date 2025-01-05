package uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

import static uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal.*;

public class CarDeathAnimator implements DeathAnimator {
    int death_time = 0;

    @Override
    public void animate(Animal animal, long now) {
        State state = animal.getState();
        if (state == Animal.State.carDeath) {
            if (now % 11 == 0) {
                death_time++;
            }
            if (death_time == 1) {
                animal.setImage(new Image(PlayerConfig.IMAGE_PATHS.get("carDeath1"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true));
            }
            if (death_time == 2) {
                animal.setImage(new Image(PlayerConfig.IMAGE_PATHS.get("carDeath2"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true));
            }
            if (death_time == 3) {
                animal.setImage(new Image(PlayerConfig.IMAGE_PATHS.get("carDeath3"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true));
            }
            if (death_time == 4) {
                animal.respawn();
                death_time = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
            }
        }
    }
}
