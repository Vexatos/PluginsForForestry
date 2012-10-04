package denoflionsx.plugins.FarmCraftory;

import denoflionsx.denLib.denLib;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class CropTileEntityHelper {
    
    public static final String single = "TileEntitySingleHarvest";
    public static final String multi = "TileEntityMultiHarvest";
    
    public static boolean isSingle(TileEntity t){
        return cleanClassQuery(denLib.getTileEntityName(t)).equals(denLib.toLowerCaseNoSpaces(single));
    }
    
    public static boolean isMulti(TileEntity t){
        return cleanClassQuery(denLib.getTileEntityName(t)).equals(denLib.toLowerCaseNoSpaces(multi));
    }
    
    private static String cleanClassQuery(String query){
        query = denLib.toLowerCaseNoSpaces(query);
        query = query.substring(5);
        return query;
    }
    
    public static HELPER getHelperType(TileEntity t){
        if (isSingle(t)){
            return HELPER.SINGLE;
        }else if (isMulti(t)){
            return HELPER.MULTI;
        }
        return null;
    }

    public static enum HELPER {

        SINGLE(single),
        MULTI(multi);
        private String theClass;

        private HELPER(String theClass) {
            this.theClass = theClass;
        }

        public int getGrowthStage(World world, int x, int y, int z) {
            return denLib.ReflectionHelper.getIntFromTileEntity(theClass, "growthStage", world.getBlockTileEntity(x, y, z));
        }

        public int getCropID(World world, int x, int y, int z) {
            return denLib.ReflectionHelper.getIntFromTileEntity(theClass, "cropID", world.getBlockTileEntity(x, y, z));
        }

        public void setCropID(World world, int x, int y, int z, int cropID) {
            denLib.ReflectionHelper.setIntFromTileEntity(theClass, "cropID", world.getBlockTileEntity(x, y, z), cropID);
            world.markBlockNeedsUpdate(x, y, z);
        }

        public void setGrowthStage(World world, int x, int y, int z, int growth) {
            if (growth > 2) {
                growth = 2;
            }
            denLib.ReflectionHelper.setIntFromTileEntity(theClass, "growthStage", world.getBlockTileEntity(x, y, z), growth);
            world.markBlockNeedsUpdate(x, y, z);
        }

        public void setCropIDandGrowthStage(World world, int x, int y, int z, int cropID, int growth) {
            this.setCropID(world, x, y, z, cropID);
            this.setGrowthStage(world, x, y, z, growth);
        }
    }
}
