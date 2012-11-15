package forestry.api.apiculture;

import forestry.api.core.IStructureLogic;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IApiaristTracker;
import java.util.ArrayList;
import net.minecraft.src.World;

public interface IBreedingManager
{
    ArrayList getBeekeepingModes();

    IBeekeepingMode getBeekeepingMode(World var1);

    IBeekeepingMode getBeekeepingMode(String var1);

    void registerBeekeepingMode(IBeekeepingMode var1);

    void setBeekeepingMode(World var1, String var2);

    int getBeeSpeciesCount();

    void blacklistBeeSpecies(String var1);

    ArrayList getBeeSpeciesBlacklist();

    boolean isBlacklisted(String var1);

    IBeekeepingLogic createBeekeepingLogic(IBeeHousing var1);

    IStructureLogic createAlvearyStructureLogic(IAlvearyComponent var1);

    void registerBeeTemplate(IAllele[] var1);

    void registerBeeTemplate(String var1, IAllele[] var2);

    IAllele[] getBeeTemplate(String var1);

    IAllele[] getDefaultBeeTemplate();

    IApiaristTracker getApiaristTracker(World var1);
}
