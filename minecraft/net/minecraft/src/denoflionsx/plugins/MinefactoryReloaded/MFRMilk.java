package net.minecraft.src.denoflionsx.plugins.MinefactoryReloaded;

import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.items.PfFContainer;
import net.minecraft.src.denoflionsx.plugins.Forestry.EnumContainers.Containers;
import net.minecraft.src.denoflionsx.plugins.Forestry.LiquidContainerSystem;

public class MFRMilk{
  
    private ItemStack milk = null;
    private ItemStack betterFarmingMilkBottle = null;
    private int mJt = 0;
    private int burnTime = 0;
    private int isSafeFuel = 0;
    private int itemID = 0;
    private String name = "Milk";
    private PfFContainer fuel = null;

    public MFRMilk(int itemID,ItemStack milk, ItemStack bfbottle, int mJt, int burntime, boolean issafe) {
        
        this.milk = milk;
        this.betterFarmingMilkBottle = bfbottle;
        this.mJt = mJt;
        this.burnTime = burntime;
        if (issafe){
            this.isSafeFuel = 1;
        }else{
            this.isSafeFuel = 2;
        }
        this.itemID = itemID;
        create();
    }
    
    private void create(){
        
        fuel = new PfFContainer(this.itemID,this.name);
        
        fuel.metaMap.put(this.name,0);
        fuel.metaMap.put(this.name + " Capsule",1);
        fuel.metaMap.put(this.name + " Can",2);
        fuel.metaMap.put(this.name + " Capsule_Red",3);
        fuel.metaMap.put(this.name + " Bucket",4);
        fuel.metaMap.put(this.name + " Bottle",5);
        fuel.metaMap.put(this.name + " Cell",6);
        
        fuel.add("milkplaceholder",fuel.metaMap.get(this.name),0,"PLACEHOLDER");
        fuel.add("milkcapsule",fuel.metaMap.get(this.name + " Capsule"),Containers.CAPSULE.getTexture(),this.name + " Capsule");
        fuel.add("milkcan",fuel.metaMap.get(this.name + " Can"),Containers.CAN.getTexture(),this.name + " Can");
        fuel.add("milkcapsule_red",fuel.metaMap.get(this.name + " Capsule_Red"),Containers.CAPSULE_RED.getTexture(),this.name + " Capsule");
        fuel.add("milkbucketplaceholder",fuel.metaMap.get(this.name + " Bucket"),0,"PLACEHOLDER");
        fuel.add("milkcell",fuel.metaMap.get(this.name + " Cell"),Containers.CELL.getTexture(),this.name + " Cell");
        if (this.betterFarmingMilkBottle != null){
            fuel.add("milkbottleplaceholder",fuel.metaMap.get(this.name + " Bottle"),0,"PLACEHOLDER");
            LiquidContainerSystem.createWithOverride(fuel, this.milk.itemID, this.betterFarmingMilkBottle, true);
        }else{
            fuel.add("milkbottle",fuel.metaMap.get(this.name + " Bottle"),Containers.BOTTLE.getTexture(),this.name + " Bottle");
            LiquidContainerSystem.createWithOverride(fuel, this.milk.itemID, API.getItem("milkbottle"), true);
        }
        fuel.setAllRenderColor(Colors.Values.WHITE.getColor());
        FuelManager.bronzeEngineFuel.put(this.milk.itemID,new EngineBronzeFuel(this.milk,this.mJt,this.burnTime,this.isSafeFuel));  
    }

}
