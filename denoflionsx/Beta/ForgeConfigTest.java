package denoflionsx.Beta;

import java.io.File;
import net.minecraftforge.common.Configuration;

public class ForgeConfigTest {
    
    public static Configuration config;
    
    public static void set(){
        config = new Configuration(new File("Test.cfg"));
        int test = config.getOrCreateIntProperty("test", Configuration.CATEGORY_ITEM, 1).getInt();
        int test2 = config.getOrCreateIntProperty("test2", Configuration.CATEGORY_GENERAL, 42).getInt();
        config.save();
        config.load();
    }
    
}
