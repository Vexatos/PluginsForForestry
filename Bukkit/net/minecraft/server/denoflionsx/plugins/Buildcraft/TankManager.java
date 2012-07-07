package net.minecraft.server.denoflionsx.plugins.Buildcraft;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import java.lang.reflect.Method;
import net.minecraft.server.Block;
import net.minecraft.server.TileEntity;
import net.minecraft.server.World;

public class TankManager
{
    public static Block TankBlock;
    protected World world;
    protected int x;
    protected int y;
    protected int z;
    protected TileEntity Tank;
    protected Class TileTank;
    protected Class Orientation;
    protected Method fill;
    protected Method empty;
    public int stored;
    public int liquidid;

    public TankManager(World var1, int var2, int var3, int var4)
    {
        this.world = var1;
        this.x = var2;
        this.y = var3;
        this.z = var4;
        this.Tank = this.getTileTank();

        try
        {
            this.Orientation = Class.forName("buildcraft.api.Orientations");
            this.TileTank = this.Tank.getClass();
            this.fill = this.TileTank.getDeclaredMethod("fill", new Class[] {this.Orientation, Integer.TYPE, Integer.TYPE, Boolean.TYPE});
            this.empty = this.TileTank.getDeclaredMethod("empty", new Class[] {Integer.TYPE, Boolean.TYPE});
            this.stored = this.TileTank.getField("stored").getInt(this.Tank);
            this.liquidid = this.TileTank.getField("liquidId").getInt(this.Tank);
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
        }
    }

    public static void setup()
    {
        if (!core.isBukkit)
        {
            TankBlock = denLib.getBlock("BuildCraftFactory", "tankBlock");
        }
        else
        {
            TankBlock = denLib.getBlock("net.minecraft.server.BuildCraftFactory", "tankBlock");
        }
    }

    private TileEntity getTileTank()
    {
        TileEntity var1 = null;

        if (this.world.getTileEntity(this.x, this.y, this.z) != null)
        {
            var1 = this.world.getTileEntity(this.x, this.y, this.z);
        }

        return var1;
    }

    private boolean isTileTank()
    {
        return this.TileTank.getName().equals("buildcraft.factory.TileTank");
    }

    public boolean fill()
    {
        if (this.Tank != null && this.isTileTank())
        {
            try
            {
                if (this.liquidid != 0)
                {
                    this.fill.invoke(this.Tank, new Object[] {null, Integer.valueOf(1000), Integer.valueOf(this.liquidid), Boolean.valueOf(true)});
                    return true;
                }
            }
            catch (Exception var2)
            {
                var2.printStackTrace();
            }
        }

        return false;
    }

    public boolean empty()
    {
        if (this.Tank != null && this.isTileTank())
        {
            try
            {
                if (this.stored != 0)
                {
                    this.empty.invoke(this.Tank, new Object[] {Integer.valueOf(1000), Boolean.valueOf(true)});
                    return true;
                }
            }
            catch (Exception var2)
            {
                var2.printStackTrace();
            }
        }

        return false;
    }
}
