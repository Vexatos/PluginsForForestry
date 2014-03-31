package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.denLib.Lib.denLib;

public class ModuleParserTemplate {

    protected String[] lines;

    public ModuleParserTemplate(String[] lines) {
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

    protected static String findTagAndParse(String[] lines, String find) {
        for (String s : lines) {
            if (s.contains(find)) {
                return denLib.StringUtils.removeSpaces(s.replace(find, ""));
            }
        }
        return "";
    }
}
