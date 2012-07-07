package net.minecraft.src.denoflionsx.plugins.Forestry.Trees;

import java.util.HashMap;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class universalTreeGenerator {

    public static HashMap<Integer, universalTreeData> Trees = new HashMap();

    public void growTree(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        for (int i = 0; i < 5; i++) {
            world.setBlockAndMetadataWithNotify(x, y + i, z, Trees.get(meta).getLog().blockID, Trees.get(meta).getLogMeta());
        }
        world.setBlockAndMetadataWithNotify(x, y + 5, z, Trees.get(meta).getLeaf().blockID, Trees.get(meta).getLeafMeta());

    }

    public static void add(int meta, Block log, Block leaf, Block sapling, int logmeta, int leafmeta, int saplingmeta) {
        Trees.put(meta, new universalTreeData(log, leaf, sapling, logmeta, leafmeta, saplingmeta));
    }

    public static void add(int meta, Block log, Block leaf, Block sapling) {
        Trees.put(meta, new universalTreeData(log, leaf, sapling));
    }
}
