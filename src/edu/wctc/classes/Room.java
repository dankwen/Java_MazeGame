package edu.wctc.classes;

/**
 * The abstract blueprint for every Room in our game.
 * It handles the spatial navigation grid pointers.
 */
public abstract class Room {
    private String name;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Room up;
    private Room down;

    // Constructor
    public Room(String name) {
        this.name = name;
    }

    // Abstract method that concrete subclasses must implement
    public abstract String getDescription();

    // Returns connected room in the given direction or null if blocked
    public Room getAdjoiningRoom(char direction) {
        char dir = Character.toLowerCase(direction);
        switch (dir) {
            case 'n': return north;
            case 's': return south;
            case 'e': return east;
            case 'w': return west;
            case 'u': return up;
            case 'd': return down;
            default: return null;
        }
    }

    // Returns a space-separated String of available exits
    public String getExits() {
        StringBuilder sb = new StringBuilder();
        if (north != null) sb.append("n ");
        if (south != null) sb.append("s ");
        if (east != null) sb.append("e ");
        if (west != null) sb.append("w ");
        if (up != null) sb.append("u ");
        if (down != null) sb.append("d ");

        String exits = sb.toString().trim();
        return exits.isEmpty() ? "None" : exits;
    }

    public String getName() {
        return name;
    }

    public boolean isValidDirection(char direction) {
        return getAdjoiningRoom(direction) != null;
    }

    // Setters for connecting rooms
    public void setNorth(Room north) { this.north = north; }
    public void setSouth(Room south) { this.south = south; }
    public void setEast(Room east) { this.east = east; }
    public void setWest(Room west) { this.west = west; }
    public void setUp(Room up) { this.up = up; }
    public void setDown(Room down) { this.down = down; }

    // Helpers to easily access directional neighbors
    protected Room getEast() { return east; }
}
