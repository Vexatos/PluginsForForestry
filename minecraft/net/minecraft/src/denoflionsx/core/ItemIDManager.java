package net.minecraft.src.denoflionsx.core;

import java.util.ArrayList;

public class ItemIDManager {

    public static final int baseID = 5312;
    public static int currentID = baseID - 1;

    private ArrayList<Integer> ItemIDs = new ArrayList();

    public ItemIDManager(int numberOfIds) {
        for (int i = 0; i != numberOfIds; i++){
            currentID++;
            this.ItemIDs.add(currentID);
        }
    }

    public ArrayList<Integer> getItemIDs() {
        return ItemIDs;
    }

}
