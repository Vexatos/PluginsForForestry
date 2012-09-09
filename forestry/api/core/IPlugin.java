package forestry.api.core;

import java.util.Random;

import cpw.mods.fml.common.network.IGuiHandler;

import net.minecraft.src.ICommand;
import net.minecraft.src.World;

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
	
	public IResupplyHandler getResupplyHandler();
	
	public ISaveEventHandler getSaveEventHandler();
	
	public IOreDictionaryHandler getDictionaryHandler();
	
	public ICommand[] getConsoleCommands();
}
