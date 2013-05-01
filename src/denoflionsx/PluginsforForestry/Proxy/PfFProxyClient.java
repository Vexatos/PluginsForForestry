package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Client.Render.ItemContainerRenderer;
import denoflionsx.PluginsforForestry.Client.Render.RenderThis;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemContainer;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.denLib.Mod.Client.Render.RenderBlockFluidClassic;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraftforge.client.MinecraftForgeClient;

public class PfFProxyClient extends PfFProxy {

    public static int liquidRenderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void registerClientSide() {
        RenderingRegistry.registerBlockHandler(liquidRenderID, new RenderBlockFluidClassic(liquidRenderID));
        try {
            for (Field f : LRItems.class.getDeclaredFields()) {
                for (Annotation a : f.getDeclaredAnnotations()) {
                    if (a instanceof RenderThis) {
                        RenderThis r = (RenderThis) a;
                        Object o = f.get(null);
                        if (o instanceof HashMap) {
                            HashMap<Integer, Item> i = (HashMap) o;
                            for (Item i2 : i.values()) {
                                MinecraftForgeClient.registerItemRenderer(i2.itemID, new ItemContainerRenderer(r.renderFile()));
                            }
                        } else if (o instanceof ItemContainer) {
                            ItemContainer c = (ItemContainer) o;
                            MinecraftForgeClient.registerItemRenderer(c.itemID, new ItemContainerRenderer(r.renderFile()));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SideOnly(Side.CLIENT)
    public static Icon registerIcon(IconRegister par1IconRegister, String icon) {
        PfF.Proxy.print("Registering icon " + icon);
        return par1IconRegister.registerIcon(icon);
    }
}
