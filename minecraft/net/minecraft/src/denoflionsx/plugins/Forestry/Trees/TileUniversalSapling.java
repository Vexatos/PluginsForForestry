package net.minecraft.src.denoflionsx.plugins.Forestry.Trees;

import java.util.Random;
import net.minecraft.src.TileEntity;

public class TileUniversalSapling extends TileEntity {

    protected int growth;
    protected Random rng;
    protected int tick;

    public TileUniversalSapling() {
        this.tick = 0;
        this.rng = new Random();
        //core.print("Tile created!");
    }

    @Override
    public void updateEntity() {
        this.tick++;
        if (this.tick == 10) {
            this.tick = 0;
            int roll = rng.nextInt(10) + 1;
            //core.print("Roll: " + String.valueOf(roll));
            if (roll == 10) {
                this.growth++;
            }
            if (this.growth == 5) {
                universalTreeGenerator TreeGen = new universalTreeGenerator();
                TreeGen.growTree(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }
}
