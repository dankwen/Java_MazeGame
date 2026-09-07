package edu.wctc;

import edu.wctc.classes.Player;
import edu.wctc.classes.Room;
import edu.wctc.interfaces.Exitable;
import edu.wctc.interfaces.Interactable;
import edu.wctc.interfaces.Lootable;
import edu.wctc.rooms.MazeRoom;
import edu.wctc.rooms.ShoneysRoom;
import edu.wctc.rooms.SubwayStationRoom;

/**
 * Builds the game map, manages room traversal, and delegates interactions.
 */
public class Maze {
    private Room currentRoom;
    private Player player;
    private boolean isFinished;
    private String lastFailureMessage;

    // Custom tracking fields for your custom Dungeon Crawler Carl loop logic
    private Room subwayStation;
    private int mazeMoves;
    private final int targetMoves;

    // Constructor required by written specification
    public Maze() {
        this.player = new Player();
        this.isFinished = false;
        this.lastFailureMessage = null;
        this.mazeMoves = 0;
        // The Adventure maze loop opens after a random number of moves (3 to 6)
        this.targetMoves = (int) (Math.random() * 4) + 3;
        initializeRooms();
    }

    // Overloaded Constructor required by the Class Diagram spec (ensures 100% compatibility)
    public Maze(Player player) {
        this.player = player;
        this.isFinished = false;
        this.lastFailureMessage = null;
        this.mazeMoves = 0;
        this.targetMoves = (int) (Math.random() * 4) + 3;
        initializeRooms();
    }

    private void initializeRooms() {
        // Instantiate our three distinct concrete Room classes (Lootable, Interactable, Exitable)
        Room shoneys = new ShoneysRoom();
        Room maze1 = new MazeRoom(1);
        Room maze2 = new MazeRoom(2);
        Room maze3 = new MazeRoom(3);
        this.subwayStation = new SubwayStationRoom();

        // 1. Start Room: Shoney's
        this.currentRoom = shoneys;

        // 2. Connect Shoney's to the adventure maze
        shoneys.setNorth(maze1);
        maze1.setSouth(shoneys);

        // 3. Connect the twisty maze rooms in a cyclical loop
        maze1.setNorth(maze2);
        maze2.setSouth(maze1);

        maze2.setNorth(maze3);
        maze3.setSouth(maze2);

        maze3.setNorth(maze1);
        maze1.setSouth(maze3);

        maze1.setWest(maze2);
        maze2.setEast(maze1);
    }

    public boolean move(char direction) {
        if (currentRoom instanceof ShoneysRoom && direction == 'n' && !((ShoneysRoom) currentRoom).hasBeenLooted()) {
            lastFailureMessage = ((ShoneysRoom) currentRoom).getExitBlockedMessage();
            return false;
        }

        if (currentRoom.isValidDirection(direction)) {
            lastFailureMessage = null;
            currentRoom = currentRoom.getAdjoiningRoom(direction);

            // Loop Maze Logic: Every step inside an Adventure MazeRoom increments the step counter
            if (currentRoom instanceof MazeRoom) {
                mazeMoves++;
                if (mazeMoves >= targetMoves) {
                    revealSubwayExit();
                }
            }
            return true;
        }

        lastFailureMessage = null;
        return false;
    }

    private void revealSubwayExit() {
        // Dynamically punch a hole in space, connecting the current maze room's EAST exit to the subway
        if (currentRoom instanceof MazeRoom && currentRoom.getAdjoiningRoom('e') != subwayStation) {
            currentRoom.setEast(subwayStation);
            subwayStation.setWest(currentRoom);
        }
    }

    // Direct teleportation bypass for your classic ADVENTURE! secret code "xyzzy"
    public void teleportToSubway() {
        System.out.println("As you read the scroll magical sparks rise from the ground, surrounding you.");
        System.out.println("Princess Donut yowls: 'CARL! Jump! It's a spatial shortcut!'");
        System.out.println("The world fades out and you feel yourself teleporting...");
        this.currentRoom = subwayStation;
    }

    public String exitCurrentRoom() {
        if (currentRoom instanceof ShoneysRoom && !((ShoneysRoom) currentRoom).hasBeenLooted()) {
            lastFailureMessage = ((ShoneysRoom) currentRoom).getExitBlockedMessage();
            return lastFailureMessage;
        }

        if (currentRoom instanceof Exitable) {
            String result = ((Exitable) currentRoom).exit(player);
            // If the player successfully ran up the stairs (meaning the boss is dead)
            if (!result.contains("blocks") && !result.contains("cannot")) {
                this.isFinished = true;
            }
            return result;
        }
        return "There is no exit in this room! You must keep exploring.";
    }

    public String interactWithCurrentRoom() {
        if (currentRoom instanceof Interactable) {
            return ((Interactable) currentRoom).interact(player);
        }
        return "No interactions are possible here.";
    }

    public String lootCurrentRoom() {
        if (currentRoom instanceof Lootable) {
            return ((Lootable) currentRoom).loot(player);
        }
        return "You find nothing.";
    }

    public String getCurrentRoomDescription() {
        return currentRoom.getDescription();
    }

    public String getCurrentRoomExits() {
        return currentRoom.getExits();
    }

    public String getPlayerInventory() {
        return player.getInventory();
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public String getLastFailureMessage() {
        return lastFailureMessage;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
