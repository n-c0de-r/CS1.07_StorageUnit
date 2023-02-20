
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ThingTest.
 *
 * @author  n_c0de_r
 * @version 2023.02.07
 */
public class ThingTest extends junit.framework.TestCase
{
    Shelf shelf1;
    Thing normal, empty, huge, negative;
    int maxSize;
    
    /**
     * Default constructor for test class ThingTest
     */
    public ThingTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        normal = new Thing("Box of Peanut Butter", 2);
        empty = new Thing(null, 0);
        huge = new Thing("Huge Truck", 24);
        negative = new Thing("Paradox Parcel", -3);
        maxSize = Shelf.MAX_THINGS_PER_SHELF;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testDescriptionPositive()
    {
        assertEquals("Box of Peanut Butter", normal.getDescription());
        assertNotEquals("Jar of Jelly", normal.getDescription());
        assertNotEquals("", normal.getDescription());
    }
    
    /* Fails, as it is expected for "empty" description to
     * always be at least an empty string not null.
     */
    @Test
    public void testDescriptionNegative()
    {
        assertEquals("", empty.getDescription());
        assertNotEquals(null, empty.getDescription());
        assertNotEquals(null, empty);
    }

    @Test
    public void testSizePostitive()
    {
        assertEquals(2, normal.getSize());
        assertEquals(true, normal.getSize()>0);
        assertEquals(2<maxSize, normal.getSize()<maxSize);
        assertNotEquals(0, normal.getSize());
    }
    
    /* Fails as the item size is expected to be bigger than
     * 0, but as it is possible to have negative sizes, it fails.
     */
    @Test
    public void testSizeNegative()
    {
        assertNotEquals(0, negative.getSize());
        assertNotEquals(true, 0>negative.getSize());
        assertNotEquals(null, negative.getSize());
    }
}