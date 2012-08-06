package net.minecraft.src.denoflionsx.items.Containers;

import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.items.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.EnumContainers;

public class ContainerBarrel {

    public static enum Barrel {

        BARREL();
        public static final ItemIDManager ID = new ItemIDManager(1, "Barrel");
        public static final multiItem barrel = new multiItem(ID.getItemIDs().get(0), "barrel");

        private Barrel(){
            
        }
        
        static {
            Barrel.values();
            barrel.metaMap.put("Barrel", 0);
            barrel.add("barrel", barrel.metaMap.get("Barrel"), EnumContainers.Containers.BARREL.getTexture(), "Barrel");
        }
    }
}
