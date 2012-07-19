package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class pluginUpdater extends pluginBase
{
    public static String vfile = "https://dl.dropbox.com/u/23892866/PluginsforForestry/Version/PFFVersion.cfg";
    private Config compare = new Config("PFFVersion.cfg");

    public pluginUpdater()
    {
        this.name = "pluginUpdater";
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.loaded = this.init();
        }
    }

    protected void defaults() {}

    protected boolean init()
    {
        try
        {
            core.print("Checking for updates...");
            this.compare.deleteConfig();
            this.saveUrl(Config.ConfigDir + "PFFVersion.cfg", vfile);
            this.compare.readFile();

            if (core.modVersion().equals(this.compare.getOption("Version")))
            {
                core.print("PFF is up to date!");
            }
            else
            {
                String var1 = "[PluginsforForestry]: New Version " + this.compare.getOption("Version") + " available!";
                core.print(var1);

                if (core.isClient())
                {
                    ;
                }
            }
        }
        catch (Exception var2)
        {
            var2.printStackTrace();
        }

        return true;
    }

    protected void recipes() {}

    public void saveUrl(String var1, String var2) throws MalformedURLException, IOException
    {
        BufferedInputStream var3 = null;
        FileOutputStream var4 = null;

        try
        {
            var3 = new BufferedInputStream((new URL(var2)).openStream());
            var4 = new FileOutputStream(var1);
            byte[] var5 = new byte[1024];
            int var6;

            while ((var6 = var3.read(var5, 0, 1024)) != -1)
            {
                var4.write(var5, 0, var6);
            }
        }
        finally
        {
            if (var3 != null)
            {
                var3.close();
            }

            if (var4 != null)
            {
                var4.close();
            }
        }
    }
}
