package denoflionsx.plugins.BlueSilkWorm.Helpers;

import denoflionsx.core.core;
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
        setWormLifeState(worm, (ISilkWormLifeState) SilkWormManagers.LifeStates.getObject("Normal"));
        setWormLifeSpan(worm, SilkWormManagers.LifeManager.getNewLife());
        setWormGender(worm, SilkWormManagers.LifeManager.getNewGender());
        setWormTranform(worm, getWormLifeSpanInt(worm) / 2);
    }

    public static boolean isWormValid(ItemStack worm) {
        return worm.stackTagCompound != null;
    }

    public static void progressWorm(ItemStack worm) {
        int current = getWormLifeSpanInt(worm);
        if (current < 0) {
            return;
        }
        int trans = getWormTransform(worm);
        if (current == trans) {
            worm.setItemDamage(SilkWormGrowthStages.COCOON.getMeta());
        }
        if (current == 0) {
            worm.setItemDamage(SilkWormGrowthStages.MOTH.getMeta());
        }
        current--;
        setWormLifeSpanInt(worm, current);
    }

    public static void setWormGender(ItemStack worm, ISilkWormGender gender) {
        worm.stackTagCompound.setString(SilkWormStats.STAT_GENDER, gender.getGender());
    }

    public static void setWormTranform(ItemStack worm, int trans) {
        worm.stackTagCompound.setInteger(SilkWormStats.STAT_TRANSITION, trans);
    }

    public static int getWormTransform(ItemStack worm) {
        return worm.stackTagCompound.getInteger(SilkWormStats.STAT_TRANSITION);
    }

    public static void setWormLifeSpanInt(ItemStack worm, int lifespan) {
        worm.stackTagCompound.setInteger(SilkWormStats.STAT_LIFESPAN, lifespan);
    }

    public static void setWormLifeState(ItemStack worm, ISilkWormLifeState state) {
        worm.stackTagCompound.setString(SilkWormStats.STAT_LIFESPAN_LABEL, state.getLifeStateName());
    }

    public static void setWormLifeSpan(ItemStack worm, ISilkWormLifeSpan span) {
        worm.stackTagCompound.setString(SilkWormStats.STAT_LIFESPAN_LABEL, span.getLifeSpanName());
        setWormLifeSpanInt(worm, span.getLifeLength());
    }

    public static int getWormLifeSpanInt(ItemStack worm) {
        return worm.stackTagCompound.getInteger(SilkWormStats.STAT_LIFESPAN);
    }

    public static String getWormGender(ItemStack worm) {
        return worm.stackTagCompound.getString(SilkWormStats.STAT_GENDER);
    }

    public static String getWormLifeSpanLabel(ItemStack worm) {
        return worm.stackTagCompound.getString(SilkWormStats.STAT_LIFESPAN_LABEL);
    }

    public static String getWormLifeState(ItemStack worm) {
        return worm.stackTagCompound.getString(SilkWormStats.STAT_LIFESTATE);
    }

    public static int ConvertMinutesToTicks(int minutes) {
        return (minutes * 60) * 20;
    }
}
