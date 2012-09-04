package denoflionsx.Achievements;

import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import denoflionsx.API.API;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;

public class AchievementReg {

    private Config list = new Config(getClass().getResourceAsStream("MasterList.cfg"));

    public AchievementReg registerAchievements() {
        ArrayList<String> MasterList = list.dumpValues();
        for (String s : MasterList) {
            core.print(s);
            Config achievement = new Config(getClass().getResourceAsStream(s));
            int ID = achievement.getOptionInt("id");
            String name = achievement.getOption("name");
            String desc = achievement.getOption("desc");
            ItemStack icon = API.getItem(achievement.getOption("icon"));
            String itemtrigger_name = achievement.getOption("itemtrigger");
            ItemStack itemtrigger;
            if (itemtrigger_name.equals("any")){
                itemtrigger = null;
            }else{
                itemtrigger = API.getItem(itemtrigger_name);
            }
            boolean isCrafting = achievement.getOptionBool("iscrafting");
            boolean isItem = achievement.getOptionBool("isitem");
            boolean isSpecial = achievement.getOptionBool("isspecial");
            int coords[] = new int[]{achievement.getOptionInt("xcoord"), achievement.getOptionInt("ycoord")};
            int reqid = achievement.getOptionInt("required");
            PfFAchievement required;
            if (reqid != -1) {
                required = PfFAchievement.achievements.get(reqid);
            }else{
                required = null;
            }
            PfFAchievement achievement2 = new PfFAchievement(ID + 5000, denLib.toLowerCaseNoSpaces(name), coords[0], coords[1], icon, required,name,desc,isItem,isCrafting,isSpecial,itemtrigger);
        }
        return this;
    }
}
