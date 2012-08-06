package net.minecraft.src.denoflionsx.Achievements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.Achievement;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.forge.AchievementPage;
import net.minecraft.src.forge.MinecraftForge;

public class PfFAchievement extends Achievement {

    public static final int baseAchievementID = 4999;
    public static int coords[] = new int[]{1, -2};
    public static int currentAchievementID = baseAchievementID;
    public static HashMap<Integer, PfFAchievement> achievements = new HashMap();
    public static ArrayList<PfFAchievement> itemAchievements = new ArrayList();
    public static boolean hasItemAchievement = true;
    public static int internalAchievmentID = -1;
    //---------------------------------------------------------------------
    private String desc;
    private String req;
    private int x;
    private int y;

    public PfFAchievement(int par1, String par2Str, int par3, int par4, ItemStack par5ItemStack, Achievement par6Achievement, String desc, String req) {
        super(par1, par2Str, par3, par4, par5ItemStack, par6Achievement);
        this.desc = desc;
        this.req = req;
    }

    public String getDesc() {
        return desc;
    }

    public String getReq() {
        return req;
    }

    public static int getNewID() {
        currentAchievementID++;
        int newID = currentAchievementID;
        return newID;
    }

    public static int getNewInternalID() {
        internalAchievmentID++;
        int newID = internalAchievmentID;
        return newID;
    }

    public static int[] getNewCoords() {
        int newCoords[] = new int[2];
        coords[0]++;
        newCoords[0] = coords[0];
        newCoords[1] = coords[1];
        return newCoords;
    }

    public static void registerAchievements() {
        PfFAchievement.Achievements.loadEnum();
        MinecraftForge.registerAchievementPage(new AchievementPage("Plugins For Forestry", (Achievement[]) achievements.values().toArray(new Achievement[achievements.size()])));
    }

    public static enum Achievements {

        BETA(getNewID(), "Beta", API.getItem("barrel"), "Test a PfF beta", "For Science!", true, false),
        PFF(getNewID(), "Install", API.getItem("liquidpeatbar"), "Install PfF", "More fuel!", false, false),
        HAMMER(getNewID(), "Hammer", API.getItem("blacksmithhammer"),PfFAchievement.Achievements.PFF, "Obtained a Blacksmith Hammer.", "Stop. Hammertime!", false, true);
        
        private PfFAchievement achieve;
        private boolean achievementGranted = false;

        private Achievements(int par1, String par2Str, ItemStack par5ItemStack, String desc, String req, boolean isSpecial, boolean isItem) {
            Achievement n = (Achievement) null;
            int XY[] = getNewCoords();
            PfFAchievement a = new PfFAchievement(par1, par2Str, XY[0], XY[1], par5ItemStack, n, desc, req);
            if (isSpecial) {
                a.setSpecial();
            }
            if (isItem) {
                itemAchievements.add(a);
            }
            achievements.put(getNewInternalID(), a);
            achieve = a;
            ModLoader.addAchievementDesc(a, req, desc);
        }

        private Achievements(int par1, String par2Str, ItemStack par5ItemStack, PfFAchievement.Achievements par6Achievement, String desc, String req, boolean isSpecial, boolean isItem) {
            int XY[] = getNewCoords();
            PfFAchievement a = new PfFAchievement(par1, par2Str, XY[0], XY[1], par5ItemStack, par6Achievement.getAchieve(), desc, req);
            if (isSpecial) {
                a.setSpecial();
            }
            if (isItem) {
                itemAchievements.add(a);
            }
            achievements.put(getNewInternalID(), a);
            achieve = a;
            ModLoader.addAchievementDesc(a, req, desc);
        }

        public PfFAchievement getAchieve() {
            return achieve;
        }
       
        public static void loadEnum() {
            PfFAchievement.Achievements.values();
        }

        public boolean isAchievementGranted() {
            return achievementGranted;
        }

        public void grantAchievement(EntityPlayer player){
            player.addStat(this.achieve,1);
            this.achievementGranted = true;
        }

        
    }
}
