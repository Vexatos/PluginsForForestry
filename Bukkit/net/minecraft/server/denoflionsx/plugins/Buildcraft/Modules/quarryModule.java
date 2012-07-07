package net.minecraft.server.denoflionsx.plugins.Buildcraft.Modules;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import java.lang.reflect.Field;

public class quarryModule extends baseModule
{
    protected static int max;
    protected static String c = "buildcraft.factory.TileQuarry";
    protected static String f = "MAX_ENERGY";

    public quarryModule(pluginBase var1)
    {
        super(var1);
    }

    protected void init()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("quarryModule_Enabled")))
        {
            try
            {
                max = Integer.valueOf(this.parent.config.getOption("QuarryMaxBuffer")).intValue();
                Class var1 = Class.forName(c);
                Field var2 = var1.getField(f);
                int var3 = var2.getInt((Object)null);
                var2.setInt((Object)null, max);
            }
            catch (Exception var7)
            {
                core.print("Failed to hook BC quarry.");
                var7.printStackTrace();
            }
            finally
            {
                ;
            }
        }
    }

    public static void load(pluginBase var0)
    {
        quarryModule var1 = new quarryModule(var0);
        var1.register();
    }

    protected void defaults()
    {
        this.parent.config.addDefault("quarryModule_Enabled=false");
        this.parent.config.addDefault("QuarryMaxBuffer=32767");
    }

    protected void recipes() {}
}
