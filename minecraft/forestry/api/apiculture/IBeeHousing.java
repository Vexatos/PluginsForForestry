package forestry.api.apiculture;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IBeeHousing extends IBeeModifier
{
    int getXCoord();

    int getYCoord();

    int getZCoord();

    ItemStack getQueen();

    ItemStack getDrone();

    void setQueen(ItemStack var1);

    void setDrone(ItemStack var1);

    int getBiomeId();

    float getTemperature();

    float getHumidity();

    World getWorld();

    EntityPlayer getOwnerEntity();

    void setErrorState(int var1);

    int getErrorOrdinal();

    boolean canBreed();

    boolean addProduct(ItemStack var1, boolean var2);

    void wearOutEquipment(int var1);

    void onQueenChange(ItemStack var1);

    boolean isSealed();

    boolean isSelfLighted();

    boolean isSunlightSimulated();

    boolean isHellish();
}
