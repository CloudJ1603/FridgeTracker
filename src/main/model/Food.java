package model;


public abstract class Food {
    // constants for approximate food item size
    private static final int LARGE = 4;
    private static final int MEDIUM = 2;
    private static final int SMALL = 1;
    // field
    protected String name;
    protected String type;
    protected int expiry;
    protected int size;
    protected boolean edible;

    // constructor
    public Food() {
    }

    // public or protect ????

    protected abstract void freeze();

    protected abstract void pickle();

    protected abstract void cook();

    /* ----------------- getter ----------------------------  */
    public String getName() {
        return name;
    }

    public int getExpiry() {
        return expiry;
    }

    public String getType() {
        return type;
    }

    public boolean isEdible() {
        return edible;
    }

    public int getSize() {
        return this.size;
    }

    /* ----------------- setter ----------------------------  */
    public void setName(String name) {
        this.name = name;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(String size) {
        if (size.equals("S")) {
            this.size = SMALL;
        } else if (size.equals("M")) {
            this.size = MEDIUM;
        } else {
            this.size = LARGE;
        }
    }

}
