package denoflionsx.Beta;

import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.Machine.PfFMachineBlock;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.Machine.PfFMachineTileEntityRenderer;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.src.Material;

public class pluginBeta extends PfFPluginTemplate{
    
    public static int blockid = 0;
    
    PfFMachineBlock block;

    public pluginBeta(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("Test_BlockID=" + 3333);
        
        this.config.getOptionInt("Test_BlockID");
    }

    @Override
    public void doSetup() {
        PfFMachineTileEntityRenderer renderer = new PfFMachineTileEntityRenderer();
        FMLWrapper.MODE.FML.registerTileEntity(PfFMachineTileEntity.class, "PfF.Machine");
        block = new PfFMachineBlock(blockid,1,Material.cactus);
        FMLWrapper.MODE.FML.registerBlock(block);
        LanguageRegistry.addName(block, "Test Barrel");
    }

    @Override
    public void recipes() {
        
    }

}
