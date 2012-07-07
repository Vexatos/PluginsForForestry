package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;

public abstract class pluginBase
{
    public Config config = null;
    protected String mod = "";
    public Map items = new HashMap();
    public Map blocks = new HashMap();
    protected ArrayList modules = new ArrayList();
    protected String name = "";
    public boolean loaded = false;
    protected boolean hooked = false;
    protected boolean isBeta = false;
    protected boolean hasModules = false;
    public boolean isLate = false;

    public void addBlock(String var1, String var2, String var3, int var4)
    {
        this.blocks.put(var3, new ItemStack(denLib.getBlock(var1, var2), 1, var4));
    }

    public void addBlock(String var1, ItemStack var2)
    {
        this.blocks.put(var1, var2);
    }

    public void addItem(String var1, String var2, String var3, int var4)
    {
        this.items.put(var3, new ItemStack(denLib.getItem(var1, var2), 1, var4));
    }

    public Block getBlock(String var1)
    {
        return Block.byId[((ItemStack)this.blocks.get(var1)).id];
    }

    public boolean getOptionBool(String var1)
    {
        return denLib.convertToBoolean(this.config.getOption(var1));
    }

    public Integer getOptionInt(String var1)
    {
        return Integer.valueOf(this.config.getOption(var1));
    }

    public void addItem(String var1, ItemStack var2)
    {
        this.items.put(var1, var2);
    }

    public ItemStack get(String var1)
    {
        return ((ItemStack)this.items.get(var1)).cloneItemStack();
    }

    public String getName()
    {
        return this.name;
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();

            if (this.loaded = this.init())
            {
                this.recipes();
            }
        }
    }

    public void registerModules()
    {
        if (this.hasModules)
        {
            Iterator var1 = this.modules.iterator();

            while (var1.hasNext())
            {
                baseModule var2 = (baseModule)var1.next();
                var2.init();
            }
        }
    }

    protected boolean detect()
    {
        return denLib.detect(this.mod);
    }

    protected abstract void recipes();

    protected abstract boolean init();

    protected abstract void defaults();

    protected void runConfig()
    {
        this.config.writeConfig();
        this.config.readFile();
    }
}
