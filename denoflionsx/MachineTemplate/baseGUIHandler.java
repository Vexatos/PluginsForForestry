package denoflionsx.MachineTemplate;

import cpw.mods.fml.common.network.IGuiHandler;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class baseGUIHandler implements IGuiHandler{
    
    public static HashMap<Integer,ArrayList<CoordObject>> slotMap = new HashMap();

    public baseGUIHandler() {
    }
   
     //returns an instance of the Container you made earlier
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof baseTileEntity){
                        return new baseContainer(player.inventory, (baseTileEntity) tileEntity,slotMap.get(id));
                }
                return null;
        }

        //returns an instance of the Gui you made earlier
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof baseTileEntity){
                        return new baseGUI(player, (baseTileEntity) tileEntity, new ArrayList<CoordObject>());
                }
                return null;
        }
    
}
