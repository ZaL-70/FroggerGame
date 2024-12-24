package uk.ac.nott.cs.comp2013.froggerApp.view.world;

import uk.ac.nott.cs.comp2013.froggerApp.actors.*;

public class LevelSetup {
    private static final String LOG3_FILEPATH = "file:src/main/resources/imgs/obstacle/log3.png";
    private static final String LOGS_FILEPATH = "file:src/main/resources/imgs/obstacle/logs.png";
    private static final String CAR1_RIGHT_FILEPATH = "file:src/main/resources/imgs/obstacle/car1Right.png";
    private static final String CAR1_LEFT_FILEPATH = "file:src/main/resources/imgs/obstacle/car1Left.png";
    private static final String TRUCK1_RIGHT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck1Right.png";
    private static final String TRUCK1_LEFT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck1Left.png";
    private static final String TRUCK2_RIGHT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck2Right.png";
    private static final String TRUCK2_LEFT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck2Left.png";
    private static final String BACKGROUND_IMAGE = "file:src/main/resources/imgs/world/frog-background.png";
    MyStage world = new MyStage();

    public MyStage createLevel1(Animal animal) {
        world.add(new BackgroundImage(BACKGROUND_IMAGE));
        createObstacles(world);
        createTurtles(world);
        createWetTurtles(world);
        createLogs(world);
        createEndPoints(world);
        createAnimal(world,animal);

        world.add(new Digit(0, 30, 360, 25));
        world.start();

        return world;
    }

    public static void createAnimal(World world, Animal animal) {
        world.add(animal);
    }

    public static void createEndPoints(World world) {
        world.add(new End(13,96));
        world.add(new End(141,96));
        world.add(new End(141 + 141-13,96));
        world.add(new End(141 + 141-13+141-13+1,96));
        world.add(new End(141 + 141-13+141-13+141-13+3,96));
    }

    public static void createLogs(World world) {
        world.add(new Log(LOG3_FILEPATH, 150, 0, 166, 0.75));
        world.add(new Log(LOG3_FILEPATH, 150, 220, 166, 0.75));
        world.add(new Log(LOG3_FILEPATH, 150, 440, 166, 0.75));
        world.add(new Log(LOGS_FILEPATH, 300, 0, 276, -2));
        world.add(new Log(LOGS_FILEPATH, 300, 400, 276, -2));
        world.add(new Log(LOG3_FILEPATH, 150, 50, 329, 0.75));
        world.add(new Log(LOG3_FILEPATH, 150, 270, 329, 0.75));
        world.add(new Log(LOG3_FILEPATH, 150, 490, 329, 0.75));
    }

    public static void createWetTurtles(World world) {
        world.add(new WetTurtle(700, 376, -1, 130, 130));
        world.add(new WetTurtle(600, 217, -1, 130, 130));
        world.add(new WetTurtle(400, 217, -1, 130, 130));
        world.add(new WetTurtle(200, 217, -1, 130, 130));
    }

    public static void createTurtles(World world) {
        world.add(new Turtle(500, 376, -1, 130, 130));
        world.add(new Turtle(300, 376, -1, 130, 130));
    }

    public static void createObstacles(World world) {
        world.add(new Obstacle(TRUCK1_RIGHT_FILEPATH, 0, 649, 1, 120, 120));
        world.add(new Obstacle(TRUCK1_RIGHT_FILEPATH, 300, 649, 1, 120, 120));
        world.add(new Obstacle(TRUCK1_RIGHT_FILEPATH, 600, 649, 1, 120, 120));
        world.add(new Obstacle(CAR1_LEFT_FILEPATH, 100, 597, -1, 50, 50));
        world.add(new Obstacle(CAR1_LEFT_FILEPATH, 250, 597, -1, 50, 50));
        world.add(new Obstacle(CAR1_LEFT_FILEPATH, 400, 597, -1, 50, 50));
        world.add(new Obstacle(CAR1_LEFT_FILEPATH, 550, 597, -1, 50, 50));
        world.add(new Obstacle(TRUCK2_RIGHT_FILEPATH, 0, 540, 1, 200, 200));
        world.add(new Obstacle(TRUCK2_RIGHT_FILEPATH, 500, 540, 1, 200, 200));
        world.add(new Obstacle(CAR1_LEFT_FILEPATH, 500, 490, -5, 50, 50));
    }
}