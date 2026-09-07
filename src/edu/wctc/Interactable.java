package edu.wctc;

/**
 * Contract guaranteeing that a Player can interact with things inside this Room.
 */
public interface Interactable {
    String interact(Player player);
}