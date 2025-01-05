package uk.ac.nott.cs.comp2013.froggerApp.model;

import java.util.HashMap;
import java.util.Map;

public class GameConfig {

    // Configuration for player
    public static class PlayerConfig {
        public static final double MOVEMENT_X = 10.666666*2;
        public static final double MOVEMENT_Y = BoardConfig.ROW_HEIGHT / 2;
        public static final int SIZE = 40;
        public static final double MAX_HEIGHT = 800;
        public static final int RESPAWN_X = 300;
        public static final int RESPAWN_ROW = 2;

        public static final Map<String, String> IMAGE_PATHS = new HashMap<>();
        static {
            IMAGE_PATHS.put("up", "file:src/main/resources/imgs/player/action/froggerUp.png");
            IMAGE_PATHS.put("upJump", "file:src/main/resources/imgs/player/action/froggerUpJump.png");
            IMAGE_PATHS.put("down", "file:src/main/resources/imgs/player/action/froggerDown.png");
            IMAGE_PATHS.put("downJump", "file:src/main/resources/imgs/player/action/froggerDownJump.png");
            IMAGE_PATHS.put("left", "file:src/main/resources/imgs/player/action/froggerLeft.png");
            IMAGE_PATHS.put("leftJump", "file:src/main/resources/imgs/player/action/froggerLeftJump.png");
            IMAGE_PATHS.put("right", "file:src/main/resources/imgs/player/action/froggerRight.png");
            IMAGE_PATHS.put("rightJump", "file:src/main/resources/imgs/player/action/froggerRightJump.png");
            IMAGE_PATHS.put("carDeath1", "file:src/main/resources/imgs/player/death/cardeath1.png");
            IMAGE_PATHS.put("carDeath2", "file:src/main/resources/imgs/player/death/cardeath2.png");
            IMAGE_PATHS.put("carDeath3", "file:src/main/resources/imgs/player/death/cardeath3.png");
            IMAGE_PATHS.put("waterDeath1", "file:src/main/resources/imgs/player/death/waterdeath1.png");
            IMAGE_PATHS.put("waterDeath2", "file:src/main/resources/imgs/player/death/waterdeath2.png");
            IMAGE_PATHS.put("waterDeath3", "file:src/main/resources/imgs/player/death/waterdeath3.png");
            IMAGE_PATHS.put("waterDeath4", "file:src/main/resources/imgs/player/death/waterdeath4.png");
        }
    }

    // Configuration for board
    public static class BoardConfig {
        public static final double WIDTH = 600;
        public static final double HEIGHT = 800;
        public static final double BASE_Y = 166;
        public static final int TOTAL_ROWS = 12;
        public static final double ROW_HEIGHT = (HEIGHT - BASE_Y) / TOTAL_ROWS;
        public static final double UPPER_BOUNDARY = 0;
        public static final double LOWER_BOUNDARY = 734;
        public static final double LEFT_BOUNDARY = 0;
        public static final double RIGHT_BOUNDARY = 575;

        public static double rowToY(int row) {
            return BASE_Y + ((TOTAL_ROWS - row) * ROW_HEIGHT);
        }
        public static final String BACKGROUND_IMAGE = "file:src/main/resources/imgs/world/frog-background2.png";
    }

    public static class SpeedConfig {
        public static final double SLOW = 0.75;
        public static final double MEDIUM = 1;
        public static final double FAST = 2.0;
        public static final double SUPERFAST = 5;
    }

    // Configuration for obstacles
    public static class ObstacleConfig {
        public static final Map<String, String> IMAGE_PATHS = new HashMap<>();
        static {
            IMAGE_PATHS.put("car1Right", "file:src/main/resources/imgs/obstacle/car1Right.png");
            IMAGE_PATHS.put("car1Left", "file:src/main/resources/imgs/obstacle/car1Left.png");
            IMAGE_PATHS.put("truck1Right", "file:src/main/resources/imgs/obstacle/truck1Right.png");
            IMAGE_PATHS.put("truck1Left", "file:src/main/resources/imgs/obstacle/truck1Left.png");
            IMAGE_PATHS.put("truck2Right", "file:src/main/resources/imgs/obstacle/truck2Right.png");
            IMAGE_PATHS.put("truck2Left", "file:src/main/resources/imgs/obstacle/truck2Left.png");
        }
        public static final int LONG_TRUCK_SIZE = 200;
        public static final int SHORT_TRUCK_SIZE = 120;
        public static final int CAR_SIZE = 50;
    }

    // Configuration for logs
    public static class LogConfig {
        public static final Map<String, String> IMAGE_PATHS = new HashMap<>();
        static {
            IMAGE_PATHS.put("log3", "file:src/main/resources/imgs/obstacle/log3.png");
            IMAGE_PATHS.put("log2", "file:src/main/resources/imgs/obstacle/log1.png");
            IMAGE_PATHS.put("log1", "file:src/main/resources/imgs/obstacle/log1.png");
        }
        public static final int SIZE_LONG = 300;
        public static final int SIZE_SHORT = 150;
    }

    // Configuration for turtles
    public static class TurtleConfig {
        public static final Map<String, String> IMAGE_PATHS = new HashMap<>();
        static {
            IMAGE_PATHS.put("wetAnimation3", "file:src/main/resources/imgs/obstacle/TurtleAnimation4Wet.png");
            IMAGE_PATHS.put("wetAnimation2", "file:src/main/resources/imgs/obstacle/TurtleAnimation3Wet.png");
            IMAGE_PATHS.put("wetAnimation1", "file:src/main/resources/imgs/obstacle/TurtleAnimation2Wet.png");
            IMAGE_PATHS.put("animation3", "file:src/main/resources/imgs/obstacle/TurtleAnimation3.png");
            IMAGE_PATHS.put("animation2", "file:src/main/resources/imgs/obstacle/TurtleAnimation2.png");
            IMAGE_PATHS.put("animation1", "file:src/main/resources/imgs/obstacle/TurtleAnimation1.png");
        }
        public static final double SPEED_MEDIUM = 1.0;
        public static final int SIZE = 130;

    }

    // Configuration for end points
    public static class EndPointConfig {
        public static final Map<String, String> IMAGE_PATHS = new HashMap<>();
        static {
            IMAGE_PATHS.put("endPoint", "file:src/main/resources/imgs/world/End.png");
            IMAGE_PATHS.put("frogEnd", "file:src/main/resources/imgs/player/FrogEnd.png");
        }
        public static final int Y_POSITION = 96;
        public static final int INITIAL_X = 13;
        public static final int GAP = 128;
        public static final int TOTAL_END_POINTS = 5;
    }
}
