package be.howest.lolmetabuilder.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by manuel on 11/28/14.
 */
public class Item {
    private int id = 0,
            totalGold = 0,
            baseGold = 0,
            depth = 0,
            specialRecipe = 0,
            map = 10,
            stacks = 0;
    private boolean purchasable = true,
            consumed = false;
    private String name = "",
            description = "",
            group = "",
            image = "";
    private ArrayList<Stat> stats = new ArrayList<Stat>();
    private ArrayList<Tag> tags = new ArrayList<Tag>();
    private ArrayList<Effect> effects = new ArrayList<Effect>();
    private ArrayList<CircularItem> requires = new ArrayList<CircularItem>();
    private ArrayList<Integer> requiresIds = new ArrayList<Integer>();
    private ArrayList<Integer> buildIntoIds = new ArrayList<Integer>();
    private ArrayList<CircularItem> buildIntos = new ArrayList<CircularItem>();

    public Item(
            int id, int totalGold, int baseGold,
            boolean purchasable, boolean consumed,
            int depth, int specialRecipe, int map,
            String name, String description, String group,
            int stacks, String image
    ) {
        this.id = id;
        this.totalGold = totalGold;
        this.baseGold = baseGold;
        this.purchasable = purchasable;
        this.consumed = consumed;
        this.depth = depth;
        this.specialRecipe = specialRecipe;
        this.map = map;
        this.name = name;
        this.description = description;
        this.group = group;
        this.stacks = stacks;
        this.image = image;
    }

    public ArrayList<Integer> getBuildIntoIds() {
        return buildIntoIds;
    }

    public void setBuildIntoIds(ArrayList<Integer> buildIntoIds) {
        this.buildIntoIds = buildIntoIds;
    }

    public ArrayList<CircularItem> getBuildIntos() {
        return buildIntos;
    }

    public void setBuildIntos(ArrayList<CircularItem> buildIntos) {
        this.buildIntos = buildIntos;
    }

    public ArrayList<Integer> getRequiresIds() {
        return requiresIds;
    }

    public void setRequiresIds(ArrayList<Integer> requiresIds) {
        this.requiresIds = requiresIds;
    }

    public ArrayList<CircularItem> getRequires() {
        return requires;
    }

    public void setRequires(ArrayList<CircularItem> requires) {
        this.requires = requires;
    }

    public String getImage() {
        return image;
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public int getStacks() {
        return stacks;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
