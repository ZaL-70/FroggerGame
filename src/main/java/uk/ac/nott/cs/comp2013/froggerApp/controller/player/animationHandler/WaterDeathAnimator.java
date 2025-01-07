package uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

public class WaterDeathAnimator implements DeathAnimator {
    int death_time = 0;

    @Override
    public void animate(Animal animal, long now) {
        if (animal.getState() == Animal.State.waterDeath) {
            if (now % 11 == 0) {
                death_time++;
            }
            if (death_time == 1) {
                animal.setImage(new Image(GameConfig.PlayerConfig.IMAGE_PATHS.get("waterDeath1"), GameConfig.PlayerConfig.SIZE, GameConfig.PlayerConfig.SIZE, true, true));
            }
            if (death_time == 2) {
                animal.setImage(new Image(GameConfig.PlayerConfig.IMAGE_PATHS.get("waterDeath2"), GameConfig.PlayerConfig.SIZE, GameConfig.PlayerConfig.SIZE, true, true));
            }
            if (death_time == 3) {
                animal.setImage(new Image(GameConfig.PlayerConfig.IMAGE_PATHS.get("waterDeath3"), GameConfig.PlayerConfig.SIZE, GameConfig.PlayerConfig.SIZE, true, true));
            }
            if (death_time == 4) {
                animal.setImage(new Image(GameConfig.PlayerConfig.IMAGE_PATHS.get("waterDeath4"), GameConfig.PlayerConfig.SIZE, GameConfig.PlayerConfig.SIZE, true, true));
            }
            if (death_time == 5) {
                animal.respawn();
                death_time = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
                animal.changeLives(-1,true);
            }
        }
    }
}
