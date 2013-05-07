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
    private BiMap<Integer, Integer> hasBroadcast = HashBiMap.create();
    private File saveFile_OLD = new File(PfF.core.configDir.getAbsolutePath() + "/hasAlerted.BiMap");
    private File saveFile = new File(PfF.core.configDir.getAbsolutePath() + "/hasAlerted_fixed.BiMap");

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
                    this.convertOld();
                    String[] changelog = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, PfF.core.getBuildNumber() + ".txt", this);
                    for (String s : changelog) {
                        if (s.equals(denLib.StringUtils.readError)) {
                            continue;
                        }
                        PfF.Proxy.sendMessageToPlayer(s);
                    }
                    hasBroadcast.put(PfF.core.getBuildNumber(), PfF.core.getBuildNumber());

                    denLib.FileUtils.saveBiMapToFile(hasBroadcast, saveFile);
                }
                display = false;
            }
        }
    }

    // I had to move away from booleans because BiMaps need unique values.
    // This converts the old format to the new format.
    private void convertOld() {
        if (saveFile_OLD.exists()) {
            PfF.Proxy.print("Converting old broadcast file to new format.");
            BiMap<Integer, Boolean> old;
            old = denLib.FileUtils.readBiMapFromFile(saveFile_OLD);
            for (Integer i : old.keySet()) {
                this.hasBroadcast.put(i, i);
            }
            if (!saveFile_OLD.delete()) {
                saveFile_OLD.deleteOnExit();
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
