/**
 * The test class DayTest.
 *
 * @author  David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class DayTest extends junit.framework.TestCase
{
    private Day day1;
    private Appointment appointm1;
    private Appointment appointm2;
    private Appointment appointm3;
    private Appointment appointm4;
    private Appointment appointm5;

    /**
     * Default constructor for test class DayTest
     */
    public DayTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        day1 = new Day(1);
        appointm1 = new Appointment("learn for Info1", 3);
        appointm2 = new Appointment("take a break", 1);
        appointm3 = new Appointment("write a report", 4);
        appointm4 = new Appointment("take a nap", 2);
        appointm5 = new Appointment("empty", 0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    /**
     * Test basic functionality by booking at either end
     * of a day, and in the middle.
     */
    public void testMakeThreeAppointments()
    {
        Day day1 = new Day(1);
        Appointment appointm1 = new Appointment("Java lecture", 1);
        Appointment appointm2 = new Appointment("Java class", 1);
        Appointment appointm3 = new Appointment("Meet John", 1);
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(true, day1.makeAppointment(13, appointm2));
        assertEquals(true, day1.makeAppointment(17, appointm3));
    }

    /**
     * Check that double-booking is not permitted.
     */
    public void testDoubleBooking()
    {
        Day day1 = new Day(1);
        Appointment appointm1 = new Appointment("Java lecture", 1);
        Appointment appointm2 = new Appointment("Error", 1);
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(false, day1.makeAppointment(9, appointm2));
    }

    public void testFindSpaceEmpty()
    {
        Day day2 = new Day(13);
        Appointment a = new Appointment("First", 1);
        assertEquals(9, day2.findSpace(a));
        day2.showAppointments();
    }

    public void testFindSpace9()
    {
        Day day1 = new Day(13);
        Appointment a = new Appointment("first", 1);
        assertEquals(9, day1.findSpace(a));
        day1.showAppointments();
    }

    public void testNotFull()
    {
        Day day1 = new Day(13);
        Appointment appointm1 = new Appointment("first!", 1);
        assertEquals(true, day1.makeAppointment(11, appointm1));
        Appointment appointm2 = new Appointment("second", 1);
        assertEquals(9, day1.findSpace(appointm2));
        day1.showAppointments();
        Appointment b3 = new Appointment("BIg", 3);
        assertEquals(12, day1.findSpace(b3));
    }

    public void testFindSpace()
    {
        Day day1 = new Day(1);
        Appointment appointm1 = new Appointment("Learning for HTW", 1);
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(10, day1.findSpace(appointm1));
    }

    public void testFindSpaceNull()
    {
        Day day2 = new Day(1);
        Appointment appointm2 = new Appointment("Learning for HTW", 1);
        Appointment appointm3 = new Appointment("have some fun time coding", 3);
        Appointment appointm4 = new Appointment("writing a report", 5);
        assertEquals(true, day2.makeAppointment(9, appointm2));
        assertEquals(true, day2.makeAppointment(10, appointm3));
        assertEquals(true, day2.makeAppointment(13, appointm4));
        assertEquals(-1, day2.findSpace(appointm2));
    }

    public void testDoubleAppointment()
    {
        Day day1 = new Day(1);
        Appointment appointm1 = new Appointment("coffee with friends", 1);
        Appointment appointm2 = new Appointment("breakfast with family", 2);
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(false, day1.makeAppointment(9, appointm2));
    }

    public void testInvalidTime()
    {
        assertEquals(false, day1.makeAppointment(19, appointm1));
    }

    public void testWrongDescription()
    {
        assertNotSame("have a tea", appointm3.getDescription());
    }

    public void testAppointmentsTooshort()
    {
        Day day2 = new Day(2);
    }

    public void testTestBench()
    {
        assertEquals(true, day1.makeAppointment(9, appointm2));
    }

    public void testCreateNegativeDay()
    {
        Day day3 = new Day(-1);
    }
}