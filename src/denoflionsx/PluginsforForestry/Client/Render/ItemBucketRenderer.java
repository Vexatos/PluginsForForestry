package denoflionsx.PluginsforForestry.Client.Render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

@SideOnly(Side.CLIENT)
public class ItemBucketRenderer extends ItemContainerRenderer{

    public ItemBucketRenderer(String coordsFile) {
        super(coordsFile);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (container == null) {
            container = LiquidContainerRegistry.EMPTY_BUCKET.getIconIndex();
        }
        LiquidStack l = LiquidContainerRegistry.getLiquidForFilledItem(item);
        if (type == ItemRenderType.INVENTORY) {
            if (l == null) {
                renderItem.renderIcon(0, 0, container, 16, 16);
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
    
    
    
}
