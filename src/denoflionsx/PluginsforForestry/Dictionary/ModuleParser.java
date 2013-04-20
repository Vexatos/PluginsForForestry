package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;

public class ModuleParser {

    private String[] lines;

    public ModuleParser(String[] lines) {
        this.lines = lines;
    }

    public String getTitle() {
        return findTagAndParse(lines, "# Title:");
    }

    public String getAuthor() {
        return findTagAndParse(lines, "# Author:");
    }

    public String getType() {
        return findTagAndParse(lines, "# Type:");
    }
    
    public ArrayList<String> getAllEntires(){
        ArrayList<String> l = new ArrayList();
        for (String s : lines){
            if (!s.contains("#")){
                l.add(denLib.StringUtils.removeSpaces(s));
            }
        }
        return l;
    }

    private static String findTagAndParse(String[] lines, String find) {
        for (String s : lines) {
            if (s.contains(find)) {
                return denLib.StringUtils.removeSpaces(s.replace(find, ""));
            }
        }
        return "";
    }
}
