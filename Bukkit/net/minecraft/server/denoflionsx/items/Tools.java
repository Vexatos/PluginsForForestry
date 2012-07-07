package net.minecraft.server.denoflionsx.items;

import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.pluginCore;
import net.minecraft.server.Block;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityCow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.World;

public class Tools extends multiItem
{
    public Tools(int var1, String var2)
    {
        super(var1, var2);
    }

    public boolean onLeftClickEntity(ItemStack var1, EntityHuman var2, Entity var3)
    {
        ItemStack var4 = new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Bag")).intValue());

        if (var3 instanceof EntityCow)
        {
            var2.a(var4.cloneItemStack(), false);
        }

        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack a(ItemStack var1, World var2, EntityHuman var3)
    {
        MovingObjectPosition var4 = this.a(var2, var3, true);

        if (var4 == null)
        {
            return var1;
        }
        else
        {
            int var5 = var4.b;
            int var6 = var4.c;
            int var7 = var4.d;

            if (var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Wooden Bucket")).intValue())))
            {
                if (var2.getMaterial(var5, var6, var7) == Material.WATER && var2.getData(var5, var6, var7) == 0)
                {
                    var2.setTypeId(var5, var6, var7, 0);
                    return new ItemStack(this, 1, ((Integer)this.metaMap.get("Filled Wooden Bucket")).intValue());
                }
            }
            else if (var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Filled Wooden Bucket")).intValue())))
            {
                if (var4.face == 0)
                {
                    --var6;
                }

                if (var4.face == 1)
                {
                    ++var6;
                }

                if (var4.face == 2)
                {
                    --var7;
                }

                if (var4.face == 3)
                {
                    ++var7;
                }

                if (var4.face == 4)
                {
                    --var5;
                }

                if (var4.face == 5)
                {
                    ++var5;
                }

                if (var2.isEmpty(var5, var6, var7) || var2.getMaterial(var5, var6, var7) == Material.WATER)
                {
                    var2.setTypeIdAndData(var5, var6, var7, Block.WATER.id, 0);
                    return new ItemStack(this, 1, ((Integer)this.metaMap.get("Wooden Bucket")).intValue());
                }
            }
            else if (var1.doMaterialsMatch(new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milker")).intValue())))
            {
                ;
            }

            return var1;
        }
    }
}
