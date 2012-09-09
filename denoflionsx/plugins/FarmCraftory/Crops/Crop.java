package denoflionsx.plugins.FarmCraftory.Crops;

import denoflionsx.plugins.FarmCraftory.cropHarvestProvider;

public class Crop extends cropHarvestProvider{

    public Crop(EnumCrops.SINGLE po) {
        super(po.getPlant());
    }
}
