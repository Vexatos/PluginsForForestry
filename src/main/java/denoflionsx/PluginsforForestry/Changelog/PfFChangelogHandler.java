package denoflionsx.PluginsforForestry.Changelog;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Mod.Changelog.IChangeLogHandler;
import java.io.InputStream;

public class PfFChangelogHandler implements IChangeLogHandler{

    @Override
    public InputStream getFileInput() {
        return this.getClass().getResourceAsStream(PfF.core.getBuildNumber() + ".txt");
    }

    @Override
    public String getName() {
        return "Plugins for Forestry";
    }

    @Override
    public int getBuildNumber() {
        return Integer.valueOf("@BUILD@");
    }
    
    
    
    
    
}
