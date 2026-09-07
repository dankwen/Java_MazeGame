package edu.wctc.interfaces;

import edu.wctc.classes.Player;

/**
 * Contract guaranteeing that a Player can interact with things inside this Room.
 */
public interface Interactable {
    String interact(Player player);
}