package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup;

import java.util.List;

public class EndInteraction implements ObjectInteractionHandler {
    @Override
    public void interact(Animal animal) {
        if (!(animal.getIntersectingObjects(End.class).isEmpty()) && !animal.getOnObstacle()) {
            List<End> inter = animal.getIntersectingObjects(End.class);
            if (!animal.getIntersectingObjects(End.class).getFirst().isActivated()) {
                animal.changeScore(50, true);
                animal.incrementStop();
                Animal.MAX_HEIGHT = LevelSetup.BOARD_HEIGHT;
                animal.getIntersectingObjects(End.class).getFirst().setEnd();
                animal.respawn();
            }
        }
    }
}
