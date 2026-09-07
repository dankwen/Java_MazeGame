package edu.wctc;

/**
 * Contract guaranteeing that a Room can be looted.
 */
public interface Lootable {
    String loot(Player player);
}