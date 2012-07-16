package net.minecraft.server.denoflionsx.denLib.item_templates;

import net.minecraft.server.denoflionsx.API.PFFItems;
import net.minecraft.server.denoflionsx.core.core;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forge.ITextureProvider;
import java.util.HashMap;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.World;

public class multiItem extends Item implements ITextureProvider
{
    protected HashMap itemMap = new HashMap();
    protected HashMap textureMap = new HashMap();
    protected HashMap stackMap = new HashMap();
    protected HashMap containerMap = new HashMap();
    protected HashMap shinyMap = new HashMap();
    public HashMap metaMap = new HashMap();
    protected int meta;

    public multiItem(int var1, String var2)
    {
        super(var1);
        this.a(true);
        this.a(var2);
        this.setMaxDurability(0);
    }

    public String a(ItemStack var1)
    {
        return this.getMetaName(var1);
    }

    public String getMetaName(ItemStack var1)
    {
        if (var1 != null)
        {
            int var2 = var1.getData();
            String var3 = (String)this.itemMap.get(Integer.valueOf(var2));

            if (var3 != null)
            {
                this.e(((Integer)this.stackMap.get(Integer.valueOf(var2))).intValue());
                this.meta = var2;
                return var3;
            }
        }

        return "";
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void d(ItemStack var1, World var2, EntityHuman var3)
    {
        super.d(var1, var2, var3);
        this.meta = var1.getData();
        this.e(((Integer)this.stackMap.get(Integer.valueOf(this.meta))).intValue());
    }

    public Item j()
    {
        return (Item)this.containerMap.get(Integer.valueOf(this.meta));
    }

    public void add(String var1, int var2, int var3, String var4)
    {
        this.itemMap.put(Integer.valueOf(var2), "item." + var1);
        this.stackMap.put(Integer.valueOf(var2), Integer.valueOf(64));
        this.textureMap.put(Integer.valueOf(var2), Integer.valueOf(var3));
        this.containerMap.put(Integer.valueOf(var2), (Object)null);
        this.shinyMap.put(Integer.valueOf(var2), new shinyObject());

        if (core.isClient())
        {
            ModLoader.addLocalization(this.getMetaName(new ItemStack(this, 1, var2)) + ".name", var4);
        }

        PFFItems.registerItem(var1, this, var2);
    }

    public void add(String var1, int var2, int var3, String var4, int var5)
    {
        this.add(var1, var2, var3, var4);
        this.stackMap.put(Integer.valueOf(var2), Integer.valueOf(var5));
    }

    public void add(String var1, int var2, int var3, String var4, int var5, Item var6)
    {
        this.add(var1, var2, var3, var4, var5);
        this.containerMap.put(Integer.valueOf(var2), var6);
    }

    public void add(String var1, int var2, int var3, String var4, int var5, Item var6, boolean var7)
    {
        this.add(var1, var2, var3, var4, var5, var6);
        this.setShiny(var2, var7);
    }

    public void add(String var1, int var2, int var3, String var4, int var5, boolean var6)
    {
        this.add(var1, var2, var3, var4, var5);
        this.setShiny(var2, var6);
    }

    public void add(String var1, int var2, int var3, String var4, boolean var5)
    {
        this.add(var1, var2, var3, var4);
        this.setShiny(var2, var5);
    }

    public void setShiny(int var1, boolean var2)
    {
        if (var2)
        {
            this.shinyMap.put(Integer.valueOf(var1), new shinyObject(true));
        }
        else
        {
            this.shinyMap.put(Integer.valueOf(var1), new shinyObject());
        }
    }

    public ItemStack customContainer(ItemStack var1, World var2, EntityHuman var3)
    {
        MovingObjectPosition var4 = this.a(var2, var3, true);

        if (var4 == null)
        {
            return var1;
        }
        else
        {
            HashMap var5 = new HashMap();
            var5.put("x", Integer.valueOf(var4.b));
            var5.put("y", Integer.valueOf(var4.c));
            var5.put("z", Integer.valueOf(var4.d));
            var5.put("id", Integer.valueOf(var2.getTypeId(((Integer)var5.get("x")).intValue(), ((Integer)var5.get("y")).intValue(), ((Integer)var5.get("z")).intValue())));
            var5.put("meta", Integer.valueOf(var2.getData(((Integer)var5.get("x")).intValue(), ((Integer)var5.get("y")).intValue(), ((Integer)var5.get("z")).intValue())));
            LiquidContainer var6 = LiquidManager.getEmptyContainer(var1, new LiquidStack(((Integer)var5.get("id")).intValue(), 1));

            if (var6 == null)
            {
                return var1;
            }
            else
            {
                int var7 = this.invSearch(var6.filled, var3);

                if (var7 != -1)
                {
                    --var1.count;
                    ++var3.inventory.getItem(var7).count;
                    var2.setTypeIdAndData(((Integer)var5.get("x")).intValue(), ((Integer)var5.get("y")).intValue(), ((Integer)var5.get("z")).intValue(), 0, 0);
                    return var1;
                }
                else
                {
                    --var1.count;
                    int var8 = this.emptySpace(var3);
                    var3.inventory.setItem(var8, new ItemStack(var6.filled.getItem(), 1, var6.filled.getData()));
                    var2.setTypeIdAndData(((Integer)var5.get("x")).intValue(), ((Integer)var5.get("y")).intValue(), ((Integer)var5.get("z")).intValue(), 0, 0);
                    return var1;
                }
            }
        }
    }

    public int invSearch(ItemStack var1, EntityHuman var2)
    {
        for (int var3 = 0; var3 < var2.inventory.getSize(); ++var3)
        {
            ItemStack var4 = var2.inventory.getItem(var3);

            if (var4 != null && var4.doMaterialsMatch(var1) && var4.count < this.maxStackSize)
            {
                return var3;
            }
        }

        return -1;
    }

    public int emptySpace(EntityHuman var1)
    {
        for (int var2 = 0; var2 < var1.inventory.getSize(); ++var2)
        {
            if (var1.inventory.getItem(var2) == null)
            {
                return var2;
            }
        }

        return -1;
    }

    public String getTextureFile()
    {
        return "/denoflionsx/spritesheet.png";
    }
}
