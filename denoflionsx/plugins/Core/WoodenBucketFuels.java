package denoflionsx.plugins.Core;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Colors;
import denoflionsx.items.PfFContainer;
import denoflionsx.plugins.Forestry.EnumContainers.Containers;
import denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class WoodenBucketFuels extends PfFContainer {

    public static ItemIDManager ID = new ItemIDManager(1, "WoodenBucketFuel");

    public WoodenBucketFuels(int par1, String name) {
        super(par1, name);
        this.setContainerItem(PfFManagers.ItemManager.getItem("woodenbucket").getItem());
        this.metaMap.put("Liquid Peat Bucket", 0);
        this.metaMap.put("Sugary Peat Bucket", 1);
        this.metaMap.put("Citrus Juice Bucket", 2);
        this.metaMap.put("Pumpkin Juice Bucket", 3);
        this.metaMap.put("Melon Juice Bucket", 4);
        this.metaMap.put("Radioactive Waste Bucket", 5);
        this.metaMap.put("Mushroom Soup Bucket",6);
        this.metaMap.put("Creosote Oil Bucket",7);
        this.metaMap.put("Oil Bucket",8);
        this.metaMap.put("Fuel Bucket",9);
        this.metaMap.put("Seed Oil Bucket",10);
        this.metaMap.put("Apple Juice Bucket",11);
        this.metaMap.put("Honey Bucket",12);
        this.metaMap.put("Biomass Bucket",13);
        this.metaMap.put("Biofuel Bucket",14);
        this.metaMap.put("Crushed Ice Bucket",15);
        addLiquid("Liquid Peat Bucket","liquidpeat",Colors.Values.BROWN);
        addLiquid("Sugary Peat Bucket", "sugarypeat", Colors.Values.LIGHTBROWN);
        addLiquid("Citrus Juice Bucket", "citrusjuice", Colors.Values.SALMON);
        addLiquid("Pumpkin Juice Bucket", "pumpkinjuice", Colors.Values.ORANGE);
        addLiquid("Melon Juice Bucket", "melonjuice", Colors.Values.PINK);
        addLiquid("Radioactive Waste Bucket","radioactivewaste",Colors.Values.LIME);
        addLiquid("Mushroom Soup Bucket","mushroomsoup",Colors.Values.TAN);
        addLiquid("Creosote Oil Bucket", "creosoteoil",Colors.Values.OIL);
        addLiquid("Oil Bucket","oil",Colors.Values.BLACK);
        addLiquid("Fuel Bucket","fuel",Colors.Values.PISS);
        addLiquid("Seed Oil Bucket","seedoil",Colors.Values.SEEDOIL);
        addLiquid("Apple Juice Bucket","applejuice",Colors.Values.LIGHTGREEN);
        addLiquid("Honey Bucket","honey",Colors.Values.HONEY);
        addLiquid("Biomass Bucket","biomass",Colors.Values.GREEN);
        addLiquid("Biofuel Bucket","biofuel",Colors.Values.ORANGE2);
        // Space for crushed ice here
    }

    @Override
    public Item getContainerItem() {
        return PfFManagers.ItemManager.getItem("woodenbucket").getItem();
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
        return PfFManagers.ItemManager.getItem("woodenbucket").copy();
    }

    
    public void addLiquid(String metaMapping, String liquid, Colors.Values v){
        if (PfFManagers.ItemManager.doesItemExist(liquid)){
            ItemStack Liquid = PfFManagers.ItemManager.getItem(liquid);
            this.add(liquid + "bucket", this.metaMap.get(metaMapping),Containers.BUCKET.getTexture(),metaMapping);
            this.renderColors.put(this.metaMap.get(metaMapping),v.getColor());
            LiquidContainerSystem.bucketWithOverride(new ItemStack(this,1,this.metaMap.get(metaMapping)), Liquid, PfFManagers.ItemManager.getItem("woodenbucket"));
        }
    }
}
