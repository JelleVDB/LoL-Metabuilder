package be.howest.lolmetabuilder.data;

/**
 * Created by manuel on 11/28/14.
 */
public class Item {
    private int id = 0,
        totalGold = 0,
        baseGold = 0,
        depth = 0,
        specialRecipe = 0,
        map = 10;
    private boolean purchasable = true,
        consumed = false;

    public Item(
            int id, int totalGold, int baseGold,
            boolean purchasable, boolean consumed,
            int depth, int specialRecipe, int map
    ) {
        this.id = id;
        this.totalGold = totalGold;
        this.baseGold = baseGold;
        this.purchasable = purchasable;
        this.consumed = consumed;
        this.depth = depth;
        this.specialRecipe = specialRecipe;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public int getBaseGold() {
        return baseGold;
    }

    public int getDepth() {
        return depth;
    }

    public int getSpecialRecipe() {
        return specialRecipe;
    }

    public int getMap() {
        return map;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public boolean isConsumed() {
        return consumed;
    }
}