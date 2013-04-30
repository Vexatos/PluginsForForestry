package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Proxy.PfFProxyClient;
import denoflionsx.denLib.Lib.denLib;
import net.minecraft.block.material.Material;
import denoflionsx.denLib.Mod.Blocks.BlockFluidClassic;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class LRLiquidBlock extends BlockFluidClassic {

    public Icon iconStill;
    public Icon iconFlowing;
    public String textureSheet;
    private String name;
    private String perma;

    public LRLiquidBlock(int id, String perma, String name, String still) {
        super(id, Material.water);
        this.setUnlocalizedName("pff." + denLib.StringUtils.removeSpaces(name.toLowerCase()));
        textureSheet = "PluginsforForestry:" + still;
        this.name = name;
        this.perma = perma;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public int getRenderType() {
        return PfFProxyClient.liquidRenderID;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        iconStill = PfFProxyClient.registerIcon(par1IconRegister, textureSheet);
        iconFlowing = PfFProxyClient.registerIcon(par1IconRegister, textureSheet.replace(".still", ".flowing"));
        LiquidStack l = LiquidDictionary.getCanonicalLiquid(this.perma);
        l.setRenderingIcon(iconStill);
        if (l.getRenderingIcon() != null){
            PfF.Proxy.print("Liquid icons loaded successfully!");
        }
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        return par1 <= 1 ? iconStill : iconFlowing;
    }
    
    
}
