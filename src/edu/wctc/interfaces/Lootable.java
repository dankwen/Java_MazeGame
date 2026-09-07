package edu.wctc.interfaces;

import edu.wctc.classes.Player;

/**
 * Contract guaranteeing that a Room can be looted.
 */
public interface Lootable {
    String loot(Player player);
}