package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.EE.customEMCParser;
import net.minecraft.server.denoflionsx.plugins.EE.Modules.BuildcraftEMCModule;
import net.minecraft.server.denoflionsx.plugins.EE.Modules.ForestryEMCModule;
import net.minecraft.server.denoflionsx.plugins.EE.Modules.VanillaValues;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.server.Block;
import net.minecraft.server.Item;

public class pluginEE extends pluginBase
{
    private static Class EEMaps;
    private static Field alchemicalValues_Field;
    private static HashMap alchemicalValues = new HashMap();
    private Config values = new Config("pluginEE_CustomEMCValues.cfg");

    public pluginEE()
    {
        this.name = "pluginEE";
        this.mod = "mod_EE";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();
            this.values.writeConfig();
            this.values.readFile();

            if (!this.detect())
            {
                core.print("EE not found! Aborting EMC assignment tasks.");
                this.config.setOption("LoadIntegrationModules", "false");
                this.config.setOption("LoadCustomEMCValuesFile", "false");
            }

            if (this.getOptionBool("LoadCustomEMCValuesFile"))
            {
                readEMC(this.values);
            }

            if (this.getOptionBool("LoadIntegrationModules"))
            {
                VanillaValues.assignValues();

                if (denLib.detect("mod_Forestry"))
                {
                    ForestryEMCModule.load(this);
                }

                if (denLib.detect("mod_BuildCraftCore") && denLib.detect("mod_BuildCraftTransport") && denLib.detect("mod_BuildCraftCore") && denLib.detect("mod_BuildCraftTransport"))
                {
                    BuildcraftEMCModule.load(this);
                }
            }

            if (this.loaded = this.init())
            {
                this.recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    protected void defaults()
    {
        this.config.addDefault("[EE Plugin Options]");
        this.config.addDefault("LoadCustomEMCValuesFile=true");
        this.config.addDefault("LoadIntegrationModules=true");
        this.values.addDefault("[Define Custom EMC Here]");
        this.values.addDefault("# NameTag=ItemID,Damage Value,EMC Value");
    }

    protected boolean init()
    {
        if (!this.detect())
        {
            return this.hooked;
        }
        else
        {
            if (this.getOptionBool("LoadIntegrationModules"))
            {
                this.registerModules();
            }

            this.hooked = true;
            return this.hooked;
        }
    }

    public static void readEMC(Config var0)
    {
        customEMCParser var1 = new customEMCParser();
        Iterator var2 = var0.Options.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();
            String var4 = var3.getValue().toString();
            var1.parse(var4);
        }

        Iterator var11 = var1.Values.entrySet().iterator();

        while (var11.hasNext())
        {
            Entry var12 = (Entry)var11.next();
            HashMap var5 = (HashMap)var12.getValue();
            Integer var6 = Integer.valueOf(var12.getKey().toString());
            Iterator var7 = var5.entrySet().iterator();

            while (var7.hasNext())
            {
                Entry var8 = (Entry)var7.next();
                Integer var9 = Integer.valueOf(var8.getKey().toString());
                Integer var10 = Integer.valueOf(var8.getValue().toString());
                addEMC(var6.intValue(), var9.intValue(), var10.intValue());
            }
        }
    }

    protected static void addEMC(int var0, int var1, int var2)
    {
        try
        {
            try
            {
                EEMaps = Class.forName("ee.EEMaps");
                alchemicalValues_Field = EEMaps.getField("alchemicalValues");
                alchemicalValues = (HashMap)alchemicalValues_Field.get((Object)null);
                HashMap var3 = new HashMap();

                if (alchemicalValues.get(Integer.valueOf(var0)) != null)
                {
                    var3 = (HashMap)alchemicalValues.get(Integer.valueOf(var0));
                }

                var3.put(Integer.valueOf(var1), Integer.valueOf(var2));
                alchemicalValues.put(Integer.valueOf(var0), var3);
                alchemicalValues_Field.set((Object)null, alchemicalValues);
            }
            catch (Exception var7)
            {
                var7.printStackTrace();
            }
        }
        finally
        {
            ;
        }
    }

    protected void blacklistItem(Item var1)
    {
        try
        {
            EEMaps = Class.forName("ee.EEMaps");
            Field var2 = EEMaps.getField("alchemicalValues");
            var2.setAccessible(true);
            alchemicalValues = (HashMap)var2.get((Object)null);
            HashMap var3 = new HashMap();
            var3.put(Integer.valueOf(0), Integer.valueOf(0));
            alchemicalValues.remove(Integer.valueOf(var1.id));
            alchemicalValues.put(Integer.valueOf(var1.id), var3);
            var2.set((Object)null, alchemicalValues);
        }
        catch (Exception var7)
        {
            var7.printStackTrace();
        }
        finally
        {
            core.print("Removed EMC Value for " + var1.getName());
        }
    }

    protected void blacklistItem(Block var1)
    {
        try
        {
            EEMaps = Class.forName("ee.EEMaps");
            Field var2 = EEMaps.getField("alchemicalValues");
            var2.setAccessible(true);
            alchemicalValues = (HashMap)var2.get((Object)null);
            HashMap var3 = new HashMap();
            var3.put(Integer.valueOf(0), Integer.valueOf(0));
            alchemicalValues.remove(Integer.valueOf(var1.id));
            alchemicalValues.put(Integer.valueOf(var1.id), var3);
            var2.set((Object)null, alchemicalValues);
        }
        catch (Exception var7)
        {
            var7.printStackTrace();
        }
        finally
        {
            core.print("Removed EMC Value for " + var1.q());
        }
    }

    protected void killEEItem(String var1)
    {
        try
        {
            Class var2 = Class.forName("ee.EEItem");
            Field var3 = var2.getField(var1);
            var3.setAccessible(true);
            Item var4 = (Item)var3.get((Object)null);
            int var5 = var4.id;
            Item.byId[var5] = null;
        }
        catch (Exception var9)
        {
            var9.printStackTrace();
        }
        finally
        {
            core.print("Killed EE Item " + var1);
        }
    }

    private static void hookEE()
    {
        try
        {
            EEMaps = Class.forName("ee.EEMaps");
            alchemicalValues_Field = EEMaps.getField("alchemicalValues");
            alchemicalValues = (HashMap)alchemicalValues_Field.get((Object)null);
        }
        catch (Exception var1)
        {
            var1.printStackTrace();
        }
    }

    public static int getEEValue(int var0, int var1)
    {
        hookEE();

        if (alchemicalValues.get(Integer.valueOf(var0)) == null)
        {
            return 0;
        }
        else
        {
            HashMap var2 = (HashMap)alchemicalValues.get(Integer.valueOf(var0));

            if (var2.get(Integer.valueOf(var1)) == null)
            {
                return 0;
            }
            else
            {
                int var3 = ((Integer)var2.get(Integer.valueOf(var1))).intValue();
                return var3;
            }
        }
    }

    public static void forceSetValue(int var0, HashMap var1)
    {
        try
        {
            hookEE();
            alchemicalValues.put(Integer.valueOf(var0), var1);
            alchemicalValues_Field.set((Object)null, alchemicalValues);
        }
        catch (Exception var3)
        {
            ;
        }
    }

    public static int flatten(int[] var0, int var1)
    {
        int var2 = 0;
        int[] var3 = var0;
        int var4 = var0.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            int var6 = var3[var5];
            var2 += var6;
        }

        float var7 = (float)var2;
        float var8 = (float)var1;
        float var9 = var7 / var8;
        Double var10 = Double.valueOf(Math.ceil((double)var9));
        var2 = var10.intValue();
        return var2;
    }

    protected void recipes() {}
}
