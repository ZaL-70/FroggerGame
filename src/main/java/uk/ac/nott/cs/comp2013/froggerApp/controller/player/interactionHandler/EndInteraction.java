package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

public class EndInteraction implements ObjectInteractionHandler {
    @Override
    public void interact(Animal animal) {
        if (!(animal.getIntersectingObjects(End.class).isEmpty()) && !animal.getOnObstacle()) {
            if (!animal.getIntersectingObjects(End.class).getFirst().isActivated()) {
                animal.changeScore(50, true);
                animal.incrementStop();
                animal.setMaxHeight(BoardConfig.HEIGHT);
                animal.getIntersectingObjects(End.class).getFirst().setEnd();
                animal.respawn();
            }
        }
    }
}
