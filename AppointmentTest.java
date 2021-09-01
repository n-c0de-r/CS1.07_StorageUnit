
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AppointmentTest.
 *
 * @author  n_c0de_r, LanaAlzbibi
 * @version 08.01.2021
 */
public class AppointmentTest
{
    /**
     * Default constructor for test class AppointmentTest
     */
    public AppointmentTest()
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
    public void appointmentDescription()
    {
        Appointment appointm1 = new Appointment("coding", 2);
        assertEquals("coding", appointm1.getDescription());
    }

    @Test
    public void appointmentDuration()
    {
        Appointment appointm1 = new Appointment("coding", 2);
        assertEquals(2, appointm1.getDuration());
    }

    @Test
    public void appointmentTooShort()
    {
        Appointment appointm1 = new Appointment("nothing", 0);
        Day day1 = new Day(1);
        assertEquals(0, appointm1.getDuration());
        day1.showAppointments();
        assertEquals(9, day1.findSpace(appointm1));
    }

    @Test
    public void invalidInput()
    {
        Appointment appointm5 = new Appointment("empty", 0);
        assertEquals(1, appointm5.getDuration());
    }

    @Test
    public void appointmentTooLong()
    {
        Appointment appointm1 = new Appointment("endless", 24);
        Day day1 = new Day(1);
        /*"Fails" instantly, since it cannot be saved
        as the appointment is out of bounds. Without an assertion
        the test is false positive.*/
    }

    @Test
    public void negativeTime()
    {
        Appointment appointm1 = new Appointment("negative length", -1);
        Day day1 = new Day(-1);
        assertEquals(false, day1.makeAppointment(9, appointm1));
    }
}