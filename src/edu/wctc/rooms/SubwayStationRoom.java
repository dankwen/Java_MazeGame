package edu.wctc.rooms;

import edu.wctc.interfaces.Exitable;
import edu.wctc.interfaces.Interactable;
import edu.wctc.classes.Player;
import edu.wctc.classes.Room;

/**
 * The Boss room containing War God Grull. Implements Interactable and Exitable.
 */
public class SubwayStationRoom extends Room implements Interactable, Exitable {
    private int grullHP;
    private boolean grullDefeated;

    public SubwayStationRoom() {
        super("Subway Station");
        this.grullHP = 25;
        this.grullDefeated = false;
    }

    public boolean isGrullDefeated() {
        return grullDefeated;
    }

    @Override
    public String getDescription() {
        if (!grullDefeated) {
            return "You enter a cold, derelict Subway Station.\n" +
                    "Shattered tiles litter the floor, and subway tracks disappear into the darkness.\n" +
                    "Directly blocking the concrete stairs to the EAST exit stands the colossal **War God Grull**!\n" +
                    "He is a terrifying chimera—the upper torso of a muscular warrior, the head of a bull,\n" +
                    "and the massive lower body of a warhorse. He strikes his hooves against the concrete, producing sparks!\n" +
                    "You cannot leave while he blocks the exit. Prepare to INTERACT to fight!";
        } else {
            return "The Subway Station is quiet now. War God Grull has shattered into piles of digital cubes.\n" +
                    "The concrete stairs leading EAST up to the neon EXIT sign are wide open!";
        }
    }

    @Override
    public String interact(Player player) {
        if (grullDefeated) {
            return "War God Grull is already vanquished. The subway platform is clear.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n=== COMBAT INITIATED ===\n");

        // Carl attacks using Gauntlet
        int carlDmg = (int) (Math.random() * 8) + 5; // Deals 5-12 damage
        grullHP -= carlDmg;
        if (grullHP < 0) grullHP = 0;

        sb.append("You swing your [Gauntlet of Hitting Things] with brutal DIY force!\n");
        sb.append("Carl strikes Grull for ").append(carlDmg).append(" damage!\n");
        sb.append("Grull's HP: ").append(grullHP).append("/25\n\n");

        if (grullHP <= 0) {
            grullDefeated = true;
            player.addToScore(50);
            sb.append("CRITICAL HIT! War God Grull lets out a deafening bull-roar of defeat!\n");
            sb.append("His massive frame ruptures, dissolving into a waterfall of sparkling blue voxels.\n");
            sb.append("Princess Donut screeches in triumph: 'YES! We did it, Carl! We beat him!'\n");
            sb.append("(+50 victory points added to your score!)");
        } else {
            // Grull counter-attacks
            boolean isDodged = Math.random() < 0.6; // 60% chance to dodge cleanly
            if (isDodged) {
                sb.append("War God Grull swings his iron-spiked war club!\n");
                sb.append("But Carl executes a frantic dive! You dodge the club cleanly!\n");
                sb.append("Donut yells: 'Incredible agility, Carl!'");
            } else {
                int grullDmg = (int) (Math.random() * 3) + 1; // 1-3 damage
                sb.append("Grull swings his club, grazing Carl's leather jacket!\n");
                sb.append("Carl takes ").append(grullDmg).append(" scratch damage!\n");
                sb.append("Carl spits: 'Is that all you got, you overgrown pony?'");
            }
        }
        return sb.toString();
    }

    @Override
    public String exit(Player player) {
        if (!grullDefeated) {
            return "War God Grull snorts a stream of liquid fire and stamps his horse hooves!\n" +
                    "Grull bellows: 'YOU CANNOT ESCAPE, CARL!'\n" +
                    "The monster physically blocks the platform stairs. You must defeat him first!";
        } else {
            player.addToScore(100); // Massive bonus for completion
            return "\n[ESCAPE REPORT]:\n" +
                    "Carl leaps over the turnstiles with Princess Donut secure in his jacket!\n" +
                    "You run up the concrete stairs as the subway tunnel collapses behind you\n" +
                    "into a cascade of structural debris.\n" +
                    "CONGRATULATIONS! You have conquered this floor of the crawl!\n" +
                    "Your final score has been processed.";
        }
    }
}