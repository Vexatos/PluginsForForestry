package forestry.api.core;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;
import net.minecraft.src.forge.IPacketHandler;
import net.minecraft.src.forge.IPickupHandler;

/**
 * Plugins get loaded at the beginning of Forestry's ModsLoaded() if isAvailable() returns true.
 * 
 * @author SirSengir
 */
public interface IPlugin {
	public boolean isAvailable();

	public void preInit();

	public void doInit();

	public void postInit();

	public String getDescription();

	public void generateSurface(World world, Random rand, int chunkX, int chunkZ);

	public IGuiHandler getGuiHandler();

	public IPacketHandler getPacketHandler();

	public IPickupHandler getPickupHandler();

	public IAchievementHandler getAchievementHandler();

	public IResupplyHandler getResupplyHandler();
}
