package denoflionsx.PluginsforForestry.Client.Render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.denLib.Lib.denLib;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

@SideOnly(Side.CLIENT)
public class ItemContainerRenderer implements IItemRenderer {

    private static RenderItem renderItem = new RenderItem();
    private Icon container = null;
    private ArrayList<RenderData> renderTargets = new ArrayList();

    public ItemContainerRenderer(String coordsFile) {
        InputStream i = this.getClass().getResourceAsStream(coordsFile);
        String f = denLib.StringUtils.scanFileContents(i);
        String[] p = denLib.StringUtils.splitByNewLine(f);
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
        if (container == null) {
            container = new ItemStack(item.itemID, 1, 0).getIconIndex();
        }
        Icon iconIndex = item.getIconIndex();
        LiquidStack l = LiquidContainerRegistry.getLiquidForFilledItem(item);
        if (type == ItemRenderType.INVENTORY) {
            if (l == null) {
                renderItem.renderIcon(0, 0, iconIndex, 16, 16);
            } else if (l.canonical().getRenderingIcon() != null) {
                renderItem.renderIcon(0, 0, container, 16, 16);
                Minecraft.getMinecraft().renderEngine.bindTexture(l.canonical().getTextureSheet());
                for (RenderData d : renderTargets) {
                    renderItem.renderIcon(d.getX(), d.getY(), l.canonical().getRenderingIcon(), d.getHeight(), d.getWidth());
                }
            } else {
                renderItem.renderIcon(0, 0, container, 16, 16);
            }
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }
}
