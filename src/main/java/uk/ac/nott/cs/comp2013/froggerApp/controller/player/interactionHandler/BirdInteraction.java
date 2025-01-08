package uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Bird;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

public class BirdInteraction implements ObjectInteractionHandler{
    @Override
    public void interact(Animal animal) {
        var birds = animal.getIntersectingObjects(Bird.class);
        if (!birds.isEmpty()) {
            Bird bird = birds.get(0);
            animal.move(bird.getSpeedX(),bird.getSpeedY());
            animal.setState(Animal.State.captured);
        } else if (animal.getState() == Animal.State.captured) {
            animal.setState(Animal.State.alive);
        }
    }
}
