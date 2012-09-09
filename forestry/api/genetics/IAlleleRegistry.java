package forestry.api.genetics;

import java.util.LinkedHashMap;

import net.minecraft.src.World;

public interface IAlleleRegistry {
	LinkedHashMap<String, IAllele> getRegisteredAlleles();
	LinkedHashMap<String, IBranch> getRegisteredBranches();
	
	void registerAllele(IAllele allele);
	IAllele getAllele(String uid);
	
	void reloadMetaMap(World world);
	IAllele getFromMetaMap(int meta);
	int getFromUIDMap(String uid);
	
	void registerBranch(IBranch branch);
	IBranch getBranch(String uid);
	
	/*
	 * Registers a new IAlleleHandler
	 */
	void registerAlleleHandler(IAlleleHandler handler);

}
