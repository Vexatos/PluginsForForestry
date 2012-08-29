package net.minecraft.src;

public class EntityAIOcelotSit extends EntityAIBase
{
    private final EntityOcelot theOcelot;
    private final float field_50083_b;
    private int field_50084_c = 0;
    private int field_52011_h = 0;
    private int field_50081_d = 0;
    private int field_50082_e = 0;
    private int field_50079_f = 0;
    private int field_50080_g = 0;

    public EntityAIOcelotSit(EntityOcelot par1EntityOcelot, float par2)
    {
        this.theOcelot = par1EntityOcelot;
        this.field_50083_b = par2;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.theOcelot.isTamed() && !this.theOcelot.isSitting() && this.theOcelot.getRNG().nextDouble() <= 0.006500000134110451D && this.func_50077_h();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.field_50084_c <= this.field_50081_d && this.field_52011_h <= 60 && this.func_50078_a(this.theOcelot.worldObj, this.field_50082_e, this.field_50079_f, this.field_50080_g);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theOcelot.getNavigator().tryMoveToXYZ((double)((float)this.field_50082_e) + 0.5D, (double)(this.field_50079_f + 1), (double)((float)this.field_50080_g) + 0.5D, this.field_50083_b);
        this.field_50084_c = 0;
        this.field_52011_h = 0;
        this.field_50081_d = this.theOcelot.getRNG().nextInt(this.theOcelot.getRNG().nextInt(1200) + 1200) + 1200;
        this.theOcelot.func_50008_ai().setIsSitting(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.theOcelot.setSitting(false);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        ++this.field_50084_c;
        this.theOcelot.func_50008_ai().setIsSitting(false);

        if (this.theOcelot.getDistanceSq((double)this.field_50082_e, (double)(this.field_50079_f + 1), (double)this.field_50080_g) > 1.0D)
        {
            this.theOcelot.setSitting(false);
            this.theOcelot.getNavigator().tryMoveToXYZ((double)((float)this.field_50082_e) + 0.5D, (double)(this.field_50079_f + 1), (double)((float)this.field_50080_g) + 0.5D, this.field_50083_b);
            ++this.field_52011_h;
        }
        else if (!this.theOcelot.isSitting())
        {
            this.theOcelot.setSitting(true);
        }
        else
        {
            --this.field_52011_h;
        }
    }

    private boolean func_50077_h()
    {
        int var1 = (int)this.theOcelot.posY;
        double var2 = 2.147483647E9D;

        for (int var4 = (int)this.theOcelot.posX - 8; (double)var4 < this.theOcelot.posX + 8.0D; ++var4)
        {
            for (int var5 = (int)this.theOcelot.posZ - 8; (double)var5 < this.theOcelot.posZ + 8.0D; ++var5)
            {
                if (this.func_50078_a(this.theOcelot.worldObj, var4, var1, var5) && this.theOcelot.worldObj.isAirBlock(var4, var1 + 1, var5))
                {
                    double var6 = this.theOcelot.getDistanceSq((double)var4, (double)var1, (double)var5);

                    if (var6 < var2)
                    {
                        this.field_50082_e = var4;
                        this.field_50079_f = var1;
                        this.field_50080_g = var5;
                        var2 = var6;
                    }
                }
            }
        }

        return var2 < 2.147483647E9D;
    }

    private boolean func_50078_a(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockId(par2, par3, par4);
        int var6 = par1World.getBlockMetadata(par2, par3, par4);

        if (var5 == Block.chest.blockID)
        {
            TileEntityChest var7 = (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4);

            if (var7.numUsingPlayers < 1)
            {
                return true;
            }
        }
        else
        {
            if (var5 == Block.stoneOvenActive.blockID)
            {
                return true;
            }

            if (var5 == Block.bed.blockID && !BlockBed.isBlockFootOfBed(var6))
            {
                return true;
            }
        }

        return false;
    }
}
