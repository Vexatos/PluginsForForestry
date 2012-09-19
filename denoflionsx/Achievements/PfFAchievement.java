package denoflionsx.Achievements;

import net.minecraft.src.Achievement;
import net.minecraft.src.ItemStack;

public class PfFAchievement extends Achievement{

    public PfFAchievement(int par1, String par2Str, int par3, int par4, ItemStack par5ItemStack, Achievement par6Achievement) {
        super(par1, par2Str, par3, par4, par5ItemStack, par6Achievement);
    }
    
    public static enum ACHIEVEMENT{
        COW_GOES_MOO,
        FOR_THE_LOVE_OF,
        EVEN_SWEETER,
        JUICY_FRUITS,
        ALL_COOPERD_UP,
        TEAM_REDUNDANCY_TEAM,
        DANGER_WILL_ROBINSON_DANGER,
        CHILLING;
    }
    
}
