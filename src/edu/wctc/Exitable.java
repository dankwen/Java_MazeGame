package edu.wctc;

/**
 * Contract guaranteeing that a Room contains a pathway to leave the Maze.
 */
public interface Exitable {
    String exit(Player player);
}
