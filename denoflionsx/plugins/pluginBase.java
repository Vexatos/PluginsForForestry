package denoflionsx.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;

public abstract class pluginBase {

    public Config config = null;
    protected String mod = "";
    protected String modid = "";
    protected boolean hooked = false;
    public Map<String, ItemStack> items = new HashMap();
    public Map<String, ItemStack> blocks = new HashMap();
    protected ArrayList<baseModule> modules = new ArrayList();
    protected String name = "";
    public boolean loaded = false;
    protected boolean hasModules = false;

    public pluginBase() {
    }

    public void addBlock(String mod, String field, String name, int meta) {
        this.blocks.put(name, new ItemStack(denLib.getBlock(mod, field), 1, meta));
    }

    public void addBlock(String name, ItemStack i) {
        this.blocks.put(name, i);
    }

    public void addItem(String mod, String field, String name, int meta) {
        this.items.put(name, new ItemStack(denLib.getItem(mod, field), 1, meta));
    }

    public void addItem(String field, String name) {
        this.items.put(name, new ItemStack(denLib.getItem(this.mod, field), 1, 0));
    }

    public void addItem(String field) {
        this.items.put(field, new ItemStack(denLib.getItem(this.mod, field), 1, 0));
    }

    public Block getBlock(String name) {
        return Block.blocksList[this.blocks.get(name).itemID];
    }

    public ItemStack getBlockItemStack(String name) {
        return this.blocks.get(name);
    }

    public boolean getOptionBool(String key) {
        return this.config.getOptionBool(key);
    }

    public float getOptionFloat(String key) {
        return this.config.getOptionFloat(key);
    }

    public Integer getOptionInt(String key) {
        return this.config.getOptionInt(key);
    }

    public void addItem(String name, ItemStack i) {
        this.items.put(name, i);
    }

    public ItemStack get(String name) {
        return this.items.get(name).copy();
    }

    public String getName() {
        return this.name;
    }

    public void register() {
        if (!this.loaded) {
            this.defaults();
            this.runConfig();
            if (this.loaded = this.init()) {
                this.recipes();
                this.registerModules();
                core.print(this.name + " loaded!");
            }
        }
    }

    public void registerModules() {
        if (this.hasModules) {
            for (baseModule b : this.modules) {
                if (!b.hasLoaded) {
                    b.init();
                    b.hasLoaded = true;
                }
            }
        }
    }

    protected boolean detect() {
        boolean d = denLib.detect(this.mod);
        if (!d) {
            //core.print(this.mod + " not found!");
        }
        return d;
    }

    protected abstract void recipes();

    protected abstract boolean init();

    protected abstract void defaults();

    protected void runConfig() {
        if (this.config != null) {
            this.config.writeConfig();
            this.config.readFile();
        }
    }
}
