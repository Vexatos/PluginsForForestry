package railcraft.common.api.signals;

import net.minecraft.block.BlockRail;
import net.minecraft.world.World;

/**
 *
 * @author CovertJaguar <railcraft.wikispaces.com>
 */
public abstract class SignalTools {

    public static IPairEffectRenderer effectManager;
    public static ISignalPacketBuilder packetBuilder;

    public static boolean isSignalBlockSectionValid(World world, IBlockSignal first, IBlockSignal second) {
        return areDistantRailsConnectedAlongAxis(world, first.getRailX(), first.getRailY(), first.getRailZ(), second.getRailX(), second.getRailY(), second.getRailZ());
    }

    /**
     * Verifies that two rails are connected to each other along a straight line
     * with no gaps or wanderings.
     *
     * @param world The World object
     * @param i1 x-Coord of Rail #1
     * @param j1 y-Coord of Rail #1
     * @param k1 z-Coord of Rail #1
     * @param i2 x-Coord of Rail #2
     * @param j2 y-Coord of Rail #2
     * @param k2 z-Coord of Rail #2
     * @return true if they are connected
     */
    public static boolean areDistantRailsConnectedAlongAxis(World world, int i1, int j1, int k1, int i2, int j2, int k2) {
        if (j1 < 0 || j2 < 0) {
            return false;
        }
        if (i1 != i2 && k1 != k2) {
            return false;
        }
        if (i1 != i2) {
            int min = 0;
            int max = 0;
            int jj = 0;
            if (i1 < i2) {
                min = i1;
                max = i2;
                jj = j1;
            } else {
                min = i2;
                max = i1;
                jj = j2;
            }
            for (int ii = min; ii <= max; ii++) {
                if (world.blockExists(ii, jj, k1)) {
                    if (BlockRail.isRailBlockAt(world, ii, jj, k1)) {
                        continue;
                    } else if (BlockRail.isRailBlockAt(world, ii, jj - 1, k1)) {
                        jj--;
                        continue;
                    } else if (BlockRail.isRailBlockAt(world, ii, jj + 1, k1)) {
                        jj++;
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        } else if (k1 != k2) {
            int min = 0;
            int max = 0;
            int jj = 0;
            if (k1 < k2) {
                min = k1;
                max = k2;
                jj = j1;
            } else {
                min = k2;
                max = k1;
                jj = j2;
            }
            for (int kk = min; kk <= max; kk++) {
                if (world.blockExists(i1, jj, kk)) {
                    if (BlockRail.isRailBlockAt(world, i1, jj, kk)) {
                        continue;
                    } else if (BlockRail.isRailBlockAt(world, i1, jj - 1, kk)) {
                        jj--;
                        continue;
                    } else if (BlockRail.isRailBlockAt(world, i1, jj + 1, kk)) {
                        jj++;
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
