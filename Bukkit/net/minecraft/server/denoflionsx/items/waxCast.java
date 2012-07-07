package net.minecraft.server.denoflionsx.items;

import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.Forestry.Modules.blueswaxModule;
import java.util.HashMap;
import java.util.List;
import net.minecraft.server.Block;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.World;

public class waxCast extends multiItem
{
    HashMap dropMap = new HashMap();

    public waxCast(int var1, String var2)
    {
        super(var1, var2);
        this.dropMap.put("Filled Wax Cast", new ItemStack(Block.ICE));
        this.dropMap.put("Filled Wax Cast_Red", new ItemStack(Block.ICE));
        this.dropMap.put("Lava Cast", new ItemStack(Block.OBSIDIAN));
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack a(ItemStack var1, World var2, EntityHuman var3)
    {
        if (!var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Wax Cast")).intValue())) && !var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Refractory Cast")).intValue())))
        {
            if (!var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Filled Wax Cast")).intValue())) && !var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Filled Wax Cast_Red")).intValue())))
            {
                if (var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Lava Cast")).intValue())))
                {
                    if (var3.inventory.c(new ItemStack(this, 1, ((Integer)this.metaMap.get("Rod of Freezing")).intValue())))
                    {
                        --var1.count;
                        var3.a(((ItemStack)this.dropMap.get("Lava Cast")).cloneItemStack(), false);
                    }
                }
                else if (var1.doMaterialsMatch(new ItemStack(this, 1, ((Integer)this.metaMap.get("Test")).intValue())))
                {
                    MovingObjectPosition var4 = this.a(var2, var3, true);
                    int[] var5 = new int[] {var4.b, var4.c, var4.d};
                    ++var5[1];
                    var2.setTypeIdAndData(var5[0], var5[1], var5[2], blueswaxModule.thatch.id, 0);
                }
            }
            else if (var3.inventory.c(new ItemStack(this, 1, ((Integer)this.metaMap.get("Rod of Freezing")).intValue())))
            {
                --var1.count;
                var3.a(((ItemStack)this.dropMap.get("Filled Wax Cast")).cloneItemStack(), false);
            }

            return var1;
        }
        else
        {
            return this.customContainer(var1, var2, var3);
        }
    }

    public void tooltips(ItemStack var1, List var2)
    {
        if (var1.getData() == ((Integer)this.metaMap.get("Wax Cast")).intValue())
        {
            var2.add("Fill me with water!");
        }
        else if (var1.getData() == ((Integer)this.metaMap.get("Refractory Cast")).intValue())
        {
            var2.add("Fill me with water or lava!");
        }
        else if (var1.getData() == ((Integer)this.metaMap.get("Rod of Freezing")).intValue())
        {
            var2.add("Used with Wax Casts.");
        }
    }
}
