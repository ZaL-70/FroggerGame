package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.nott.cs.comp2013.froggerApp.model.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Obstacle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Turtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class LevelSetupTests extends ApplicationTest {
    MyStage testWorld;
    Animal testAnimal;

    @BeforeEach
    public void setup() {
        testWorld = MyStage.getInstance();
        testAnimal = new Animal("file:src/main/resources/imgs/player/action/froggerUp.png");
    }

    @Test
    public void testCreateAnimal() {
        LevelSetup.createAnimal(testWorld, testAnimal);
        // Verify the number of obstacles
        long animalCount = testWorld.getChildren().stream().filter(node -> node instanceof Animal).count();
        assertEquals(1, animalCount);
    }

    @Test
    public void testCreateEndPoints() {
        LevelSetup.createEndPoints(testWorld);
        // Verify the number of obstacles
        long endPointCount = testWorld.getChildren().stream().filter(node -> node instanceof End).count();
        assertEquals(5, endPointCount);
    }

    @Test
    public void testCreateLogs() {
        LevelSetup.createLogs(testWorld);
        // Verify the number of obstacles
        long logCount = testWorld.getChildren().stream().filter(node -> node instanceof Log).count();
        assertEquals(8, logCount);
    }

    @Test
    public void testCreateWetTurtles() {
        LevelSetup.createWetTurtles(testWorld);
        // Verify the number of obstacles
        long wetTurtleCount = testWorld.getChildren().stream().filter(node -> node instanceof WetTurtle).count();
        assertEquals(4, wetTurtleCount);
    }

    @Test
    public void testCreateTurtles() {
        LevelSetup.createTurtles(testWorld);
        // Verify the number of obstacles
        long turtleCount = testWorld.getChildren().stream().filter(node -> node instanceof Turtle).count();
        assertEquals(2, turtleCount);
    }

    @Test
    public void testCreateObstacles() {
        LevelSetup.createObstacles(testWorld);
        // Verify the number of obstacles
        long obstacleCount = testWorld.getChildren().stream().filter(node -> node instanceof Obstacle).count();
        assertEquals(10, obstacleCount);
    }
}
