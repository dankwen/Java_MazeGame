package edu.wctc.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracks the player's core state: score and inventory contents.
 */
public class Player {
    private int score;
    private List<String> inventory;

    public Player() {
        this.score = 0;
        this.inventory = new ArrayList<>();
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public void addToScore(int points) {
        score += points;
    }

    public String getInventory() {
        if (inventory.isEmpty()) {
            return "Your inventory is currently empty.";
        }

        // Split items conceptually into worn items for the flavor experience
        StringBuilder sb = new StringBuilder("--- Player Status & Inventory ---\n");
        sb.append("You are carrying:\n");
        for (String item : inventory) {
            sb.append("  - ").append(item).append("\n");
        }

        sb.append("\nYou are wearing:\n");
        if (inventory.contains("Black Leather Jacket")) sb.append("  - Black Leather Jacket\n");
        if (inventory.contains("Magical Boxer Shorts")) sb.append("  - Magical Boxer Shorts (white with red hearts)\n");
        if (inventory.contains("Gauntlet of Hitting Things")) sb.append("  - Gauntlet of Hitting Things (equipped)\n");

        return sb.toString().trim();
    }

    public int getScore() {
        return score;
    }
}
