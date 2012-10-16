package denoflionsx.Machine;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraftforge.common.ForgeDirection;

public class ForgeSideInventory {
    
    public HashMap<ForgeDirection,Integer> Size = new HashMap();
    public HashMap<ForgeDirection,Integer> Start = new HashMap();
    private ArrayList<ForgeDirection> sides = new ArrayList();

    public ForgeSideInventory() {
        sides.add(ForgeDirection.EAST);
        sides.add(ForgeDirection.NORTH);
        sides.add(ForgeDirection.SOUTH);
        sides.add(ForgeDirection.WEST);
    }
    
    public void add(ForgeDirection side, int[] slots){
        this.Start.put(side,slots[0]);
        this.Size.put(side, (slots[1] - slots[0]) + 1);
    }
    
    public void addToAllSides(int[] slots){
        for (ForgeDirection side : sides){
            this.Start.put(side,slots[0]);
            this.Size.put(side, (slots[1] - slots[0]) + 1);
        }
    }
}
