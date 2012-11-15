package forestry.api.core;

import cpw.mods.fml.common.network.Player;
import java.io.DataInputStream;
import net.minecraft.src.INetworkManager;

public interface IPacketHandler
{
    void onPacketData(INetworkManager var1, int var2, DataInputStream var3, Player var4);
}
