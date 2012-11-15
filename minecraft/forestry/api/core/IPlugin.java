package forestry.api.core;

import cpw.mods.fml.common.network.IGuiHandler;
import java.util.Random;
import net.minecraft.src.ICommand;
import net.minecraft.src.World;

public interface IPlugin
{
    boolean isAvailable();

    void preInit();

    void doInit();

    void postInit();

    String getDescription();

    void generateSurface(World var1, Random var2, int var3, int var4);

    IGuiHandler getGuiHandler();

    IPacketHandler getPacketHandler();

    IPickupHandler getPickupHandler();

    IResupplyHandler getResupplyHandler();

    ISaveEventHandler getSaveEventHandler();

    IOreDictionaryHandler getDictionaryHandler();

    ICommand[] getConsoleCommands();
}
