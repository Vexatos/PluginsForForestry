package net.minecraft.src.denoflionsx.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.denLib;

public abstract class pluginBase {

    public Config config = null;
    protected String mod = "";
    public Map<String, ItemStack> items = new HashMap();
    public Map<String, ItemStack> blocks = new HashMap();
    protected ArrayList<baseModule> modules = new ArrayList();
    protected String name = "";
    public boolean loaded = false;
    protected boolean hooked = false;
    protected boolean hasModules = false;

    public pluginBase() {
        
    }

    public void addBlock(String mod, String field, String name, int meta) {
        this.blocks.put(name, new ItemStack(denLib.getBlock(mod, field), 1, meta));
    }

    public void addBlock(String name, ItemStack i){
        this.blocks.put(name, i);
    }
    
    public void addItem(String mod, String field, String name, int meta) {
        this.items.put(name, new ItemStack(denLib.getItem(mod, field), 1, meta));
    }
    
    public Block getBlock(String name){
        return Block.blocksList[this.blocks.get(name).itemID];
    }
    
    public boolean getOptionBool(String key){
        return denLib.convertToBoolean(this.config.getOption(key));
    }
    
    public Integer getOptionInt(String key){
        return Integer.valueOf(this.config.getOption(key));
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
            if (this.loaded = this.init()) {
                this.recipes();
            }
        }
    }

    public void registerModules() {
        if (this.hasModules) {
            for (baseModule b : this.modules) {
                b.init();
            }
        }
    }

    protected boolean detect() {
        boolean d = denLib.detect(this.mod);
        if (!d){
            core.print(this.mod + " not found!");
        }
        return d;
    }

    protected abstract void recipes();

    protected abstract boolean init();

    protected abstract void defaults();

    protected void runConfig() {
        this.config.writeConfig();
        this.config.readFile();
    }
}
