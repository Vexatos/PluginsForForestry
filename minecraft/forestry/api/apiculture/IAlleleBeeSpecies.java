package forestry.api.apiculture;

import forestry.api.genetics.IAlleleSpecies;
import java.util.HashMap;
import net.minecraft.src.World;

public interface IAlleleBeeSpecies extends IAlleleSpecies
{
    HashMap getProducts();

    HashMap getSpecialty();

    boolean isJubilant(World var1, int var2, int var3, int var4, int var5);
}
