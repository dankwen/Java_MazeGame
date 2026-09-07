package edu.wctc;

import java.util.Scanner;

/**
 * The driver class. Strictly the ONLY class permitted to perform I/O
 * (Scanner/System.out) under the assignment grading specifications.
 */
public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("    WELCOME TO THE DUNGEON CRAWLER CARL MAZE!   ");
        System.out.println("=================================================");
        System.out.println("Carl & Princess Donut are trapped in the crawl!");
        System.out.println("Commands:\n" +
                "  n, s, e, w, u, d : Move cardinal directions\n" +
                "  i                : Interact with the room / NPCs / Boss\n" +
                "  l                : Loot the room\n" +
                "  v                : View your items & score\n" +
                "  x                : Attempt to exit room\n" +
                "=================================================");

        while (!maze.isFinished()) {
            System.out.println("\n-------------------------------------------------");
            System.out.println(maze.getCurrentRoomDescription());
            System.out.println("Obvious Exits: [ " + maze.getCurrentRoomExits() + " ]");
            System.out.print("What is your command, Carl? > ");

            String rawInput = scanner.nextLine().trim();
            if (rawInput.isEmpty()) {
                continue;
            }

            // Magical Cheat-Code teleport bypass from the classic USENET MUSH era
            if (rawInput.equalsIgnoreCase("xyzzy")) {
                System.out.println("\n*** Shimmering green particles orbit Princess Donut's tiara! ***");
                System.out.println("Princess Donut yowls: 'CARL! Jump! It's an spatial shortcut!'");
                maze.teleportToSubway();
                continue;
            }

            char command = Character.toLowerCase(rawInput.charAt(0));

            if (command == 'n' || command == 's' || command == 'e' || command == 'w' || command == 'u' || command == 'd') {
                boolean hasMoved = maze.move(command);
                if (hasMoved) {
                    System.out.println("You move down the corridor...");
                } else {
                    System.out.println("There is no path leading that direction! You walk smack into a wall.");
                }
            } else if (command == 'i') {
                String result = maze.interactWithCurrentRoom();
                System.out.println(result);
            } else if (command == 'l') {
                String result = maze.lootCurrentRoom();
                System.out.println(result);
            } else if (command == 'v') {
                System.out.println(maze.getPlayerInventory());
                System.out.println("Current Score: " + maze.getPlayerScore() + " Points");
            } else if (command == 'x') {
                String result = maze.exitCurrentRoom();
                System.out.println(result);
            } else {
                System.out.println("Carl, that's not a valid command! (Use n, s, e, w, u, d, i, l, v, x)");
            }
        }

        System.out.println("\n=================================================");
        System.out.println("                   GAME OVER                     ");
        System.out.println("=================================================");
        System.out.println("Congratulations! You cleared the game loop!");
        System.out.println("Final Score: " + maze.getPlayerScore() + " Points");
        System.out.println("=================================================");

        scanner.close();
    }
}
