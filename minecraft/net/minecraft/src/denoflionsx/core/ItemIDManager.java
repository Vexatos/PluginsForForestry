package net.minecraft.src.denoflionsx.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.denoflionsx.denLib.Config.Config;

public class ItemIDManager {

    public static int baseID;
    public static int currentID;
    public static String configFile = "ItemIDRegistry.cfg";
    public static Config usedIDs = new Config(configFile);
    public static boolean hasDefault = false;
    public static boolean alreadyExisted = false;
    private ArrayList<Integer> ItemIDs = new ArrayList();
    private int readID;

    public ItemIDManager(int numberOfIds, String tag) {
        if (!hasDefault) {
            File f = new File(Config.ConfigDir + configFile);
            if (f.exists()) {
                alreadyExisted = true;
            }
            defaults();
            usedIDs.writeConfig();
            usedIDs.readFile();
            baseID = usedIDs.getOptionInt("BaseID");
            currentID = baseID - 1;
            hasDefault = true;
        }
        for (int i = 0; i != numberOfIds; i++) {
            tag = tag + i;
            if (usedIDs.getOptionNoUpdate(tag) != null) {
            } else {
                if (alreadyExisted) {
                    currentID = findLastID();
                }
                currentID++;
                usedIDs.addDefault(tag + "=" + currentID);
            }
            readID = usedIDs.getOptionInt(tag);
            ItemIDs.add(readID);
        }
    }

    public static int findLastID() {
        Iterator i = usedIDs.Options.entrySet().iterator();
        int id;
        ArrayList<Integer> list = new ArrayList();
        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            list.add(Integer.valueOf(pairs.getValue().toString()));
        }
        Collections.sort(list);
        id = list.get(list.size() - 1);
        return id;
    }

    public static void defaults() {
        usedIDs.addDefault("[Item ID Registry]");
        usedIDs.addDefault("# This file makes sure the system does not");
        usedIDs.addDefault("# use the same item ID twice when upgrading");
        usedIDs.addDefault("# from 1.3.1 onward.");
        usedIDs.addDefault("BaseID=5312");
        usedIDs.addDefault("[Registry]");
        usedIDs.addDefault("# Do not mess with these values.");
        usedIDs.addDefault("# If you need to change an id do it in the normal");
        usedIDs.addDefault("# config file for the plugin.");
    }

    public ArrayList<Integer> getItemIDs() {
        return ItemIDs;
    }
}
