package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.factories.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class LevelSetup {
    private static final String LOG1 = LogConfig.IMAGE_PATHS.get("log1");
    private static final String LOG3 = LogConfig.IMAGE_PATHS.get("log3");
    private static final String TRUCK1_RIGHT = ObstacleConfig.IMAGE_PATHS.get("truck1Right");
    private static final String TRUCK2_RIGHT = ObstacleConfig.IMAGE_PATHS.get("truck2Right");
    private static final String CAR1_LEFT = ObstacleConfig.IMAGE_PATHS.get("car1Left");
    private static final double SLOW_SPEED = SpeedConfig.SLOW;
    private static final double MEDIUM_SPEED = SpeedConfig.MEDIUM;
    private static final double FAST_SPEED = SpeedConfig.FAST;
    private static final double SUPERFAST_SPEED = SpeedConfig.SUPERFAST;

    MyStage world;

    public LevelSetup() {
        world = MyStage.getInstance();
    }

    public MyStage createLevel1(Animal animal) {
        world.add(new BackgroundImage(BoardConfig.BACKGROUND_IMAGE));
        createObstacles(world);
        createTurtles(world);
        createWetTurtles(world);
        createLogs(world);
        createEndPoints(world);
        createAnimal(world,animal);

        createLives(world,animal);
        world.add(new Digit(0, 30, 360, 25));
        world.start();

        return world;
    }

    public static void createLives(MyStage world, Animal animal) {
        for(int i = 0; i < LivesConfig.STARTING_LIVES; i++) {
            world.add(new Life(30, LivesConfig.LIFE_PADDING + i * LivesConfig.LIFE_PADDING, 1));
        }
    }

    public static void createAnimal(MyStage world, Animal animal) {
        world.add(animal);
    }

    public static void createEndPoints(MyStage world) {
        int ypos = EndPointConfig.Y_POSITION, initialX = EndPointConfig.INITIAL_X, gap = EndPointConfig.GAP;
        for (int i = 0; i < EndPointConfig.TOTAL_END_POINTS; i++) {
            world.add(new End(initialX + i * gap, ypos));
        }
    }

    public static void createLogs(MyStage world) {
        final int LONG_LOG_SIZE = LogConfig.SIZE_LONG, SHORT_LOG_SIZE = LogConfig.SIZE_SHORT;
        // Row 5 logs, gap 220, right, slow
        world.add(LogFactory.createLog(LOG3, SHORT_LOG_SIZE, 0, 12, SLOW_SPEED));
        world.add(LogFactory.createLog(LOG3, SHORT_LOG_SIZE, 220, 12, SLOW_SPEED));
        world.add(LogFactory.createLog(LOG3, SHORT_LOG_SIZE, 440, 12, SLOW_SPEED));
        // Row 3 logs, gap 400, left fast
        world.add(LogFactory.createLog(LOG1, LONG_LOG_SIZE, 0, 10, -FAST_SPEED));
        world.add(LogFactory.createLog(LOG1, LONG_LOG_SIZE, 400, 10, -FAST_SPEED));
        // Row 2 logs, gap 220, right, slow
        world.add(LogFactory.createLog(LOG3, SHORT_LOG_SIZE, 50, 9, SLOW_SPEED));
        world.add(LogFactory.createLog(LOG3, SHORT_LOG_SIZE, 270, 9, SLOW_SPEED));
        world.add(LogFactory.createLog(LOG3, SHORT_LOG_SIZE, 490, 9, SLOW_SPEED));
    }

    public static void createWetTurtles(MyStage world) {
        final int TURTLE_SIZE = TurtleConfig.SIZE;
        // Row 4 wet turtles, gap 200, left, medium
        world.add(WetTurtleFactory.createWetTurtle(TURTLE_SIZE,200,11,-MEDIUM_SPEED));
        world.add(WetTurtleFactory.createWetTurtle(TURTLE_SIZE,400,11,-MEDIUM_SPEED));
        world.add(WetTurtleFactory.createWetTurtle(TURTLE_SIZE,600,11,-MEDIUM_SPEED));
        // Row 1 wet turtle, left, medium
        world.add(WetTurtleFactory.createWetTurtle(TURTLE_SIZE,700,8,-MEDIUM_SPEED));
    }

    public static void createTurtles(MyStage world) {
        final int WET_TURTLE_SIZE = TurtleConfig.SIZE;
        // Row 1 turtles, gap 200, left medium
        world.add(TurtleFactory.createTurtle(WET_TURTLE_SIZE,300,8,-MEDIUM_SPEED));
        world.add(TurtleFactory.createTurtle(WET_TURTLE_SIZE,500,8,-MEDIUM_SPEED));
    }

    public static void createObstacles(MyStage world) {
        final int CAR_SIZE = ObstacleConfig.CAR_SIZE;
        final int LONG_TRUCK_SIZE = ObstacleConfig.LONG_TRUCK_SIZE, SHORT_TRUCK_SIZE = ObstacleConfig.SHORT_TRUCK_SIZE;
        // Row 1 trucks, gap 300, right, medium
        world.add(ObstacleFactory.createObstacle(TRUCK1_RIGHT, SHORT_TRUCK_SIZE,0,3, MEDIUM_SPEED));
        world.add(ObstacleFactory.createObstacle(TRUCK1_RIGHT, SHORT_TRUCK_SIZE,300,3, MEDIUM_SPEED));
        world.add(ObstacleFactory.createObstacle(TRUCK1_RIGHT, SHORT_TRUCK_SIZE,500,3, MEDIUM_SPEED));
        // Row 2 cars, gap 150, left, medium
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT, CAR_SIZE,100,4, -MEDIUM_SPEED));
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT, CAR_SIZE,250,4, -MEDIUM_SPEED));
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT, CAR_SIZE,400,4, -MEDIUM_SPEED));
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT, CAR_SIZE,550 ,4, -MEDIUM_SPEED));
        // Row 3 trucks, gap 500, right, medium
        world.add(ObstacleFactory.createObstacle(TRUCK2_RIGHT,LONG_TRUCK_SIZE,0,5, MEDIUM_SPEED));
        world.add(ObstacleFactory.createObstacle(TRUCK2_RIGHT,LONG_TRUCK_SIZE,500,5, MEDIUM_SPEED));
        // Row 4 car, left, superfast
        world.add(ObstacleFactory.createObstacle(CAR1_LEFT,CAR_SIZE,500,6, -SUPERFAST_SPEED));
    }

    public static void createEagles(MyStage world) {
        world.add(BirdFactory.createBirdTopLeft(BirdConfig.SIZE, SpeedConfig.FAST));
        world.add(BirdFactory.createBirdLeft(BirdConfig.SIZE, 600, BoardConfig.rowToY(2), -2.25 * SpeedConfig.MEDIUM, -SpeedConfig.MEDIUM));
    }
}