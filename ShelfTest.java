
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ShelfTest.
 *
 * @author  David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class ShelfTest extends junit.framework.TestCase
{
    private Shelf shelf1, shelf100, shelfN3;
    private Thing thing0, thing1, thing3,
            thing6, thing9, thing24, thingN3;

    /**
     * Default constructor for test class ShelfTest
     */
    public ShelfTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    protected void setUp()
    {
        shelf1 = new Shelf(1);
        shelf100 = new Shelf(100);
        shelfN3 = new Shelf(-3);
        thing0 = new Thing("empty", 0);
        thing1 = new Thing("A cup", 1);
        thing3 = new Thing("Set of books", 3);
        thing6 = new Thing("Sports equipment", 6);
        thing9 = new Thing("Washing machine", 9);
        thing24 = new Thing("Bathtub filled with Rubber Ducks", 24);
        thingN3 = new Thing("negative space", -3);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    protected void tearDown()
    {
    }
    
    // Finding tests
    
    /**
     * Searches for an empty space to store an object.
     */
    @Test
    public void testFindEmptySpace()
    {
        // Positive
        assertEquals(1, shelf1.findSpace(thing1));
        // Negative
        assertNotEquals(9, shelf1.findSpace(thing1));
        
        // Make a change
        shelf1.storeThing(1, thing9);
        
        // Positive after changes
        assertEquals(10, shelf1.findSpace(thing1));
        // Negative after changes
        assertNotEquals(1, shelf1.findSpace(thing1));
        // Show all spaces, just to check if it was right
        // shelf1.showThings();
    }
    
    /**
     * Tries to find an empty slot in position 1.
     */
    @Test
    public void testFindSpace1()
    {
        // Positive
        assertEquals(1, shelf1.findSpace(thing1));
        // Negative
        assertNotEquals(9, shelf1.findSpace(thing1));
        
        shelf1.storeThing(2, thing3); // make changes
        // Positive test after change, same place still free
        assertEquals(1, shelf1.findSpace(thing1));
        
        shelf1.storeThing(1, thing1); // make changes
        // Negative test after second change
        assertNotEquals(1, shelf1.findSpace(thing1));
        //shelf1.showThings();
    }
        
    /**
     * Tests if impossibly sized objects can be stored.
     */
    @Test
    public void testFindSpaceImpossible()
    {
        // We expect there is no place for too large objects.
        assertEquals(-1, shelf1.findSpace(thing24)); // Fails, too big, needs guard!
        // We expect there is no place fo negative sized objects.
        assertEquals(-1, shelf1.findSpace(thingN3));
        
        // In reverse, we don't believe these to give a real result.
        assertNotEquals(1, shelf1.findSpace(thing24));
        // If they fail, we have errors to fix in the code.
        assertNotEquals(1, shelf1.findSpace(thingN3));
    }
    
    /**
     * Tests if all places are full.
     */
    @Test
    public void testFindNotFull()
    {
        assertEquals(true, shelf1.storeThing(2, thing9));
        assertEquals(true, shelf1.storeThing(11, thing6));
        assertEquals(17, shelf1.findSpace(thing3)); // pos
        
        // Shelf is full, this should not be possible. As expected
        // Any fail here, would mean, over storing is possible, needs fix.
        assertNotEquals(17, shelf1.findSpace(thing6)); // neg
    }
    
    // Storing tests
    /**
     * Test basic functionality by storing at
     * either end of a shelf, and in the middle.
     */
    @Test
    public void testStoreThreeThings()
    {
        assertEquals(true, shelf1.storeThing(1,  thing1));
        assertEquals(true, shelf1.storeThing(17, thing3));
        assertEquals(true, shelf1.storeThing(14, thing6));
    }
    
    /**
     * Check that double-storing is not permitted.
     */
    @Test
    public void testStoreDouble()
    {
        // Positive, what we expect to happen
        assertEquals(true,  shelf1.storeThing(9, thing1));
        // What we expect NOT to happen
        assertEquals(false, shelf1.storeThing(9, thing3));
    }
    
    /**
     * Try to store in an unexisting space
     */
    @Test
    public void testStoreInvalidSpace()
    {
        // We expect these to fail
        assertEquals(false, shelf1.storeThing(21, thing1));
        assertEquals(false, shelf1.storeThing(-1, thing1));
        
        // Wo don't expect these to succeed
        assertNotEquals(true, shelf1.storeThing(21, thing1));
        assertNotEquals(true, shelf1.storeThing(-1, thing1));
    }
    
    /**
     * Tries to store objects in a full shelf.
     */
    @Test
    public void testStoreFull()
    {
        // Setup, filling the shelf
        assertEquals(true, shelf1.storeThing(1, thing9));
        assertEquals(true, shelf1.storeThing(10, thing6));
        assertEquals(true, shelf1.storeThing(16, thing3));
        
        // Positive
        assertEquals(false, shelf1.storeThing(19, thing3));
        // Negative
        assertEquals(true, shelf1.storeThing(19, thing3));
    }
    
    /**
     * Tries to store objects of impossible size.
     */
    @Test
    public void testStoreImpossibleSizes()
    {
        // Positive, we expect it to NOT be possible
        assertEquals(false, shelf1.storeThing(1, thingN3));
        assertEquals(false, shelf1.storeThing(4, thing24));
        
         // Negative
        assertEquals(true, shelf1.storeThing(1, thingN3));
        assertEquals(true, shelf1.storeThing(4, thing24));
    }
    
    // Getting tests
    /**
     * Tests if the stored thing is still in there.
     */
    @Test
    public void testGetThing()
    {
        shelf1.storeThing(1, thing1);
        // Some assert variations, possible in newer JUnits!
        assertTrue(thing1 == shelf1.getThing(1));
        assertSame(thing1, shelf1.getThing(1));
        assertNull(shelf1.getThing(5));
        
        assertFalse(thing3 == shelf1.getThing(1));
        assertNotNull(shelf1.getThing(1));
        assertNotEquals(thing1, shelf1.getThing(5));
    }
    
    // Shelf number tests
    /**
     * Tests if Shelves can be made of correct numbers
     */
    @Test
    public void testNumberOfShelves()
    {
        assertEquals(1, shelf1.getShelfNumber());
        assertNotEquals(0, shelf1.getShelfNumber());
        assertTrue(shelf1.getShelfNumber()<100);
        assertTrue(shelf1.getShelfNumber()>0);
        assertNotEquals(1.0, shelf1.getShelfNumber());
        
        assertNotEquals(-1, shelf1.getShelfNumber());
        assertNotNull(shelf1.getShelfNumber());
        assertFalse(shelf100.getShelfNumber()>99);
    }
    
    /**
     * Tests if Shelves can be made of wrong numbers
     */
    @Test
    public void testNumberOfImpossibleShelves()
    {
        assertEquals(3, shelfN3.getShelfNumber());
        assertNotEquals(0, shelfN3.getShelfNumber());
        assertNotEquals(3.0, shelfN3.getShelfNumber());
        
        assertNotEquals(-3, shelfN3.getShelfNumber());
        assertFalse(shelf1.getShelfNumber()>100);
        assertFalse(shelf1.getShelfNumber()<0);
    }
}