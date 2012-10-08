package denoflionsx.plugins.BlueSilkWorm;

import denoflionsx.Annotations.ThisIsBlues;
import denoflionsx.Machine.PfFMachineBlock;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.plugins.BlueSilkWorm.Gadget.BlockIncubator;
import denoflionsx.plugins.BlueSilkWorm.Gadget.GadgetIncubator;
import denoflionsx.plugins.BlueSilkWorm.Gadget.TileEntityIncubator;
import denoflionsx.plugins.BlueSilkWorm.Items.ItemSilkWorm;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

@ThisIsBlues
public class pluginBlueSilkWorm extends PfFPluginTemplate{
    
    public ItemIDManager id = new ItemIDManager(1,"silkworm");
    public ItemSilkWorm silkworm;
    public int defaultBlockID = 3333;
    public PfFMachineBlock block;
    
    public pluginBlueSilkWorm(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("SilkWorm_ItemID=" + id.getItemIDs().get(0));
        this.config.addDefault("TestBlock_BlockID=" + defaultBlockID);
    }

    @Override
    public void doSetup() {
        silkworm = new ItemSilkWorm(this.config.getOptionInt("SilkWorm_ItemID"),"silkworms");
        FMLWrapper.MODE.FML.registerTileEntity(TileEntityIncubator.class, "dolx.incubator");
        block = new BlockIncubator(this.config.getOptionInt("TestBlock_BlockID"),Material.cactus,GadgetIncubator.incubator,"Incubator");
    }

    @Override
    public void recipes() {
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(silkworm,1,0), new Object[]{
                    "XXX",
                    "XDX",
                    "XXX",
                    Character.valueOf('D'),new ItemStack(Block.dirt)});
    }

}
