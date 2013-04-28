package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Proxy.PfFProxyClient;
import net.minecraft.client.renderer.texture.IconRegister;

public class ItemWoodenBucketEmpty extends ItemLRBucket {

    public ItemWoodenBucketEmpty(int par1, int par2, String local) {
        super(par1, par2, local);
        this.local = PfFTranslator.instance.translateKey(local);
    }

    @Override
    public void updateIcons(IconRegister par1IconRegister) {
        try {
            this.iconIndex = PfFProxyClient.registerIcon(par1IconRegister, "PluginsforForestry:bucket_wood_birch");
        } catch (Exception ex) {
            // shh
        }
    }
}
