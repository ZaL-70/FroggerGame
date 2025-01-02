package uk.ac.nott.cs.comp2013.froggerApp.view.world;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.Digit;

import java.util.ArrayList;
import java.util.List;

public class World extends Pane {
    private static World instance;
    private AnimationTimer t;
    
    protected World() {
        if (instance != null) {
            throw new RuntimeException("An instance of class: World already exists");
        }
        sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyReleased() != null) {
                                getOnKeyReleased().handle(event);
                            }
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}
					});
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyPressed() != null) {
                                getOnKeyPressed().handle(event);
                            }
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}
					});
				}
			}
		});
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public void createTimer() {
        t = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<Actor> actors = getObjects(Actor.class);
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
            }
        };
    }

    public void start() {
    	createTimer();
        t.start();
    }

    public void stop() {
        t.stop();
    }
    
    public void add(Actor actor) {
        getChildren().add(actor);
    }

    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add(cls.cast(n));
            }
        }
        return someArray;
    }
}
