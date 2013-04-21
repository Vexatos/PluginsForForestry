package denoflionsx.PluginsforForestry.Core;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Plugins.Localization.PluginLang;
import denoflionsx.denLib.CoreMod.Updater.IDenUpdate;
import denoflionsx.denLib.CoreMod.denLibCore;
import denoflionsx.denLib.Mod.denLibMod;
import java.io.*;
import net.minecraftforge.common.Configuration;

public class PfFCore implements IDenUpdate {

    public final String containergfxpath = "denoflionsx/PluginsforForestry/gfx/containers/";
    public final String liquidgfxpath = "denoflionsx/PluginsforForestry/gfx/liquids/";
    public File configDir;
    private File sourceFile;
    private String updatedUrl;

    public PfFCore(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setupContainers() {
        PluginLR.containers.registerNewContainer("Barrel", new String[]{containergfxpath + "barrel"}, PfFTuning.getInt(PfFTuning.Items.barrel_ItemID), PfFTuning.getInt(PfFTuning.Items.barrel_capacity));
    }

    public void setupLocalization() {
        PfF.plugins.registerPlugin(new PluginLang());
    }

    public void setupConfig(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory().getAbsolutePath() + "/denoflionsx/PluginsforForestry/");
        PfFTuning.config = new Configuration(new File(configDir.getAbsolutePath() + "/PluginsforForestry.cfg"));
        denLibMod.tuning.registerTunableClass(PfFTuning.class);
        this.test();
    }

    public void setupRendering() {
        PfF.Proxy.registerClientSide();
    }

    public void test() {
    }

    @Override
    public String getUpdaterUrl() {
        return "https://dl.dropboxusercontent.com/u/23892866/VersionCheck/PfF3x/PfF.txt";
    }

    @Override
    public String getUpdaterName() {
        return "Plugins for Forestry 3.X Alpha";
    }

    @Override
    public void registerWithUpdater() {
        denLibCore.updater.registerUpdate(PfF.core);
    }

    @Override
    public File getSourceFile() {
        return this.sourceFile;
    }

    @Override
    public int getBuildNumber() {
        return 0;
    }

    @Override
    public String getUpdatedModFileUrl() {
        return this.updatedUrl;
    }

    @Override
    public void setUpdatedModFileUrl(String url) {
        this.updatedUrl = url;
    }
    
    
}
