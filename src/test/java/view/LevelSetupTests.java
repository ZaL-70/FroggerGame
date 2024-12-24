package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.nott.cs.comp2013.froggerApp.actors.*;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.LevelSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.World;

public class LevelSetupTests extends ApplicationTest {
    World mockWorld;
    Animal mockAnimal;

    @BeforeEach
    public void setup() {
        mockWorld = new World() {
            @Override
            public void act(long now) { /*No implementation for this test*/ }
        };
        mockAnimal = new Animal("file:src/main/resources/imgs/player/action/froggerUp.png");
    }

    @Test
    public void testCreateAnimal() {
        LevelSetup.createAnimal(mockWorld, mockAnimal);
        // Verify the number of obstacles
        long animalCount = mockWorld.getChildren().stream().filter(node -> node instanceof Animal).count();
        assertEquals(1, animalCount);
    }

    @Test
    public void testCreateEndPoints() {
        LevelSetup.createObstacles(mockWorld);
        // Verify the number of obstacles
        long endPointCount = mockWorld.getChildren().stream().filter(node -> node instanceof End).count();
        assertEquals(5, endPointCount);
    }

    @Test
    public void testCreateLogs() {
        LevelSetup.createObstacles(mockWorld);
        // Verify the number of obstacles
        long logCount = mockWorld.getChildren().stream().filter(node -> node instanceof Log).count();
        assertEquals(8, logCount);
    }

    @Test
    public void testCreateWetTurtles() {
        LevelSetup.createObstacles(mockWorld);
        // Verify the number of obstacles
        long wetTurtleCount = mockWorld.getChildren().stream().filter(node -> node instanceof WetTurtle).count();
        assertEquals(2, wetTurtleCount);
    }

    @Test
    public void testCreateTurtles() {
        LevelSetup.createObstacles(mockWorld);
        // Verify the number of obstacles
        long turtleCount = mockWorld.getChildren().stream().filter(node -> node instanceof Turtle).count();
        assertEquals(4, turtleCount);
    }

    @Test
    public void testCreateObstacles() {
        LevelSetup.createObstacles(mockWorld);
        // Verify the number of obstacles
        long obstacleCount = mockWorld.getChildren().stream().filter(node -> node instanceof Obstacle).count();
        assertEquals(10, obstacleCount);
    }

}
