package uk.ac.nott.cs.comp2013.froggerApp.view.world;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * This singleton class contains event listeners for all {@link Actor}
 * on the pane and continually renders them. This class must not be instantiated
 * and must stay in one package alongside {@link MyStage} as that is the main class
 * for custom behaviour. Any core game loop or world content management behaviour
 * can be added here which {@link MyStage} inherit.
 */
public class World extends Pane {
    private AnimationTimer timer;

    /**
     * Default world pane. Listens for inputs from {@link Actor} types
     */
    protected World() {
        sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue != null) {
                    newValue.setOnKeyReleased(event -> {
                        if (getOnKeyReleased() != null) {
                            getOnKeyReleased().handle(event);
                        }
                        List<GameObject> gameObjects = getObjects(GameObject.class);
                        for (GameObject obj : gameObjects) {
                            if (obj.getOnKeyReleased() != null) {
                                obj.getOnKeyReleased().handle(event);
                            }
                        }
                    });

                    newValue.setOnKeyPressed(event -> {
                        if (getOnKeyPressed() != null) {
                            getOnKeyPressed().handle(event);
                        }
                        List<GameObject> gameObjects = getObjects(GameObject.class);
                        for (GameObject obj : gameObjects) {
                            if (obj.getOnKeyPressed() != null) {
                                obj.getOnKeyPressed().handle(event);
                            }
                        }
                    });
                }
            }
        });
    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<GameObject> gameObjects = getObjects(GameObject.class);
                for (GameObject obj : gameObjects) {
                    if (obj instanceof Actor actor) {
                        actor.act(now); // Call act only on Actors
                    }
                }
            }
        };
    }

    /**
     * Instantiates timer to continually render all {@link Actor}
     * game objects & starts keyboard input event listener
     */
    public void start() {
        createTimer();
        timer.start();
    }

    /**
     * Stops timer which renders {@link Actor} game objects
     */
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Adds game objects to the world pane
     * @param gameObject (e.g. logs, player, end point etc)
     */
    public void add(GameObject gameObject) {
        getChildren().add(gameObject);
    }

    /**
     * Removes all instances of a specific game object type from the world pane
     * @param clazz Game object to remove
     * @param <A> Input game object type
     */
    public <A> void removeInstancesOf(Class<A> clazz) {
        if (getChildren() != null) {
            getChildren().removeIf(clazz::isInstance);
        }
    }

    /**
     * Retrieve all game objects of a generic type that exist on the world pane
     * @param cls Game object type to find
     * @return ArrayList of found objects
     * @param <A> Game object type & return list tye
     */
    public <A extends GameObject> List<A> getObjects(Class<A> cls) {
        ArrayList<A> result = new ArrayList<>();
        for (Node n : getChildren()) {
            if (cls.isInstance(n)) {
                result.add(cls.cast(n));
            }
        }
        return result;
    }
}
