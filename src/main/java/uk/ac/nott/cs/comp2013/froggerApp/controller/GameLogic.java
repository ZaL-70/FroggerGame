package uk.ac.nott.cs.comp2013.froggerApp.controller;

import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.Digit;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.Life;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class GameLogic {
    MyStage world;
    Animal animal;
    int level = 1;

    public GameLogic(MyStage world, Animal animal) {
        this.world = world;
        this.animal = animal;
    }

    public void setNumber(int n) {
        world.removeInstancesOf(Digit.class);
        int shift = 0;
        while (n > 0) {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            world.add(new Digit(k,30, 360-shift, 25));
            shift += 30;
        }
    }

    public void setLives() {
        world.removeInstancesOf(Life.class);
        for(int i = 0; i < animal.getLives(); i++) {
            world.add(new Life(LivesConfig.LIFE_SIZE, LivesConfig.LIFE_PADDING + i * LivesConfig.LIFE_PADDING, 1));
        }
    }

    public boolean handleGameEnd() {
        boolean stop = animal.getStop();
        if (stop) { gameEnd(); showLose(); }
        if (level > 2) { gameEnd(); showWin(); }
        return stop || level > 2;
    }

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

    public void gameEnd() {
        System.out.println("STOPP:");
        world.stopMusic();
        world.stop();
    }
}