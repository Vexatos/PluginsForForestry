package denoflionsx.Machine;

import java.util.List;
import net.minecraft.src.*;

public class PfFMachineItemBlock extends ItemBlock {

    public PfFMachineItemBlock(int par1) {
        super(par1);
        this.setMaxDamage(0);
        this.hasSubtypes = true;
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(this,1,0));
    }
}
