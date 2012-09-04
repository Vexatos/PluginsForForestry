package denoflionsx.core;

import denoflionsx.MachineTemplate.*;
import denoflionsx.plugins.BluesFood.MachineOven;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class PfFGUIHandler extends baseGUIHandler{
    
    public PfFGUIHandler() {
        super();
    }

    @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof baseTileEntity){
                        return new baseContainer(player.inventory, (baseTileEntity) tileEntity,baseGUIHandler.slotMap.get(id));
                }
                return null;
        }

        //returns an instance of the Gui you made earlier
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof MachineOven.TileEntityOven){
                        return new MachineOven.GUIOven(player, (baseTileEntity) tileEntity);
                }
                return null;
        }

}
