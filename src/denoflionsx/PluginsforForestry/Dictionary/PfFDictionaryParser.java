package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class PfFDictionaryParser {

    private HashMap<String, ArrayList<String>> lists = new HashMap();
    public static PfFDictionaryParser instance;
    private String typesFile;
    private String list;

    public PfFDictionaryParser(String typesFile, String list) {
        this.typesFile = typesFile;
        this.list = list;
    }

    public void parse() {
        for (String type : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, typesFile, instance)) {
            lists.put(type, new ArrayList<String>());
            PfF.Proxy.print("Creating new dictionary list: " + type);
        }
        for (String module : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, list, instance)) {
            ModuleParser parse = new ModuleParser(denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, module, instance));
            PfF.Proxy.print("Reading module " + parse.getTitle() + " by " + parse.getAuthor());
            lists.get(parse.getType()).addAll(parse.getAllEntires());
        }
    }

    public static void createInstance() {
        instance = new PfFDictionaryParser("VALID_TYPES.txt", "OreDictionaryModules.txt");
        instance.parse();
    }

    public ItemStack[] getAllEntriesForType(String type) {
        ArrayList<String> s = lists.get(type);
        ArrayList<ItemStack> stacks = new ArrayList();
        for (String a : s) {
            stacks.addAll(OreDictionary.getOres(a));
        }
        return stacks.toArray(new ItemStack[stacks.size()]);
    }
}
