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
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.IFluidContainerItem;

@SideOnly(Side.CLIENT)
public class ItemContainerRenderer implements IItemRenderer {

    protected String coordsFile;
    protected static RenderItem renderItem = new RenderItem();
    protected ArrayList<RenderData> renderTargets = new ArrayList();
    private int iconSizeX = 16;
    private int iconSizeY = 16;

    public ItemContainerRenderer(String coordsFile) {
        this.coordsFile = coordsFile;
        this.debugRender();
    }

    protected final void debugRender() {
        renderTargets.clear();
        String[] p = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, coordsFile, this);
        for (String a : p) {
            if (a.contains("#")) {
                // Special command!
                PfF.Proxy.print("Render resolution override found in " + coordsFile + ".");
                String command = denLib.StringUtils.removeSpaces(a.replace("#", ""));
                String[] parse = command.split(";");
                iconSizeX = Integer.valueOf(parse[0]);
                iconSizeY = Integer.valueOf(parse[1]);
                continue;
            }
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
        return type == ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (PfFTuning.getBool(PfFTuning.Rendering.render_debugMode)) {
            this.debugRender();
        }
        Icon icon = item.getIconIndex();
        if (icon != null) {
            renderItem.renderIcon(0, 0, icon, this.iconSizeX, this.iconSizeY);
        }
        IFluidContainerItem l = (IFluidContainerItem) item.getItem();
        if (l == null) {
            return;
        }
        // Bucket is empty, most likely.
        if (l.getFluid(item) == null) {
            return;
        }
        Icon liquid = l.getFluid(item).getFluid().getIcon();
        if (liquid == null) {
            liquid = BlockFluid.func_94424_b("water");
        }
        Minecraft.getMinecraft().renderEngine.func_110577_a(TextureMap.field_110575_b);
        for (RenderData t : renderTargets) {
            if (t == null || liquid == null) {
                continue;
            }
            renderItem.renderIcon(t.getX(), t.getY(), liquid, t.getHeight(), t.getWidth());
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }
}
