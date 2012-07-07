package net.minecraft.server.denoflionsx.plugins.IC2;

import buildcraft.api.ILiquidContainer;
import buildcraft.api.Orientations;
import buildcraft.api.Position;
import ic2.api.Direction;
import ic2.api.EnergyNet;
import ic2.api.IEnergySink;
import net.minecraft.server.Block;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.TileEntity;

public class TileEntityMachine extends TileEntity implements IEnergySink, ILiquidContainer
{
    protected int storedeu;
    protected int maxeu;
    protected int storedliquid;
    protected int maxliquid;
    protected boolean isAdded = false;
    protected boolean valid = true;

    public TileEntityMachine(int var1)
    {
        this.maxeu = var1;
        this.maxliquid = var1;
    }

    public TileEntityMachine() {}

    public int getStoredLiquid()
    {
        return this.storedliquid;
    }

    public int getStoredEU()
    {
        return this.storedeu;
    }

    public boolean demandsEnergy()
    {
        return this.storedeu < this.maxeu;
    }

    public int injectEnergy(Direction var1, int var2)
    {
        int var3 = Math.min(var2, this.maxeu - this.storedeu);
        this.storedeu += var3;
        return var2 - var3;
    }

    public boolean acceptsEnergyFrom(TileEntity var1, Direction var2)
    {
        return true;
    }

    public boolean isAddedToEnergyNet()
    {
        return this.isAdded;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void q_()
    {
        if (!this.isAdded)
        {
            EnergyNet.getForWorld(this.world).addTileEntity(this);
            this.isAdded = true;
        }

        if (this.storedeu > 0 && this.storedeu - 20 >= 0)
        {
            this.storedeu -= 20;
            this.storedliquid += 20;
        }

        if (this.storedliquid > 0)
        {
            for (int var1 = 0; var1 < 6; ++var1)
            {
                Position var2 = new Position((double)this.x, (double)this.y, (double)this.z, Orientations.values()[var1]);
                var2.moveForwards(1.0D);
                TileEntity var3 = this.world.getTileEntity((int)var2.x, (int)var2.y, (int)var2.z);

                if (var3 instanceof ILiquidContainer)
                {
                    byte var4 = 100;
                    int var5 = var4 - ((ILiquidContainer)var3).fill(var2.orientation.reverse(), var4, this.getLiquidId(), true);
                    this.storedliquid -= var4 - var5;

                    if (var5 <= 0)
                    {
                        break;
                    }
                }
            }
        }
    }

    /**
     * invalidates a tile entity
     */
    public void j()
    {
        this.valid = false;

        if (this.isAdded)
        {
            EnergyNet.getForWorld(this.world).removeTileEntity(this);
            this.isAdded = false;
        }
    }

    /**
     * validates a tile entity
     */
    public void m()
    {
        this.valid = true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void a(NBTTagCompound var1)
    {
        super.a(var1);
        this.maxeu = var1.getInt("maxeu");
        this.storedeu = var1.getInt("stored");
        this.storedliquid = var1.getInt("storedliquid");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void b(NBTTagCompound var1)
    {
        super.b(var1);
        var1.setInt("stored", this.storedeu);
        var1.setInt("maxeu", this.maxeu);
        var1.setInt("storedliquid", this.storedliquid);
    }

    public int empty(int var1, boolean var2)
    {
        return 0;
    }

    public int fill(Orientations var1, int var2, int var3, boolean var4)
    {
        return 0;
    }

    public int getCapacity()
    {
        return this.maxliquid;
    }

    public int getLiquidId()
    {
        return Block.STATIONARY_WATER.id;
    }

    public int getLiquidQuantity()
    {
        return this.storedliquid;
    }
}
