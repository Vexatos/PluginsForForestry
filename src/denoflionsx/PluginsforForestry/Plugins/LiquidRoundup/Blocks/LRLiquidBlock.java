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

    public LRLiquidBlock(int id, String name) {
        super(id, Material.water);
        this.setUnlocalizedName("pff." + denLib.StringUtils.removeSpaces(name.toLowerCase()));
        textureSheet = PfF.core.liquidgfxpath + this.getUnlocalizedName() + ".still";
        this.name = name;
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
        LiquidStack l = LiquidDictionary.getCanonicalLiquid(this.name);
        l.canonical().setTextureSheet("/terrain.png");
        l.canonical().setRenderingIcon(iconStill);
    }

    @Override
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return par1 <= 1 ? iconStill : iconFlowing;
    }
}
