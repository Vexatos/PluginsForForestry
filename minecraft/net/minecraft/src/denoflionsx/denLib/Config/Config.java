package net.minecraft.src.denoflionsx.denLib.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class Config {

    public HashMap<String, String> Options = new HashMap();
    protected ArrayList<String> defaults = new ArrayList();
    protected ArrayList<String> rawFile = new ArrayList();
    public static final String ConfigDir = Minecraft.getMinecraftDir() + File.separator + "config" + File.separator + "denoflionsx" + File.separator;
    protected String ConfigFile = "";

    public Config(String c) {

        this.ConfigFile = ConfigDir + c;

    }

    public void readFile() {

        ArrayList read = FileRead.Read(new String[]{this.ConfigFile});
        Iterator<String> al = read.iterator();
        while (al.hasNext()) {

            String next = al.next();
            parseLine(next);

        }

    }

    public void setOption(String key, String param) {

        denLib.print("Set Option " + key + " to " + param);
        Options.put(key, param);

    }

    public String getOption(String key) {
        String op = Options.get(key);
        if (op != null) {
            return op;
        }else{
            denLib.print("Attempting to update config file...");
            for (String s : this.defaults){
                if (s.contains(key)){
                    parseLine(s);
                    op = Options.get(key);
                    new File(this.ConfigFile).delete();
                    FileWrite.write(this.ConfigFile,this.rawFile);
                    return op;
                }
            }
        }
        return "";
    }

    public void parseLine(String line) {

        char s = "=".charAt(0);
        rawFile.add(line);
        if (!line.substring(0, 1).equals("#")) {
            // # is a comment line.
            if (!line.substring(0, 1).equals("[")) {
                // [] (Brackets) are for section titles.
                String read[] = line.split("=");
                if (read.length == 2) {
                    setOption(read[0], read[1]);
                }
            }
        }

    }

    public void addDefault(String d) {

        defaults.add(d);

    }

    public void writeConfig() {

        if (!new File(this.ConfigFile).exists()) {
            new File(ConfigDir).mkdir();
            FileWrite.write(this.ConfigFile, defaults);
            denLib.print("Wrote config file: " + this.ConfigFile);
        }
    }
}
