package denoflionsx.Handlers;

import denoflionsx.core.core;
import java.util.HashMap;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
    
    public static HashMap<String, ItemStack> ingots = new HashMap();
    public static HashMap<String, ItemStack> dusts = new HashMap();
    public static final String ingot = "ingot";
    public static final String dust = "dust";

    public OreDictHandler() {
        core.print("Starting up Forge Ore Dictionary Listener.");
    }
    
    @ForgeSubscribe
    public void onOreRegistered(OreDictionary.OreRegisterEvent event){
        if (event.Name.substring(0,dust.length()).contains(dust)){
            if (dusts.get(event.Name.substring(0,dust.length())) == null){
                dusts.put(event.Name,event.Ore);
            }
        }else if (event.Name.substring(0,ingot.length()).contains(ingot)){
            if (ingots.get(event.Name.substring(0,ingot.length())) == null){
                ingots.put(event.Name,event.Ore);
            }
        }
    }
    
    public HashMap<String, ItemStack> getDusts(){
        return dusts;
    }
    
    public HashMap<String, ItemStack> getIngots(){
        return ingots;
    } 
}
