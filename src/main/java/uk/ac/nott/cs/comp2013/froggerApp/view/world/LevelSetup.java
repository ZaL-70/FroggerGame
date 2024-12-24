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
        world.add(new digit(0, 30, 360, 25));
        world.start();
        return world;
    }

    public static void createAnimal(World world, Animal animal) {
    }

    private static void createEndPoints(World world) {
    }

    private static void createLogs(World world) {
    }

    private static void createWetTurtles(World world) {
    }

    private static void createTurtles(World world) {
    }

    public static void createObstacles(World world) {
    }
}
