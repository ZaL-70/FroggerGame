package uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker;

import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Obstacle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

public class CollisionChecker implements DeathChecker {
    @Override
    public void check(Animal animal) {
        var cars = animal.getIntersectingObjects(Obstacle.class);
        if (!(cars.isEmpty())) {
            animal.setState(Animal.State.carDeath);
        }
    }
}
