package forestry.api.arboriculture;

import java.util.ArrayList;

import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividual;

import net.minecraft.src.World;

public interface ITree extends IIndividual {

	void mate(ITree other);

	IEffectData[] doEffect(IEffectData[] storedData, World world, int biomeid, int x, int y, int z);

	IEffectData[] doFX(IEffectData[] storedData, World world, int biomeid, int x, int y, int z);

	ArrayList<Integer> getSuitableBiomeIds();

	ITreeGenome getGenome();
}
