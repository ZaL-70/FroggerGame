package actors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.World;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class actorTests {

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

    // Test objects intersecting
    /* Helper function for setting actor locations & mocking world behavior with actors
    * Returns the actor being tested in the mock world */
    private TestActor intersectingActorsHelper(TestActor testActor, double testActorX, double testActorY, double anotherActorX, double anotherActorY) {
        testActor.setX(testActorX);
        testActor.setY(testActorY);
        TestActor anotherActor = spy(new TestActor());
        anotherActor.setX(anotherActorX);
        anotherActor.setY(anotherActorY);
        // Mock the World to act as the Actor's parent
        when(mockWorld.getObjects(TestActor.class)).thenReturn(Arrays.asList(anotherActor, testActor));
        when(anotherActor.getWorld()).thenReturn(mockWorld);
        return anotherActor;
    }
    // Helper function to mock
    @Test
    public void testGetIntersectingObjectsTrue() {
        TestActor anotherActor = intersectingActorsHelper(testActor, 10, 10, 10, 10);
        // Assert
        List<TestActor> intersectingObjects = anotherActor.getIntersectingObjects(TestActor.class);
        assertEquals(1, intersectingObjects.size());
        assertTrue(intersectingObjects.contains(testActor));
    }

    @Test
    public void testGetIntersectingObjectsFalse() {
        TestActor anotherActor = intersectingActorsHelper(testActor, 0, 0, 10, 10);
        // Assert
        List<TestActor> intersectingObjects = anotherActor.getIntersectingObjects(TestActor.class);
        assertEquals(0, intersectingObjects.size());
        assertTrue(intersectingObjects.isEmpty());
    }

    @Test
    public void testGetOneIntersectingObject() {
        TestActor anotherActor = intersectingActorsHelper(testActor, 10,10,10,10);
        Actor intersectingObject = anotherActor.getOneIntersectingObject(TestActor.class);

        assertNotNull(intersectingObject);
        assertEquals(intersectingObject, testActor);
    }

    // Test individual actions (act implementations)
}
