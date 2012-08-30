package net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels;

import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.items.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.EnumContainers;

public class InfusionBar extends multiItem{

    public InfusionBar(int par1, String name) {
        super(par1, name);
        this.metaMap.put("Infusion Bar",0);
        this.add("infusionbar",EnumContainers.Containers.BAR.getTexture(),this.metaMap.get("Infusion Bar"),"Infusion Bar");
    }
    
    public void infusionSystem(){
        // TODO
    }
    
    public static enum INFUSIONBAR{
        BAR();
        
        public static InfusionBar bar;
        private static ItemIDManager id = new ItemIDManager(1,"InfusionBar");
        
        private INFUSIONBAR(){
        }
        
        static{
            bar = new InfusionBar(id.getItemIDs().get(0),"infusionbar");
        }
        
        public static void register(){
            InfusionBar.INFUSIONBAR.values();
        }
    }
}
