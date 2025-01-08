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
 * added to the pane and renders their behavior repeatedly.
 * This class must not be instantiated or accessed and must stay in its own package alongside {@link MyStage},
 * as that should be the main class to use for custom behaviour. Any necessary looping game behaviour can be added
 * here and {@link MyStage} will inherit it
 */
public class World extends Pane {
    private AnimationTimer timer;

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

    public void start() {
        createTimer();
        timer.start();
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void add(GameObject gameObject) {
        getChildren().add(gameObject);
    }

    public <T> void removeInstancesOf(Class<T> clazz) {
        if (getChildren() != null) {
            getChildren().removeIf(clazz::isInstance);
        }
    }

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
