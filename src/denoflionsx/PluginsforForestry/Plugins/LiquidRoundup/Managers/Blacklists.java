package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

public class Blacklists {

    /*
     * Due to mostly load order issues, LR will sometimes create duplicates.
     * So this blacklist prevents that.
     */
    public static final String[] ironBucket = new String[]{"Creosote Oil", "redstone", "ender", "Steam", "immibis.liquidxp", "Oil", "Fuel"};
    public static final String[] capsule = new String[]{"Oil", "Fuel", "Steam", "Creosote Oil"};
    public static final String[] woodenBucket = new String[]{"Steam", "Lava"};
    public static final String[] barrel = new String[]{"Steam"};
}
