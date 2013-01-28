package denoflionsx.LiquidRoundup.API.Objects;

import net.minecraft.item.ItemStack;

public class LRContainer {
    
    private String name;
    private String[] textures;
    private int capacity;
    private ItemStack empty;
    private ItemStack stack;
    private boolean blackListLava;
    private boolean customOnly;

    public LRContainer(String name, String[] textures, int capacity, ItemStack empty, boolean blackListLava, boolean customOnly) {
        this.name = name;
        this.textures = textures;
        this.capacity = capacity;
        this.empty = empty;
        this.blackListLava = blackListLava;
        this.customOnly = customOnly;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public String[] getTextures() {
        return textures;
    }

    public ItemStack getEmpty() {
        return empty;
    }

    public void setEmpty(ItemStack empty) {
        this.empty = empty;
    }

    public boolean isBlackListLava() {
        return blackListLava;
    }

    public boolean isCustomOnly() {
        return customOnly;
    }
}
