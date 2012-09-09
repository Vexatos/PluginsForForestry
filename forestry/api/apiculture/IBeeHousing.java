package forestry.api.apiculture;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IBeeHousing extends IBeeModifier {
	
	int getXCoord();
	int getYCoord();
	int getZCoord();
	
	ItemStack getQueen();
	ItemStack getDrone();
	
	void setQueen(ItemStack itemstack);
	void setDrone(ItemStack itemstack);
	
	int getBiomeId();
	float getTemperature();
	float getHumidity();
	
	World getWorld();
	EntityPlayer getOwnerEntity();
	
	void setErrorState(int state);
	int getErrorOrdinal();
	
	/**
	 * @return true if princesses and drones can (currently) mate in this housing to generate queens.
	 */
	boolean canBreed();
	
	/**
	 * Called by IBeekeepingLogic to add products to the housing's inventory.
	 * @param product ItemStack with the product to add.
	 * @param all
	 * @return Boolean indicating success or failure.
	 */
	boolean addProduct(ItemStack product, boolean all);	
	
	/**
	 * Called when the bees wear out the housing's equipment.
	 * @param amount Integer indicating the amount worn out.
	 */
	void wearOutEquipment(int amount);
	
	/**
	 * Called on queen update.
	 * @param queen
	 */
	void onQueenChange(ItemStack queen);
	
	/**
	 * @return Boolean indicating if housing can ignore rain
	 */
	boolean isSealed();
	
	/**
	 * @return Boolean indicating if housing can ignore darkness/night
	 */
	boolean isSelfLighted();
	
	/**
	 * @return Boolean indicating if housing can ignore not seeing the sky
	 */
	boolean isSunlightSimulated();
	
	/**
	 * @return Boolean indicating whether this housing simulates the nether
	 */
	boolean isHellish();
}
