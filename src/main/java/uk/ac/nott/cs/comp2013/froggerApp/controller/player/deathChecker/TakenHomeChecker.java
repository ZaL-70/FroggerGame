package uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker;

import uk.ac.nott.cs.comp2013.froggerApp.model.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

public class TakenHomeChecker implements DeathChecker {
    @Override
    public void check(Animal animal) {
        var ends = animal.getIntersectingObjects(End.class);
        if (!(ends.isEmpty()) && !animal.getOnObstacle()) {
            End end = ends.get(0);
            if (end.isActivated()) {
                animal.setState(Animal.State.endDeath);
            }
        }
    }
}
