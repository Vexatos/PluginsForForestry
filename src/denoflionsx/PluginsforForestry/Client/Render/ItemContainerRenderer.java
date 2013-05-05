package denoflionsx.PluginsforForestry.Client.Render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import net.minecraft.block.BlockFluid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

@SideOnly(Side.CLIENT)
public class ItemContainerRenderer implements IItemRenderer {

    protected String coordsFile;
    protected static RenderItem renderItem = new RenderItem();
    protected ArrayList<RenderData> renderTargets = new ArrayList();

    public ItemContainerRenderer(String coordsFile) {
        this.coordsFile = coordsFile;
        this.debugRender();
    }

    protected void debugRender() {
        renderTargets.clear();
        String[] p = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, coordsFile, this);
        for (String a : p) {
            String b[] = a.split(",");
            int x = PfFTuning.getInt(b[0]);
            int y = PfFTuning.getInt(b[1]);
            int height = PfFTuning.getInt(b[2]);
            int width = PfFTuning.getInt(b[3]);
            RenderData d = new RenderData(x, y, height, width);
            renderTargets.add(d);
        }
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return ItemRenderType.INVENTORY == type;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (PfFTuning.getBool(PfFTuning.Rendering.render_debugMode)) {
            this.debugRender();
        }
        Icon icon = item.getIconIndex();
        renderItem.renderIcon(0, 0, icon, 16, 16);
        LiquidStack l = LiquidContainerRegistry.getLiquidForFilledItem(item);
        if (l == null){
            return;
        }
        Icon liquid = l.canonical().getRenderingIcon();
        if (liquid == null){
            liquid = BlockFluid.func_94424_b("water");
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(l.canonical().getTextureSheet());
        for (RenderData t : renderTargets) {
            renderItem.renderIcon(t.getX(), t.getY(), liquid, t.getHeight(), t.getWidth());
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }
}
