package denoflionsx.PluginsforForestry_PluginTweaks.Core;

import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry_PluginTweaks.Config.TweaksTuning;
import denoflionsx.PluginsforForestry_PluginTweaks.Integration.ExtraBees.ExtraBeesIntegration;
import denoflionsx.PluginsforForestry_PluginTweaks.PfFTweaks;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class PfFTweakCore implements IPfFCore {
    
    private int eventID;
    
    @Override
    public void preloadTextures() {
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
    }
    
    @Override
    public void setupBlocks() {
        if (TweaksTuning.Enables.fencelight_enabled) {
            PfFTweaks.Proxy.changeBlockLight(Block.fence, 1.0f);
            PfFTweaks.Proxy.changeBlockLight(Block.netherFence, 1.0f);
        }
        if (TweaksTuning.Enables.fenceGateLight_enabled) {
            PfFTweaks.Proxy.changeBlockLight(Block.fenceGate, 1.0f);
        }
        if (TweaksTuning.Enables.torchlightincrease_enabled) {
            PfFTweaks.Proxy.changeBlockLight(Block.torchWood, 1.0f);
        }
    }
    
    @PfFSubscribe
    public void onEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            TweaksTuning.tuning_enabled = true;
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }
    
    @Override
    public void setupConfig() {
    }
    
    @Override
    public void setupItems() {
        if (TweaksTuning.Enables.smeltLeatherFromRottenFlesh) {
            FMLWrapper.MODE.FML.addSmelt(new ItemStack(Item.rottenFlesh), new ItemStack(Item.leather));
        }
        PfFTweaks.Proxy.changeMaxStack(Item.egg, TweaksTuning.Tweaks.egg_stacksize);
        PfFTweaks.Proxy.changeMaxStack(Item.potion, TweaksTuning.Tweaks.potion_stacksize);
        PfFTweaks.Proxy.changeMaxStack(Item.boat, TweaksTuning.Tweaks.boat_stacksize);
        PfFTweaks.Proxy.changeMaxStack(Item.enderPearl, TweaksTuning.Tweaks.enderpearl_stacksize);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    public void lateCode() {
        ExtraBeesIntegration.Integrate();
    }
    private boolean hasWorldLoaded = false;
    
    @ForgeSubscribe
    public void onWorldLoaded(WorldEvent.Load event) {
        if (!hasWorldLoaded) {
            hasWorldLoaded = true;
            this.lateCode();
            try {
                MinecraftForge.EVENT_BUS.unregister(this);
            } catch (Exception ex) {
                
            }
        }
    }
}
