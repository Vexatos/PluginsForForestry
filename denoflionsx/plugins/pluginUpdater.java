package denoflionsx.plugins;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import net.minecraft.src.ModLoader;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;

public class pluginUpdater extends pluginBase {
    
    public static String vfile = "https://dl.dropbox.com/u/23892866/PluginsforForestry/Version/PFFVersion.cfg";
    private Config compare = new Config("PFFVersion.cfg");
    
    
    public pluginUpdater() {
        this.name = "pluginUpdater";
        this.register();
    }

    @Override
    public void register() {
        if (!this.loaded) {
            this.loaded = init();
        }
    }

    @Override
    protected void defaults() {
    }

    @Override
    protected boolean init() {
        if (core.isBetaBuild){
            core.print("Update check cancelled. Beta build detected.");
            return false;
        }
        try{
        core.print("Checking for updates...");
        this.compare.deleteConfig();
        saveUrl(compare.ConfigDir + "PFFVersion.cfg",vfile);
        this.compare.readFile();
        if (core.modVersion().equals(this.compare.getOption("Version"))){
            core.print("PFF is up to date!");
        }else{
            String msg = "[PluginsforForestry]: New Version " + this.compare.getOption("Version") + " available!";
            core.print(msg);
            if (core.isClient()){
                ModLoader.getMinecraftInstance().thePlayer.addChatMessage(msg);
            }
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected void recipes() {
    }

    public void saveUrl(String filename, String urlString) throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
            
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }
}
