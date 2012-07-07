package net.minecraft.server.denoflionsx.items;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.TankManager;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class milker
{
    protected EntityHuman player;
    protected TankManager tank;
    public static ItemStack[] validContainers = new ItemStack[] {ItemInterface.getItem("waxCapsule"), ItemInterface.getItem("canEmpty"), ItemInterface.getItem("refractoryEmpty"), new ItemStack(Item.BUCKET)};
    protected int itemId;
    protected boolean hasValidContainer = false;
    protected boolean hasInventorySpace = false;
    protected int containerid;
    protected LiquidContainer forFill;
    protected int index;

    public milker(EntityHuman var1, World var2, int var3, int var4, int var5)
    {
        this.player = var1;
        this.tank = new TankManager(var2, var3, var4, var5);
        core.print("" + this.tank.stored);
    }

    public void milk()
    {
        int var1 = -1;
        ItemStack[] var2 = this.player.inventory.items;
        int var3 = var2.length;
        int var4 = 0;

        while (var4 < var3)
        {
            ItemStack var5 = var2[var4];
            ++var1;

            if (var5 == null)
            {
                this.hasInventorySpace = true;
                this.index = var1;
            }

            ItemStack[] var6 = validContainers;
            int var7 = var6.length;
            int var8 = 0;

            while (true)
            {
                if (var8 < var7)
                {
                    ItemStack var9 = var6[var8];

                    if (this.hasValidContainer || var5 == null || var9.getItem().id != var5.getItem().id)
                    {
                        ++var8;
                        continue;
                    }

                    this.hasValidContainer = true;
                    this.containerid = var5.getItem().id;
                }

                ++var4;
                break;
            }
        }

        if (this.tank.liquidid != 0 && this.hasValidContainer && this.hasInventorySpace)
        {
            LiquidContainer var10 = LiquidManager.getEmptyContainer(new ItemStack(this.containerid, 1, 0), new LiquidStack(this.tank.liquidid, 1000));

            if (var10 != null && this.tank.empty())
            {
                this.player.inventory.c(this.containerid);
                this.player.inventory.setItem(this.index, var10.filled);
            }
        }
    }

    public void antimilk()
    {
        core.print("antimilk called!");
        ItemStack[] var1 = this.player.inventory.items;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            ItemStack var4 = var1[var3];

            if (var4 == null)
            {
                this.hasInventorySpace = true;
                LiquidContainer var5;

                if (this.tank.stored == 0 && var4 != null)
                {
                    var5 = LiquidManager.getLiquidContainer(var4);

                    if (var5 != null && var5.filled.getItem().id == var4.getItem().id && !this.hasValidContainer)
                    {
                        core.print("Found liquid in bags!");
                        this.forFill = LiquidManager.getLiquidContainer(var4);
                        this.hasValidContainer = true;
                        break;
                    }
                }

                if (this.tank.stored != 0 && var4 != null)
                {
                    var5 = LiquidManager.getLiquidContainer(var4);
                    core.print("" + var5.filled.id + " " + var4.id);
                    core.print("" + LiquidManager.getLiquidContainer(var4).liquid.itemID + " " + this.tank.liquidid);

                    if (LiquidManager.getLiquidContainer(var4).liquid.isLiquidEqual(var4))
                    {
                        core.print("Found liquid in bags!");
                        this.forFill = LiquidManager.getLiquidContainer(var4);
                        this.hasValidContainer = true;
                        break;
                    }
                }
            }
        }

        if (this.forFill != null && (this.tank.liquidid == 0 || this.tank.liquidid == this.forFill.liquid.itemID) && this.hasValidContainer && this.tank.fill())
        {
            this.player.inventory.c(this.forFill.filled.id);

            if (this.forFill.isBucket)
            {
                this.player.inventory.pickup(new ItemStack(Item.BUCKET));
            }
        }
    }
}
