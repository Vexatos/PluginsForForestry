package net.minecraft.server.denoflionsx.denLib.Config;

import net.minecraft.server.denoflionsx.denLib.denLib;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Config
{
    public HashMap Options = new HashMap();
    protected ArrayList defaults = new ArrayList();
    protected ArrayList rawFile = new ArrayList();
    public static final String ConfigDir = "./" + File.separator + "config" + File.separator + "denoflionsx" + File.separator;
    protected String ConfigFile = "";

    public Config(String var1)
    {
        this.ConfigFile = ConfigDir + var1;
    }

    public void readFile()
    {
        ArrayList var1 = FileRead.Read(new String[] {this.ConfigFile});
        Iterator var2 = var1.iterator();

        while (var2.hasNext())
        {
            String var3 = (String)var2.next();
            this.parseLine(var3);
        }
    }

    public void setOption(String var1, String var2)
    {
        denLib.print("Set Option " + var1 + " to " + var2);
        this.Options.put(var1, var2);
    }

    public String getOption(String var1)
    {
        String var2 = (String)this.Options.get(var1);

        if (var2 != null)
        {
            return var2;
        }
        else
        {
            denLib.print("Attempting to update config file...");
            Iterator var3 = this.defaults.iterator();
            String var4;

            do
            {
                if (!var3.hasNext())
                {
                    return "";
                }

                var4 = (String)var3.next();
            }
            while (!var4.contains(var1));

            this.parseLine(var4);
            var2 = (String)this.Options.get(var1);
            (new File(this.ConfigFile)).delete();
            FileWrite.write(this.ConfigFile, this.rawFile);
            return var2;
        }
    }

    public void parseLine(String var1)
    {
        char var2 = "=".charAt(0);
        this.rawFile.add(var1);

        if (!var1.substring(0, 1).equals("#") && !var1.substring(0, 1).equals("["))
        {
            String[] var3 = var1.split("=");

            if (var3.length == 2)
            {
                this.setOption(var3[0], var3[1]);
            }
        }
    }

    public void addDefault(String var1)
    {
        this.defaults.add(var1);
    }

    public void writeConfig()
    {
        if (!(new File(this.ConfigFile)).exists())
        {
            (new File(ConfigDir)).mkdir();
            FileWrite.write(this.ConfigFile, this.defaults);
            denLib.print("Wrote config file: " + this.ConfigFile);
        }
    }
}
