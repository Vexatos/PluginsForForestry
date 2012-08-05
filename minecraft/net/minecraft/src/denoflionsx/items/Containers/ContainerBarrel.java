package net.minecraft.src.denoflionsx.items.Containers;

import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.items.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.EnumContainers;

public class ContainerBarrel {

    public static final ItemIDManager ID = new ItemIDManager(1);
    public static multiItem barrel = new multiItem(ID.getItemIDs().get(0),"barrel");
    
    public static void Barrel(){
        
        barrel.metaMap.put("Barrel",0);
        barrel.add("barrel", barrel.metaMap.get("Barrel"), EnumContainers.Containers.BARREL.getTexture(), "Barrel");
        
    }
    
}
