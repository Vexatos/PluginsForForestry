package denoflionsx.MachineTemplate;

import java.util.ArrayList;
import net.minecraft.src.*;

public class baseContainer extends Container {

    protected baseTileEntity tileEntity;
        
        public baseContainer (InventoryPlayer inventoryPlayer, baseTileEntity te, ArrayList<CoordObject> map){
                tileEntity = te;
                for (CoordObject o : map){
                    this.addSlotToContainer(new Slot(te,o.getX(),o.getY(),o.getZ()));
                }
                //commonly used vanilla code that adds the player's inventory
                bindPlayerInventory(inventoryPlayer);
        }

        @Override
        public boolean canInteractWith(EntityPlayer player) {
                return tileEntity.isUseableByPlayer(player);
        }


        protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
                for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 9; j++) {
                                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                                8 + j * 18, 84 + i * 18));
                        }
                }

                for (int i = 0; i < 9; i++) {
                        addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
                }
        }
}
