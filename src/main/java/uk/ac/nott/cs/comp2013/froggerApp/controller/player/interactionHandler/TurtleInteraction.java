package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Turtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;

public class TurtleInteraction implements ObjectInteractionHandler {
    @Override
    public void interact(Animal animal) {
        var turtles = animal.getIntersectingObjects(Turtle.class);
        if (!turtles.isEmpty()) {
            animal.move(-TurtleConfig.SPEED_MEDIUM, 0);
        }
    }
}
