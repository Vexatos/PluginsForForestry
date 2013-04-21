package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Dictionary.ModuleParser.SqueezeObject;
import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import java.util.HashMap;

public class PfFDictionaryParser {

    private HashMap<String, ArrayList<SqueezeObject>> lists = new HashMap();
    public static PfFDictionaryParser instance;
    private String typesFile;
    private String list;

    public PfFDictionaryParser(String typesFile, String list) {
        this.typesFile = typesFile;
        this.list = list;
    }

    public void parse() {
        for (String type : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, typesFile, instance)) {
            lists.put(denLib.StringUtils.removeSpaces(type), new ArrayList<SqueezeObject>());
            PfF.Proxy.print("Creating new dictionary list: " + type);
        }
        for (String module : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, list, instance)) {
            if (module.equals("")){
                break;
            }
            ModuleParser parse = new ModuleParser(denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, module, instance));
            PfF.Proxy.print("Reading module " + parse.getTitle() + " by " + parse.getAuthor());
            lists.get(parse.getType()).addAll(parse.getAllEntires());
        }
    }

    public static void createInstance() {
        instance = new PfFDictionaryParser("VALID_TYPES.txt", "OreDictionaryModules.txt");
        instance.parse();
    }

    public HashMap<String, ArrayList<SqueezeObject>> getLists() {
        return lists;
    }
}
