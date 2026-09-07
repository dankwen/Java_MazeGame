package edu.wctc.rooms;

import edu.wctc.interfaces.Interactable;
import edu.wctc.interfaces.Lootable;
import edu.wctc.classes.Player;
import edu.wctc.classes.Room;

/**
 * Carl's starting Room. Implements Lootable and Interactable.
 */
public class ShoneysRoom extends Room implements Lootable, Interactable {
    private boolean hasBeenLooted;
    private int talkCount;

    public ShoneysRoom() {
        super("Shoney's Safe Room");
        this.hasBeenLooted = false;
        this.talkCount = 0;
    }

    @Override
    public String getDescription() {
        return "You are standing inside a simulated Shoney's diner, suspended in a holographic brainalyzer grid.\n" +
                "Vinyl booths line the walls, and the air smells faintly of pancakes and static electricity.\n" +
                "Princess Donut, a pristine Persian cat with a tiara, is sitting on a laminate table looking deeply offended.\n" +
                "A nervous-looking gnome-creature named Folded Jerry stands behind the cash register. Only exit is NORTH.";
    }

    @Override
    public String loot(Player player) {
        if (!hasBeenLooted) {
            hasBeenLooted = true;
            player.addToInventory("Gauntlet of Hitting Things");
            player.addToInventory("Magical Boxer Shorts");
            player.addToInventory("Black Leather Jacket");
            player.addToScore(15);
            return "\n[LOOT REPORT]:\n" +
                    "You scramble behind the breakfast bar and loot the supply lockers!\n" +
                    "You find: a [Gauntlet of Hitting Things], [Magical Boxer Shorts] (white with red hearts),\n" +
                    "and a slick [Black Leather Jacket]! You equip them immediately.\n" +
                    "Princess Donut yowls: 'Carl! You know how I feel about white boxers! They are tacky!'\n" +
                    "(+15 points added to your score!)";
        }
        return "You dig through empty pancake syrup dispensers. There is nothing left here to loot.";
    }

    @Override
    public String interact(Player player) {
        talkCount++;
        if (talkCount == 1) {
            return "Folded Jerry stammers: 'Welcome to Shoney's! I... I wish Rick was here...'\n" +
                    "Princess Donut snorts: 'Carl, this gnome-man is pathetic. Let's get moving.'";
        } else if (talkCount == 2) {
            return "Princess Donut look up at you: 'Carl, if we are going to survive this crawl, you need to find some actual pants.'";
        } else {
            return "Folded Jerry is hyper-focusing on cleaning a mustard bottle, muttering to himself about simulated realities.";
        }
    }
}
