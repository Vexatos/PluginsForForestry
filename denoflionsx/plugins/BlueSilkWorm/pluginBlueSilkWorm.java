package denoflionsx.plugins.BlueSilkWorm;

import buildcraft.api.gates.ActionManager;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.transport.IPipe;
import denoflionsx.Annotations.ThisIsBlues;
import denoflionsx.Interfaces.IPfFTrigger;
import denoflionsx.Machine.PfFMachineBlock;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.plugins.BlueSilkWorm.Items.ItemSilkWorm;
import denoflionsx.plugins.BlueSilkWorm.Triggers.Triggers;
import java.util.LinkedList;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;

@ThisIsBlues
public class pluginBlueSilkWorm extends PfFPluginTemplate implements ITriggerProvider{
    
    public ItemIDManager id = new ItemIDManager(1,"silkworm");
    public ItemSilkWorm silkworm;
    public int defaultBlockID = 3333;
    public PfFMachineBlock block;
    
    public pluginBlueSilkWorm(String name, String parent) {
        super(name, parent);
        ActionManager.registerTriggerProvider(this);
    }

    @Override
    public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
        if (tile instanceof PfFMachineTileEntity){
            PfFMachineTileEntity t = (PfFMachineTileEntity)tile;
            return t.getCustomTriggers();
        }
        return null;
    }

    @Override
    public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
        return null;
    }

    @Override
    public void defaults() {
        this.config.addDefault("SilkWorm_ItemID=" + id.getItemIDs().get(0));
        this.config.addDefault("TestBlock_BlockID=" + defaultBlockID);
    }

    @Override
    public void doSetup() {
        silkworm = new ItemSilkWorm(this.config.getOptionInt("SilkWorm_ItemID"),"silkworms");
        FMLWrapper.MODE.FML.registerTileEntity(PfFMachineTileEntity.class, "pffmachinetileentity");
        block = new PfFMachineBlock(this.config.getOptionInt("TestBlock_BlockID"),1,Material.cactus);
        FMLWrapper.MODE.FML.registerBlock(block);
        FMLWrapper.MODE.FML.registerBlockName(block, "Test Machine");
        Triggers.init();
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
