package denoflionsx.PluginsforForestry.Lang;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import java.util.HashMap;

public class PfFTranslator {

    public static PfFTranslator instance;
    private static final String defaultLang = "en_US";

    public static void createInstance() {
        instance = new PfFTranslator();
    }

    public String translateKey(String key) {
        return this.translateKey(key, defaultLang);
    }

    public String translateKey(String key, String lang) {

        String[] p = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, lang + ".lang", this);
        HashMap<String, String> l = new HashMap();
        for (String a : p) {
            String[] sp = a.split("=");
            String key1 = denLib.StringUtils.removeSpaces(sp[0]);
            String value1 = denLib.StringUtils.removeSpaces(sp[1]);
            l.put(key1, value1);
        }
        return l.get(key);
    }
}
