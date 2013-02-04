package denoflionsx.PluginsforForestry.Config;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import denoflionsx.PluginsforForestry.Annotations.Updater;
import denoflionsx.PluginsforForestry.Annotations.UpdaterParser;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.FileUtils;
import denoflionsx.PluginsforForestry.Version.PfFVersion;
import denoflionsx.denLib.EnumTextColor;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

@Updater(tag = "Beta", version = PfFVersion.version, url = "https://dl.dropbox.com/u/23892866/VersionCheck/core.txt")
public class CoreUpdater implements IScheduledTickHandler {

    private static ArrayList<Class> updaters = new ArrayList();
    private static ArrayList<Class> alerts = new ArrayList();
    private static HashMap<Class, String> versions = new HashMap();
    private static boolean alertPlayer = false;
    private static CoreUpdater updater;
    private static boolean sent = false;

    @Override
    public int nextTickSpacing() {
        return 400;
    }

    @Override
    public String getLabel() {
        return "PfF update label";
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        sent = true;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (BuildFlags.isCustomBuild){
            return;
        }
        if (alertPlayer) {
            for (Class c : alerts) {
                String t = EnumTextColor.DARK_GREEN.ColorString("[PfF2]");
                PfF.Proxy.sendMessageToPlayer(t + ": Update found: " + EnumTextColor.DARK_RED.ColorString(UpdaterParser.getTag(c) + " " + versions.get(c)));
            }
        }
        if (!PfF.Core.config.hasKey("messages", "wiki")) {
            String t = EnumTextColor.DARK_GREEN.ColorString("[PfF2]");
            PfF.Proxy.sendMessageToPlayer(t + ": PfF is looking for wiki contributors!");
            PfF.Proxy.sendMessageToPlayer(t + ": PM denoflions on MinecraftForum if interested.");
            PfF.Proxy.sendMessageToPlayer(t + ": (This message will only display once.)");
            PfF.Core.config.get("messages", "wiki", true);
            PfF.Core.config.save();
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        if (sent) {
            return EnumSet.noneOf(TickType.class);
        }
        return EnumSet.of(TickType.PLAYER);
    }

    static {
        updater = new CoreUpdater();
        registerUpdater(updater.getClass());
        TickRegistry.registerScheduledTickHandler(updater, Side.CLIENT);
    }

    public static void registerUpdater(Class c) {
        updaters.add(c);
    }

    public static void updateCheck() {
        for (Class c : updaters) {
            String current = UpdaterParser.getVersion(c);
            String online = "";
            try {
                online = FileUtils.readFileFromNet(new URL(UpdaterParser.getURL(c)));
                if (!current.equals(online)) {
                    PfF.Proxy.print("Update found! " + UpdaterParser.getTag(c) + ": " + online);
                    alertPlayer = true;
                    alerts.add(c);
                    versions.put(c, online);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
