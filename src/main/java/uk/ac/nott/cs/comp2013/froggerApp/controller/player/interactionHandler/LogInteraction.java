package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

public class LogInteraction implements ObjectInteractionHandler {
    @Override
    public void interact(Animal animal) {
        var logs = animal.getIntersectingObjects(Log.class);
        if (!logs.isEmpty() && animal.getState() != Animal.State.captured) {
            Log log = logs.get(0);  // Get the first intersecting log
            animal.move(log.getSpeed(),0);
        }
    }
}
