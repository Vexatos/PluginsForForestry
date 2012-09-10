package denoflionsx.plugins.Core;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Colors;
import denoflionsx.items.PfFContainer;
import denoflionsx.plugins.Forestry.EnumContainers;
import denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class BarrelFuels extends PfFContainer {
    
    public static ItemIDManager ID = new ItemIDManager(1,"BarrelFuels");

    public BarrelFuels(int par1, String name) {
        super(par1, name);
        this.setContainerItem(PfFManagers.ItemManager.getItem("barrel").getItem());
        this.metaMap.put("Creosote Oil Barrel", 0);
        this.metaMap.put("Oil Barrel", 1);
        this.metaMap.put("Fuel Barrel", 2);
        this.metaMap.put("Seed Oil Barrel", 3);
        this.metaMap.put("Apple Juice Barrel", 4);
        this.metaMap.put("Honey Barrel", 5);
        this.metaMap.put("Biomass Barrel", 6);
        this.metaMap.put("Biofuel Barrel", 7);
        this.metaMap.put("Crushed Ice Barrel", 8);
        addLiquid("Creosote Oil Barrel", "creosoteoil", Colors.Values.OIL);
        addLiquid("Oil Barrel", "oil", Colors.Values.BLACK);
        addLiquid("Fuel Barrel", "fuel", Colors.Values.PISS);
        addLiquid("Seed Oil Barrel", "seedoil", Colors.Values.SEEDOIL);
        addLiquid("Apple Juice Barrel", "applejuice", Colors.Values.LIGHTGREEN);
        addLiquid("Honey Barrel", "honey", Colors.Values.HONEY);
        addLiquid("Biomass Barrel", "biomass", Colors.Values.GREEN);
        addLiquid("Biofuel Barrel", "biofuel", Colors.Values.ORANGE2);
    }

    public void addLiquid(String metaMapping, String liquid, Colors.Values v) {
        if (PfFManagers.ItemManager.doesItemExist(liquid)) {
            ItemStack Liquid = PfFManagers.ItemManager.getItem(liquid);
            this.add(liquid + "barrel", this.metaMap.get(metaMapping), EnumContainers.Containers.BARREL.getTexture(), metaMapping);
            this.renderColors.put(this.metaMap.get(metaMapping), v.getColor());
            LiquidContainerSystem.barrelOverride(PfFManagers.ItemManager.getItem("barrel"), new ItemStack(this,1,this.metaMap.get(metaMapping)),Liquid.itemID);
        }
    }

    @Override
    public Item getContainerItem() {
        return PfFManagers.ItemManager.getItem("barrel").getItem();
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
        return PfFManagers.ItemManager.getItem("barrel");
    }
}
