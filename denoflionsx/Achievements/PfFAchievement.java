package denoflionsx.Achievements;

import denoflionsx.denLib.FMLWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.src.*;
import net.minecraftforge.common.AchievementPage;

public class PfFAchievement extends Achievement {

    private static final int baseAchievementID = 4999;
    public static HashMap<Integer, PfFAchievement> achievements = new HashMap();
    public static ArrayList<PfFAchievement> itemAchievements = new ArrayList();
    public static ArrayList<PfFAchievement> craftAchievements = new ArrayList();
    public static ArrayList<PfFAchievement> freeAchievements = new ArrayList();
    public static final Achievement unlinked = (Achievement) null;
    //---------------------------------------------------------------------
    private boolean isAchievementGranted = false;
    
    public static void registerAchievements(){
        AchievementReg reg = new AchievementReg().registerAchievements();
        AchievementPage PluginsforForestry = new AchievementPage("Plugins for Forestry",(PfFAchievement[]) achievements.values().toArray(new PfFAchievement[achievements.size()]));
        FMLWrapper.MODE.FML.addAchievementPage(PluginsforForestry);
    }
    
    public PfFAchievement(int par1, String par2Str, int par3, int par4, ItemStack par5ItemStack, Achievement par6Achievement, String name, String desc, boolean isItemAchievement, boolean isCraftingAchievement, boolean isSpecial, ItemStack target) {
        super(par1, par2Str, par3, par4, par5ItemStack, par6Achievement);
        ModLoader.addAchievementDesc(this, name, desc);
        if (isSpecial){
            this.setSpecial();
        }
        if (isItemAchievement){
            itemAchievements.add(this);
            if (target == null){
                freeAchievements.add(this);
            }
        }
        if (isCraftingAchievement){
            craftAchievements.add(this);
        }
        achievements.put(par1 - 5000,this);
    }
    
    public void grantAchievement(EntityPlayer Player){
        if (!this.isAchievementGranted){
            Player.addStat(this,1);
            this.isAchievementGranted = true;
        }
    }
}
