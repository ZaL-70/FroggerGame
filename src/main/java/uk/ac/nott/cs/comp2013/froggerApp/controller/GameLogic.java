package uk.ac.nott.cs.comp2013.froggerApp.controller;

import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.Digit;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.Life;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

/**
 * Class updating the information displayed by world pane based on current
 * {@link Animal} and {@link MyStage} world state (lives & score)
 */
public class GameLogic {
    MyStage world;
    Animal animal;
    int level = 1;

    public GameLogic(MyStage world, Animal animal) {
        this.world = world;
        this.animal = animal;
    }

    /**
     * Update score displayed on the {@link MyStage} world pane
     * depending on the amount of points an {@link Animal} has
     * @param points the player has
     */
    public void setNumber(int points) {
        world.removeInstancesOf(Digit.class);
        int shift = 0;
        while (points > 0) {
            int d = points / 10;
            int k = points - d * 10;
            points = d;
            world.add(new Digit(k,30, 360-shift, 25));
            shift += 30;
        }
    }

    /**
     * Update the {@link Life} (hearts) displayed on the {@link MyStage}
     * world pane depending on the amount of lives an {@link Animal} has
     */
    public void setLives() {
        world.removeInstancesOf(Life.class);
        for(int i = 0; i < animal.getLives(); i++) {
            world.add(new Life(LivesConfig.LIFE_SIZE, LivesConfig.LIFE_PADDING + i * LivesConfig.LIFE_PADDING, 1));
        }
    }

    /**
     * End game when past Level 2 or lives depleted
     * @return Boolean based on above
     */
    public boolean handleGameEnd() {
        boolean stop = animal.getStop();
        if (stop) { endGame(); showLose(); }
        if (level > 2) { endGame(); showWin(); }
        return stop || level > 2;
    }

    /**
     * Process required for when a level is cleared
     */
    public void handleLevelEnd() {
        boolean end = animal.getEnd();
        if (end && level < 3) {
            animal.setEnd(0); animal.changeLives(
                    1,true);
            level++; LevelSetup.resetEnds();
            LevelSetup.createEndPoints(world);
            LevelSetup.createEagles(world);
        }
    }

    public void showWin() {
        world.createWinScreen(animal.getPoints());
    }

    public void showLose() {
        world.createGameOverScreen(animal.getPoints());
    }

    public void endGame() {
        System.out.println("STOPP:");
        world.stopMusic();
        world.stop();
    }
}