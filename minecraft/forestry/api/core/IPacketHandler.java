package forestry.api.core;

import java.io.DataInputStream;

import cpw.mods.fml.common.network.Player;
import net.minecraft.src.NetworkManager;

public interface IPacketHandler {

	void onPacketData(NetworkManager network, int packetID, DataInputStream data, Player player);

}
