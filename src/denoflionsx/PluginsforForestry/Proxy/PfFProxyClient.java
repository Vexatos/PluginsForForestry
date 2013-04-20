package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Client.Render.ItemContainerRenderer;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.denLib.Mod.Client.Render.RenderBlockFluidClassic;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.client.MinecraftForgeClient;

public class PfFProxyClient extends PfFProxy {

    public static int liquidRenderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void registerClientSide() {
        MinecraftForgeClient.registerItemRenderer(LRItems.containers.get("Barrel").itemID, new ItemContainerRenderer("barrel.txt"));
        RenderingRegistry.registerBlockHandler(liquidRenderID, new RenderBlockFluidClassic(liquidRenderID));
    }

    @SideOnly(Side.CLIENT)
    public static Icon registerIcon(IconRegister par1IconRegister, String icon) {
        PfF.Proxy.print("Registering icon " + icon);
        return par1IconRegister.registerIcon(icon);
    }
}
