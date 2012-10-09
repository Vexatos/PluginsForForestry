package denoflionsx.plugins.Pam.Crops;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.denLib;
import net.minecraft.src.ItemStack;

public class EnumPamCrops {

    public static final String a = "pam";
    public static final String b = "Crop";

    public static String getVarName(String s) {
        return a + denLib.toLowerCaseNoSpaces(s) + b;
    }

    public static String getVarName(EXPLODY e) {
        return getVarName(e.toString());
    }

    public static String getVarName(NONEXPLODY e) {
        return getVarName(e.toString());
    }

    public static enum EXPLODY {

        POTATO,
        ONION,
        CARROT,
        LETTUCE,
        PEANUT,
        BEANS,
        TEA,
        COFFEE,
        BEET,
        BROCCOLI,
        PEAS,
        TURNIP,
        GINGER,
        MUSTARD,
        GARLIC,
        ROTTENMEAT,
        HARVESTWHEAT,
        BLUEBERRY,
        BLACKBERRY,
        RASPBERRY,
        KIWI,
        GRAPE,
        SUNFLOWER,
        WHITEMUSHROOM,
        COTTON,
        CANDLEBERRY;
        private ItemStack crop;

        private EXPLODY() {
            this.crop = denLib.ReflectionHelper.getNewItemStackBlock(EnumModIDs.MODS.PAM.gettheClass(), getVarName(this));
        }

        public ItemStack getCrop() {
            return crop;
        }
    }

    public static enum NONEXPLODY {

        TOMATO,
        CORN,
        CUCUMBER,
        RICE,
        BELLPEPPER,
        EGGPLANT,
        SWEETPOTATO,
        PINEAPPLE,
        STRAWBERRY;
        private ItemStack crop;

        private NONEXPLODY() {
            this.crop = denLib.ReflectionHelper.getNewItemStackBlock(EnumModIDs.MODS.PAM.gettheClass(), getVarName(this));
        }

        public ItemStack getCrop() {
            return crop;
        }
    }

    public static enum TREES {

        NULL;
    }
}
