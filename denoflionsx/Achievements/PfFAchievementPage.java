package denoflionsx.Achievements;

import net.minecraft.src.Achievement;
import net.minecraftforge.common.AchievementPage;

public class PfFAchievementPage extends AchievementPage{

    public PfFAchievementPage(String name, Achievement... achievements) {
        super(name, achievements);
    }
    
    public static enum PAGES{
        ACHIEVEMENTS,
        CREDITS,
        DONATORS;
    }
    
}
