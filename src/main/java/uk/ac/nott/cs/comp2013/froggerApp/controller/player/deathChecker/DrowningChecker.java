package uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker;

import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

public class DrowningChecker implements DeathChecker {
    @Override
    public void check(Animal animal) {
        // Check water by turtle death
        var wetTurtles = animal.getIntersectingObjects(WetTurtle.class);
        if (!(wetTurtles.isEmpty())) {
            WetTurtle wetTurtle = wetTurtles.get(0);
            if (wetTurtle.isSunk()) {
                animal.setState(Animal.State.waterDeath);
            }
        }
        // Check water death
        if (animal.getY() < 413) {
            if (!animal.getOnObstacle()) {
                animal.setState(Animal.State.waterDeath);
            }
        }
    }
}
