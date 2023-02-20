/**
 * Record details of a storage thing.
 * 
 * @author  David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Thing
{
    // The description of the thing.
    private String description;
    // The size (in arbitrary units) of the thing.
    private int size;

    /**
     * Create a thing with a given size.
     * @param description   The reason for the thing.
     * @param size          The size of the thing in units.
     */
    public Thing(String description, int size)
    {
        this.description = description;
        this.size = size;
    }

    /**
     * @return  The description of the thing.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return  The size (in hours) of the thing.
     */
    public int getSize()
    {
        return size;
    }
}
