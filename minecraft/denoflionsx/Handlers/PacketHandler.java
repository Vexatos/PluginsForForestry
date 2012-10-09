package denoflionsx.Handlers;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import denoflionsx.Version.PfFVersion;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler{

    @Override
    public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals(PfFVersion.channel)){
            
        }
    }

}
