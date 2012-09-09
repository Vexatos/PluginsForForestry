package denoflionsx.plugins.Core;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Colors;
import denoflionsx.items.PfFContainer;
import denoflionsx.plugins.Forestry.EnumContainers.Containers;
import denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.ItemStack;

public class WoodenBucketFuels extends PfFContainer {

    public static ItemIDManager ID = new ItemIDManager(1, "WoodenBucketFuel");
    private static ItemStack WoodenBucket;

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
        WoodenBucket = PfFManagers.ItemManager.getItem("woodenbucket");
        addLiquid("Liquid Peat Bucket","liquidpeat",Colors.Values.BROWN);
        addLiquid("Sugary Peat Bucket", "sugarypeat", Colors.Values.LIGHTBROWN);
        addLiquid("Citrus Juice Bucket", "citrusjuice", Colors.Values.SALMON);
        addLiquid("Pumpkin Juice Bucket", "pumpkinjuice", Colors.Values.ORANGE);
        addLiquid("Melon Juice Bucket", "melonjuice", Colors.Values.PINK);
        addLiquid("Radioactive Waste Bucket","radioactivewaste",Colors.Values.LIME);
        addLiquid("Mushroom Soup Bucket","mushroomsoup",Colors.Values.TAN);
    }
    
    public void addLiquid(String metaMapping, String liquid, Colors.Values v){
        if (PfFManagers.ItemManager.doesItemExist(liquid)){
            ItemStack Liquid = PfFManagers.ItemManager.getItem(liquid);
            this.add(liquid + "bucket", this.metaMap.get(metaMapping),Containers.BUCKET.getTexture(),metaMapping);
            this.renderColors.put(this.metaMap.get(metaMapping),v.getColor());
            LiquidContainerSystem.bucketWithOverride(new ItemStack(this,1,this.metaMap.get(metaMapping)), Liquid, WoodenBucket);
        }
    }
}
