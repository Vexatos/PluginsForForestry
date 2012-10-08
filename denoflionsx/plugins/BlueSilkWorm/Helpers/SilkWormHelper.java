package denoflionsx.plugins.BlueSilkWorm.Helpers;

import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGender;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpan;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeState;
import denoflionsx.plugins.BlueSilkWorm.Managers.SilkWormManagers;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public class SilkWormHelper {

    public static void setupWorm(ItemStack worm) {
        worm.stackTagCompound = new NBTTagCompound();
        setWormGender(worm, SilkWormManagers.LifeManager.getNewGender());
        setWormLifeSpan(worm, SilkWormManagers.LifeManager.getNewLifeSpanNoGenetics());
        setWormLifeStateNormal(worm);
    }

    public static boolean isWormValid(ItemStack worm) {
        return worm.stackTagCompound != null;
    }

    public static void progressWorm(ItemStack worm) {
        int current = getWormCurrentLife(worm);
        current--;
        setWormCurrentLife(worm,current);
        if (current == 0){
            if (worm.getItemDamage() == SilkWormGrowthStages.WORM.getMeta()){
                setWormCurrentLifeToCocoon(worm);
                worm.setItemDamage(SilkWormGrowthStages.COCOON.getMeta());
            }else if (worm.getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()){
                setWormCurrentLife(worm,-1);
                worm.setItemDamage(SilkWormGrowthStages.MOTH.getMeta());
            }
        }
    }
    
    public static void setWormGender(ItemStack worm, ISilkWormGender gender) {
        worm.stackTagCompound.setString(SilkWormStats.STAT_GENDER, gender.getGender());
    }
    
    public static String getWormCocoonCategory(ItemStack worm){
        return worm.stackTagCompound.getString(SilkWormStats.STAT_LIFESPAN_TTL_COCOON_LABEL);
    }
    
    public static String getWormMothCategory(ItemStack worm){
        return worm.stackTagCompound.getString(SilkWormStats.STAT_LIFESPAN_TTL_MOTH_LABEL);
    }
    
    public static int getWormLength(ItemStack worm){
        return worm.stackTagCompound.getInteger(SilkWormStats.STAT_LIFESPAN_TTL_COCOON);
    }
    
    public static int getWormCocoonLength(ItemStack worm){
        return worm.stackTagCompound.getInteger(SilkWormStats.STAT_LIFESPAN_TTL_MOTH);
    }

    public static void setWormLifeSpan(ItemStack worm, ISilkWormLifeSpan span) {
        worm.stackTagCompound.setInteger(SilkWormStats.STAT_LIFESPAN_TTL_COCOON, span.getActualWormLifespan());
        worm.stackTagCompound.setInteger(SilkWormStats.STAT_LIFESPAN_TTL_MOTH, span.getActualCocoonLifespan());
        worm.stackTagCompound.setString(SilkWormStats.STAT_LIFESPAN_TTL_COCOON_LABEL, SilkWormManagers.LifeManager.whereDoesThisFall(span.getActualWormLifespan()).getCategoryLabel());
        worm.stackTagCompound.setString(SilkWormStats.STAT_LIFESPAN_TTL_MOTH_LABEL, SilkWormManagers.LifeManager.whereDoesThisFall(span.getActualCocoonLifespan()).getCategoryLabel());
        worm.stackTagCompound.setInteger(SilkWormStats.STAT_LIFESPAN_CURRENT, span.getActualWormLifespan());
    }

    public static int getWormLifeSpanMax(ItemStack worm) {
        int max = -1;
        if (worm.getItemDamage() == SilkWormGrowthStages.WORM.getMeta()) {
            max = worm.stackTagCompound.getInteger(SilkWormStats.STAT_LIFESPAN_TTL_COCOON);
        } else if (worm.getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()) {
            max = worm.stackTagCompound.getInteger(SilkWormStats.STAT_LIFESPAN_TTL_MOTH);
        }
        return max;
    }

    public static void setWormCurrentLifeToCocoon(ItemStack worm){
        setWormCurrentLife(worm,getWormCocoonLength(worm));
    }
    
    public static void setWormCurrentLife(ItemStack worm, int life){
        worm.stackTagCompound.setInteger(SilkWormStats.STAT_LIFESPAN_CURRENT,life);
    }
    
    public static int getWormCurrentLife(ItemStack worm) {
        return worm.stackTagCompound.getInteger(SilkWormStats.STAT_LIFESPAN_CURRENT);
    }

    public static void setWormLifeStateNormal(ItemStack worm) {
        ISilkWormLifeState state = (ISilkWormLifeState) SilkWormManagers.LifeStates.getObject("Normal");
        setWormLifeState(worm,state);
    }

    public static void setWormLifeState(ItemStack worm, ISilkWormLifeState state) {
        worm.stackTagCompound.setString(SilkWormStats.STAT_LIFESTATE, state.getLifeStateName());
    }

    public static String getWormGender(ItemStack worm) {
        return worm.stackTagCompound.getString(SilkWormStats.STAT_GENDER);
    }

    public static String getWormLifeState(ItemStack worm) {
        return worm.stackTagCompound.getString(SilkWormStats.STAT_LIFESTATE);
    }

    public static int ConvertMinutesToTicks(int minutes) {
        return (minutes * 60) * 20;
    }
    
    public static int ConvertSecondsToTicks(int seconds){
        return seconds * 20;
    }
}
