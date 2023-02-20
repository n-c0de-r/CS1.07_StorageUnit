/**
 * Maintain the things for one shelf in a storage.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Shelf
{
    // The first and final available place in a shelf.
    public static final int START_OF_SHELF = 1;
    public static final int FINAL_THING_SPACE = 20;
    // The number of available places in a shelf.
    public static final int MAX_THINGS_PER_SHELF =
                                FINAL_THING_SPACE -
                                START_OF_SHELF + 1;
    
    // A shelf number within a particular storage unit. (1-366)
    private int shelfNumber;
    // The current list of things for this shelf.
    private Thing[] things;

    /**
     * Constructor for objects of class Shelf.
     * @param shelfNumber   The number of this shelf in the storage (1-366).
     */
    public Shelf(int shelfNumber)
    {
        this.shelfNumber = shelfNumber;
        things = new Thing[MAX_THINGS_PER_SHELF];
    }

    /**
     * Try to find space for a thing.
     * @param thing The thing to be accommodated.
     * @return      The first place that can accommodate
     *              the thing. Return -1 if there is 
     *              insufficient space.
     */ 
    public int findSpace(Thing thing)
    {
        int size = thing.getSize();
        for(int slot = 0; slot < MAX_THINGS_PER_SHELF; slot++) {
            if(things[slot] == null) {
                final int place = START_OF_SHELF + slot;
                // Potential start point.
                if(size == 1) {
                    // Only a single slot needed.
                    return place;
                }
                else {
                    // How many more slots are needed?
                    int further_slots_required = size - 1;
                    for(int nextSlot = slot + 1;
                                further_slots_required > 0 &&
                                things[nextSlot] == null;
                                    nextSlot++) {
                        further_slots_required--;
                    }
                    if(further_slots_required == 0) {
                        // A big enough space has been found.
                        return place;
                    }
                }
            }
        }
        // Not enough space available.
        return -1;
    }

    /**
     * Store a thing.
     * @param place The place at which the thing starts.
     * @param thing The thing to be placed.
     * @return      true if the thing was successful,
     *              false otherwise.
     */
    public boolean storeThing(int place, Thing thing)
    {
        if(validPlace(place)) {
            int startPlace = place - START_OF_SHELF;
            if(things[startPlace] == null) {
                int size = thing.getSize();
                // Fill in all the slots for the full size
                // of the thing.
                for(int i = 0; i < size; i++) {
                    things[startPlace + i] = thing;
                }
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * @param place Which place of shelf. This must be between the
     *        START_OF_SHELF place and the FINAL_THING_SPACE.
     * @return The Thing at the given place. null is returned
     *         if either the place is invalid or there is no
     *         Thing at the given place.
     */
    public Thing getThing(int place)
    {
        if(validPlace(place)) {
            return things[place - START_OF_SHELF];
        }
        else {
            return null;
        }
    }

    /**
     * Print a list of the shelf's things on standard output.
     */
    public void showThings()
    {
        System.out.println("=== Shelf " + shelfNumber + " ===");
        int place = START_OF_SHELF;
        for(Thing thing : things) {
            System.out.print(place + ": ");
            if(thing != null) {
                System.out.println(thing.getDescription());
            }
            else {
                System.out.println();
            }
            place++;
        }
    }

    /**
     * @return The number of this shelf within the storage (1 - 99).
     */
    public int getShelfNumber()
    {
        return shelfNumber;
    }
    
    /**
     * @return true if the place is between FINAL_THING_SPACE and
     *         END_OF_SHELF, false otherwise.
     */
    public boolean validPlace(int place)
    {
        return place >= START_OF_SHELF && place <= FINAL_THING_SPACE;
    }
}
