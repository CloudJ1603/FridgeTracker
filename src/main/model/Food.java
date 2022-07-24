package model;

// an abstract class which is the superclass of all types of food
public abstract class Food {
    // field
    protected String name;
    protected String type;
    protected int remaining;
    protected int size;


    // EFFECTS: construct Food object
    public Food() {
    }

    /* ----------------- getter ----------------------------  */
    // EFFECTS: return the name of the Food object
    public String getName() {
        return name;
    }

    // EFFECTS: return the remaining days of the Food object
    public int getRemaining() {
        return remaining;
    }

    // EFFECTS: return the type of the Food object
    public String getType() {
        return type;
    }


    /* ----------------- setter ----------------------------  */
    // MODIFIES: this
    // EFFECTS: set up the name of the Food object
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: the input parameter must be a positive integer
    // MODIFIES: this
    // EFFECTS: set up the remaining (days) of the Food object
    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


    /* ----------------- modifier ----------------------------  */
    // MODIFIES: this
    // EFFECTS: forward to next day, equivalent to decrement the remaining (days)
    public void nextDay() {
        if (remaining != 0) {
            remaining--;
        }
    }

}
