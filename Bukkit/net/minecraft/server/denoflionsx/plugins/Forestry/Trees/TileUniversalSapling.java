package net.minecraft.server.denoflionsx.plugins.Forestry.Trees;

import java.util.Random;
import net.minecraft.server.TileEntity;

public class TileUniversalSapling extends TileEntity
{
    protected int growth;
    protected Random rng = new Random();
    protected int tick = 0;

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void q_()
    {
        ++this.tick;

        if (this.tick == 10)
        {
            this.tick = 0;
            int var1 = this.rng.nextInt(10) + 1;

            if (var1 == 10)
            {
                ++this.growth;
            }

            if (this.growth == 5)
            {
                universalTreeGenerator var2 = new universalTreeGenerator();
                var2.growTree(this.world, this.x, this.y, this.z);
            }
        }
    }
}
