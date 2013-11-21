package denoflionsx.PluginsforForestry.Lang;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import java.util.HashMap;

public class PfFTranslator {

    public static PfFTranslator instance;
    private static final String defaultLang = "en_US";
    private boolean notify = false;
    private HashMap<String, String> trans = null;

    public static void createInstance() {
        instance = new PfFTranslator();
    }

    public String translateKey(String key) {
        return this.translateKey(key, PfF.Proxy.getLang());
    }

    public void overrideKey(String key, String overrideKey) {
        try {
            String s = this.translateKey(overrideKey);
            if (trans.containsKey(key)) {
                trans.put(key, s);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String translateKey(String key, String lang) {
        if (trans == null) {
            String[] p = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, lang + ".lang", this);
            if (p[0].equals(denLib.StringUtils.readError)) {
                // No local available. Use default.
                p = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, defaultLang + ".lang", this);
                if (!notify) {
                    PfF.Proxy.warning("No localization data for language " + lang + ". Using en_US instead.");
                    PfF.Proxy.print("Feel free to translate the en_US.lang file in the jar and submit it!");
                    notify = true;
                }
            }
            HashMap<String, String> l = new HashMap();
            for (String a : p) {
                String[] sp = a.split("=");
                String key1 = denLib.StringUtils.removeSpaces(sp[0]);
                String value1 = sp[1].trim();
                l.put(key1, value1);
            }
            trans = l;
        }
        return trans.get(key);
    }
}
