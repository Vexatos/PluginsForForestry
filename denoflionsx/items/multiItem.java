package denoflionsx.items;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import denoflionsx.API.PfFManagers;
import java.util.HashMap;
import net.minecraft.src.*;
import denoflionsx.core.core;
import denoflionsx.PluginsforForestry;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;
import java.util.List;

public class multiItem extends Item {

    protected HashMap<Integer, String> itemMap = new HashMap();
    protected HashMap<Integer, Integer> textureMap = new HashMap();
    protected HashMap<Integer, shinyObject> shinyMap = new HashMap();
    public HashMap<String, Integer> metaMap = new HashMap();

    public multiItem(int par1, String name) {
        super(par1);
        this.setHasSubtypes(true);
        this.setItemName(name);
        this.setMaxDamage(0);
        this.setTabToDisplayOn(CreativeTabs.tabMisc);
    }

    @Override
    public int getIconFromDamage(int par1) {
        if (this.textureMap.get(par1) != null) {
            return this.textureMap.get(par1);
        }
        return 0;
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        ItemStack p;
        if (par1ItemStack == null) {
            p = new ItemStack(this, 1, 0);
        } else {
            p = par1ItemStack;
        }
        return getMetaName(p);
    }

    public String getMetaName(ItemStack par1ItemStack) {
        if (par1ItemStack != null) {
            int dmg = par1ItemStack.getItemDamage();
            String dname = this.itemMap.get(Integer.valueOf(dmg));
            if (dname != null) {
                return dname;
            }
        }
        return "";
    }

    public void add(String name, int dmg, int texid) {
        add(denLib.toLowerCaseNoSpaces(name), dmg, texid, name);
    }

    public void add(String name, int dmg, int texid, String displayname) {
        itemMap.put(dmg, "item." + name);
        textureMap.put(dmg, texid);
        // Default shinyObject returns normal values.
        shinyMap.put(dmg, new shinyObject());
        if (core.isClient()){
            FMLWrapper.MODE.FML.addName("item." + name + ".name", displayname);
        }
        // Register with API.
        PfFManagers.ItemManager.registerItem(name, this, dmg);
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (String name : this.itemMap.values()){
            par3List.add(PfFManagers.ItemManager.getItem(denLib.removeDotItemFromName(name)));
        }
    }

    public void add(String name, int dmg, int texid, String displayname, boolean shiny) {
        add(name, dmg, texid, displayname);
        this.setShiny(dmg, shiny);
    }

    public void setShiny(int dmg, boolean t) {
        if (t) {
            this.shinyMap.put(dmg, new shinyObject(true));
        } else {
            this.shinyMap.put(dmg, new shinyObject());
        }
    }

    public int invSearch(ItemStack par1, EntityPlayer par2) {
        for (int i = 0; i < par2.inventory.getSizeInventory(); i++) {
            ItemStack t = par2.inventory.getStackInSlot(i);
            if (t != null) {
                if (t.isItemEqual(par1) && t.stackSize < this.maxStackSize) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int emptySpace(EntityPlayer par1) {
        for (int i = 0; i < par1.inventory.getSizeInventory(); i++) {
            if (par1.inventory.getStackInSlot(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        if (this.shinyMap.get(par1ItemStack.getItemDamage()) != null) {
            return this.shinyMap.get(par1ItemStack.getItemDamage()).getShiny();
        } else {
            return new shinyObject().getShiny();
        }
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }
}
