package denoflionsx.PluginsforForestry.Core.CoreMod;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import denoflionsx.PluginsforForestry.Core.CoreMod.ASM.ColorDBRequest;
import java.util.Map;

public class PfFCoreMod implements IFMLLoadingPlugin {

    @Override
    public String[] getLibraryRequestClass() {
        return null;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{ColorDBRequest.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }
}
