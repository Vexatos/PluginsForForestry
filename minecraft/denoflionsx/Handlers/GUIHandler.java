package denoflionsx.Handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import denoflionsx.Machine.PfFMachineContainer;
import denoflionsx.Machine.Client.PfFMachineGUI;
import denoflionsx.Machine.PfFMachineTileEntity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class GUIHandler implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile instanceof PfFMachineTileEntity) {
            // Fixing derped Tile Entities.
            return new PfFMachineGUI(new PfFMachineContainer(tile, player));
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile instanceof PfFMachineTileEntity) {
            return new PfFMachineContainer(tile, player);
        }
        return null;
    }

}
