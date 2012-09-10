package denoflionsx.plugins.Buildcraft;

import denoflionsx.core.EnumModIDs;
import java.lang.reflect.Method;
import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;

public class TankManager {

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

    public TankManager(World par3World, int par4, int par5, int par6) {
        this.world = par3World;
        this.x = par4;
        this.y = par5;
        this.z = par6;
        this.Tank = getTileTank();

        try {
            this.Orientation = Class.forName("buildcraft.api.Orientations");
            this.TileTank = this.Tank.getClass();
            this.fill = this.TileTank.getDeclaredMethod("fill", new Class[]{Orientation, int.class, int.class, boolean.class});
            this.empty = this.TileTank.getDeclaredMethod("empty", new Class[]{int.class, boolean.class});
            this.stored = this.TileTank.getField("stored").getInt(this.Tank);
            this.liquidid = this.TileTank.getField("liquidId").getInt(this.Tank);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public static void setup() {
        if (!core.isBukkit) {
            TankBlock = denLib.getBlock(EnumModIDs.MODS.BUILDCRAFT_FACTORY.gettheClass(), "tankBlock");
        } else {
            TankBlock = denLib.getBlock("net.minecraft.server.BuildCraftFactory", "tankBlock");
        }
    }

    private TileEntity getTileTank() {
        TileEntity t = null;
        if (this.world.getBlockTileEntity(this.x, this.y, this.z) != null) {
            t = this.world.getBlockTileEntity(this.x, this.y, this.z);
        }

        return t;
    }

    private boolean isTileTank() {

        return this.TileTank.getName().equals("buildcraft.factory.TileTank");

    }

    public boolean fill() {
        if (this.Tank != null) {
            if (isTileTank()) {
                try {
                    if (this.liquidid != 0) {
                        this.fill.invoke(this.Tank, null, 1000, this.liquidid, true);
                        return true;
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean empty() {
        if (this.Tank != null) {
            if (isTileTank()) {
                try {
                    if (this.stored != 0) {
                        this.empty.invoke(this.Tank, 1000, true);
                        return true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;

    }
}
