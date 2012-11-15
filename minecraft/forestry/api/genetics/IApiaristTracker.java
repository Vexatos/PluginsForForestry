package forestry.api.genetics;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

public interface IApiaristTracker
{
    void decodeFromNBT(NBTTagCompound var1);

    void encodeToNBT(NBTTagCompound var1);

    String getModeName();

    void setModeName(String var1);

    void registerQueen(EntityPlayer var1, IIndividual var2);

    int getQueenCount();

    void registerPrincess(EntityPlayer var1, IIndividual var2);

    int getPrincessCount();

    void registerDrone(EntityPlayer var1, IIndividual var2);

    int getDroneCount();

    int getSpeciesBred();

    void registerMutation(IMutation var1);

    boolean isDiscovered(IMutation var1);

    boolean isDiscovered(IAlleleSpecies var1);
}
