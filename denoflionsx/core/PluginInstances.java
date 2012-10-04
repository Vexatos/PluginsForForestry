package denoflionsx.core;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.Loader.pluginLoader;
import denoflionsx.plugins.Buildcraft.pluginBuildcraft;
import denoflionsx.plugins.Forestry.pluginForestry;

public class PluginInstances {
    
    public static pluginLoader Loader = new pluginLoader("Loader",""); 
    
    public static pluginForestry Forestry = new pluginForestry("pluginForestry",EnumModIDs.MODS.FORESTRY.getID());
    public static pluginBuildcraft Buildcraft = new pluginBuildcraft("pluginBuildcraft",EnumModIDs.MODS.BUILDCRAFT_CORE.getID());
}
