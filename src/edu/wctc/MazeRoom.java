package edu.wctc;

/**
 * Representing the twisty looping hallways. Implements Lootable and Interactable.
 */
public class MazeRoom extends Room implements Lootable, Interactable {
    private boolean containsPotion;
    private int mazeId;

    public MazeRoom(int id) {
        super("Twisty Maze of Adventure");
        this.mazeId = id;
        this.containsPotion = true;
    }

    @Override
    public String getDescription() {
        String desc = "You are in a maze of twisty passages, all of which look the same.\n" +
                "Damp stone blocks surround you, and water droplets echo through the darkness.\n" +
                "Exits stretch out in every direction, but they feel strangely looping.";

        // Hint injection: If Maze class dynamically connected this room to Subway (EAST)
        if (getEast() != null && getEast().getName().equals("Subway Station")) {
            desc += "\n*** CARL! Princess Donut twitching her ears! You feel a cold, rushing breeze blowing from the EAST! ***";
        }
        return desc;
    }

    @Override
    public String loot(Player player) {
        if (containsPotion) {
            containsPotion = false;
            player.addToInventory("Healing Potion");
            player.addToScore(10);
            return "\n[LOOT REPORT]:\n" +
                    "You search a loose mortar gap in the mossy wall... Success!\n" +
                    "You find a glowing [Healing Potion]!\n" +
                    "Princess Donut purrs: 'Ooh, a shiny health bottle! Put it in the inventory, Carl!'\n" +
                    "(+10 points added to your score!)";
        }
        return "You search the mossy cracks again, but find nothing but damp lint.";
    }

    @Override
    public String interact(Player player) {
        return "You shout down the corridor. Your voice echoes endlessly in circular waves.\n" +
                "Princess Donut grumbles: 'Carl! Stop shouting, you're ruining my hearing and I am a Princess. I have to hear my loyal subjects!'";
    }
}
