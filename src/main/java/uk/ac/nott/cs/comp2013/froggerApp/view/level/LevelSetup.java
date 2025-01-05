package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import uk.ac.nott.cs.comp2013.froggerApp.model.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories.LogFactory;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories.ObstacleFactory;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories.TurtleFactory;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories.WetTurtleFactory;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.World;

public class LevelSetup {
    public static final String LOG3_FILEPATH = "file:src/main/resources/imgs/obstacle/log3.png";
    public static final String LOGS_FILEPATH = "file:src/main/resources/imgs/obstacle/logs.png";
    public static final String CAR1_RIGHT_FILEPATH = "file:src/main/resources/imgs/obstacle/car1Right.png";
    public static final String CAR1_LEFT_FILEPATH = "file:src/main/resources/imgs/obstacle/car1Left.png";
    public static final String TRUCK1_RIGHT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck1Right.png";
    public static final String TRUCK1_LEFT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck1Left.png";
    public static final String TRUCK2_RIGHT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck2Right.png";
    public static final String TRUCK2_LEFT_FILEPATH = "file:src/main/resources/imgs/obstacle/truck2Left.png";
    public static final String BACKGROUND_IMAGE = "file:src/main/resources/imgs/world/frog-background2.png";
    public static final double BOARD_HEIGHT = 800;
    public static final double BOARD_WIDTH = 600;
    public static final double BASE_Y = 166;
    public static final double TOTAL_ROWS = 12;
    public static final double ROW_HEIGHT = (BOARD_HEIGHT - BASE_Y) / TOTAL_ROWS;

    MyStage world = MyStage.getInstance();

    public static double rowToY(int row) {
        return BASE_Y + ((TOTAL_ROWS - row) * ROW_HEIGHT);
    }

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
        int ypos = 96, initialX = 13, gap = 128;
        for (int i = 0; i < 5; i++) {
            world.add(new End(initialX + i * gap, ypos));
        }
    }

    public static void createLogs(World world) {
        // Row 5 logs, gap 220, right, slow
        world.add(LogFactory.createLog(LOG3_FILEPATH, 150, 0, 12, 0.75));
        world.add(LogFactory.createLog(LOG3_FILEPATH, 150, 220, 12, 0.75));
        world.add(LogFactory.createLog(LOG3_FILEPATH, 150, 440, 12, 0.75));
        // Row 3 logs, gap 400, left fast
        world.add(LogFactory.createLog(LOGS_FILEPATH, 300, 0, 10, -2));
        world.add(LogFactory.createLog(LOGS_FILEPATH, 300, 400, 10, -2));
        // Row 2 logs, gap 220, right, slow
        world.add(LogFactory.createLog(LOG3_FILEPATH, 150, 50, 9, 0.75));
        world.add(LogFactory.createLog(LOG3_FILEPATH, 150, 270, 9, 0.75));
        world.add(LogFactory.createLog(LOG3_FILEPATH, 150, 490, 9, 0.75));
    }

    public static void createWetTurtles(World world) {
        // Row 4 wet turtles, gap 200, left, medium
        world.add(WetTurtleFactory.createWetTurtle(130,200,11,-1));
        world.add(WetTurtleFactory.createWetTurtle(130,400,11,-1));
        world.add(WetTurtleFactory.createWetTurtle(130,600,11,-1));
        // Row 1 wet turtle, left, medium
        world.add(WetTurtleFactory.createWetTurtle(130,700,8,-1));
    }

    public static void createTurtles(World world) {
        // Row 1 turtles, gap 200, left medium
        world.add(TurtleFactory.createTurtle(130,300,8,-1));
        world.add(TurtleFactory.createTurtle(130,500,8,-1));
    }

    public static void createObstacles(World world) {
        // Row 1 trucks, gap 300, right, medium
        world.add(ObstacleFactory.createObstacle(TRUCK1_RIGHT_FILEPATH, 120,0,3,1));
        world.add(ObstacleFactory.createObstacle(TRUCK1_RIGHT_FILEPATH, 120,300,3,1));
        world.add(ObstacleFactory.createObstacle(TRUCK1_RIGHT_FILEPATH, 120,600,3,1));
        // Row 2 cars, gap 150, left, medium
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT_FILEPATH, 50,100,4, -1));
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT_FILEPATH, 50,250,4, -1));
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT_FILEPATH, 50,400,4, -1));
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT_FILEPATH, 50,550 ,4, -1));
        // Row 3 trucks, gap 500, right, medium
        world.add(ObstacleFactory.createObstacle(TRUCK2_RIGHT_FILEPATH,200,0,5,1));
        world.add(ObstacleFactory.createObstacle(TRUCK2_RIGHT_FILEPATH,200,500,5,1));
        // Row 4 car, left, superfast
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT_FILEPATH,50,500,6,-5));
    }
}