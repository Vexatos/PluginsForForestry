package denoflionsx.plugins.ForgottenNature.Crops;

import denoflionsx.denLib.denLib;

public class EnumCrops {
    
    public static final String clazzpath = "ForgottenNature.mod_ForgottenNature";
    public static final int hempCottonBambooCornID = denLib.ReflectionHelper.getStaticInt(clazzpath, "hempCottonBambooCornID");
    public static final int[] corn = new int[]{1,3};
    
    public static enum CROPS{
        
        COTTON(4,7);
        
        private int baseMeta;
        private int grownMeta;

        private CROPS(int baseMeta, int grownMeta) {
            this.baseMeta = baseMeta;
            this.grownMeta = grownMeta;
        }

        public int getBaseMeta() {
            return baseMeta;
        }

        public int getGrownMeta() {
            return grownMeta;
        }
    }
    
}
