package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

public class LogInteraction implements ObjectInteractionHandler {
    @Override
    public void interact(Animal animal) {
        var logs = animal.getIntersectingObjects(Log.class);
        if (!(logs.isEmpty())) {
            Log log = logs.get(0);  // Get the first intersecting log
            if(log.getLeft())
                animal.move(-2,0);
            else
                animal.move(.75,0);
        }
    }
}
