package forestry.api.arboriculture;

import forestry.api.genetics.IAlleleSpecies;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public interface IAlleleTreeSpecies extends IAlleleSpecies
{
    WorldGenerator getTreeGenerator(ITree var1);

    ItemStack[] getGroundBlocks(World var1, int var2, int var3, int var4);

    int getGermlingIconIndex(EnumGermlingType var1);

    String getTextureFile();

    boolean usesDefaultTexture();
}
