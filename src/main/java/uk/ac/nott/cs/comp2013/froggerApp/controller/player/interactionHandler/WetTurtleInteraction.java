package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

public class WetTurtleInteraction implements ObjectInteractionHandler {
    @Override
    public void interact(Animal animal) {
        var wetTurtles = animal.getIntersectingObjects(WetTurtle.class);
        if (!wetTurtles.isEmpty()) {
            WetTurtle wetTurtle = wetTurtles.get(0);
            if (!(wetTurtle.isSunk())) {
                animal.move(wetTurtle.getSpeed(),0);
            }
        }
    }
}
