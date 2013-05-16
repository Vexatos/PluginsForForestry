package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

public class Blacklists {
    
    /*
     * Due to mostly load order issues, LR will sometimes create duplicates.
     * So this blacklist prevents that.
     */
    public static final String[] ironBucket = new String[]{"Creosote Oil", "redstone", "ender", "Steam"};
    public static final String[] capsule = new String[]{"Oil", "Fuel", "Steam", "Creosote Oil"};
    public static final String[] woodenBucket = new String[]{"Steam"};
    public static final String[] barrel = new String[]{"Steam"};
    // These are liquids that are known to cause issues with the managers.
    public static final String[] known_mismaps = new String[]{"Molten Iron"};
}
