package denoflionsx.PluginsforForestry.Changelog;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import java.io.File;
import java.util.EnumSet;

public class ChangelogReader implements ITickHandler {

    private final int delay = 500;
    private int count = 0;
    private boolean display = true;
    private BiMap<Integer, Boolean> hasBroadcast = HashBiMap.create();
    private File saveFile = new File(PfF.core.configDir.getAbsolutePath() + "/hasAlerted.BiMap");

    public static void create() {
        TickRegistry.registerTickHandler(new ChangelogReader(), Side.CLIENT);
    }

    public ChangelogReader() {
        if (saveFile.exists()) {
            hasBroadcast = denLib.FileUtils.readBiMapFromFile(saveFile);
        } else {
            PfF.Proxy.print("Creating new broadcast file.");
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (display) {
            count++;
            if (count > delay) {
                if (hasBroadcast.get(PfF.core.getBuildNumber()) == null) {
                    String[] changelog = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, PfF.core.getBuildNumber() + ".txt", this);
                    for (String s : changelog) {
                        if (s.equals(denLib.StringUtils.readError)){
                            continue;
                        }
                        PfF.Proxy.sendMessageToPlayer(s);
                    }
                    hasBroadcast.put(PfF.core.getBuildNumber(), display);
                    denLib.FileUtils.saveBiMapToFile(hasBroadcast, saveFile);
                }
                display = false;
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "PfF Changelog";
    }
}
