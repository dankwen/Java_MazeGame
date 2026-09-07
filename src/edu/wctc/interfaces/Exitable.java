package edu.wctc.interfaces;

import edu.wctc.classes.Player;

/**
 * Contract guaranteeing that a Room contains a pathway to leave the Maze.
 */
public interface Exitable {
    String exit(Player player);
}
