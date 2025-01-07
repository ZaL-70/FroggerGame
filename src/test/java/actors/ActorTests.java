package actors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.World;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActorTests {
    private static final double OVERLAPPING_POSITION = 10;
    private static final double BASE_POSITION = 0;
    private static final double NON_OVERLAPPING_POSITION = 100;

    public class TestActor extends Actor {
        @Override
        public void act(long now) { /* No implementation for base class tests */ }
    }
    private TestActor testActor;
    private World mockWorld;

    @BeforeEach
    public void setUp() {
        testActor = spy(new TestActor());
        mockWorld = mock(World.class);
    }

    // Test movements
    @ParameterizedTest
    @CsvSource({
            "10,5,15",
            "10,12.5,22.5",
            "10,-5,5",
            "15,-12.5,2.5",
            "5,-5,0"
    })
    public void testMoveX(double startX, double dx, double endX) {
        testActor.setX(startX);
        testActor.move(dx,0);
        assertEquals(endX, testActor.getX());
    }
    @ParameterizedTest
    @CsvSource({
            "10,5,15",
            "10,12.5,22.5",
            "10,-5,5",
            "15,-12.5,2.5",
            "5,-5,0"
    })
    public void testMoveY(double startY, double dy, double endY) {
        testActor.setY(startY);
        testActor.move(0, dy);
        assertEquals(endY, testActor.getY());
    }

    // Test get world
    @Test
    public void testGetWorld() {
        when(mockWorld.getObjects(TestActor.class)).thenReturn(Arrays.asList(testActor));
        when(testActor.getWorld()).thenReturn(mockWorld);
        assertEquals(mockWorld, testActor.getWorld());
    }

    // Test correct size
    @Test
    public void testGetWidth() {
        testActor.setFitWidth(50);
        assertEquals(50, testActor.getWidth());
    }
    @Test
    public void testGetHeight() {
        testActor.setFitHeight(30);
        assertEquals(30, testActor.getHeight());
    }

    private TestActor createActorAtPosition(double x, double y) {
        TestActor actor = spy(new TestActor());
        actor.setX(x);
        actor.setY(y);
        return actor;
    }

    private void mockWorldWithActors(TestActor... actors) {
        when(mockWorld.getObjects(TestActor.class)).thenReturn(Arrays.asList(actors));
        Arrays.stream(actors).forEach(actor -> when(actor.getWorld()).thenReturn(mockWorld));
    }
    @Test
    public void testGetIntersectingObjectsTrue() {
        TestActor anotherActor = createActorAtPosition(OVERLAPPING_POSITION, OVERLAPPING_POSITION);
        testActor.setX(OVERLAPPING_POSITION);
        testActor.setY(OVERLAPPING_POSITION);
        mockWorldWithActors(anotherActor, testActor);
        assertEquals(1, anotherActor.getIntersectingObjects(TestActor.class).size());
        assertTrue(anotherActor.getIntersectingObjects(TestActor.class).contains(testActor));
    }

    @Test
    public void testGetIntersectingObjectsContainsFalse() {
        TestActor anotherActor = createActorAtPosition(BASE_POSITION, BASE_POSITION);
        testActor.setX(NON_OVERLAPPING_POSITION);
        testActor.setY(NON_OVERLAPPING_POSITION);
        mockWorldWithActors(anotherActor, testActor);
        assertEquals(0, anotherActor.getIntersectingObjects(TestActor.class).size());
        assertFalse(anotherActor.getIntersectingObjects(TestActor.class).contains(testActor));
    }
}
