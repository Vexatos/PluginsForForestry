package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;

public class ReflectionModuleParser extends ModuleParser {

    public ReflectionModuleParser(String[] lines) {
        super(lines);
    }

    public ArrayList<ClassAndField> getClassAndFieldEntries() {
        ArrayList<String> getEntries = this.getAllEntires();
        ArrayList<ClassAndField> r = new ArrayList();
        for (String s : getEntries) {
            String[] parse = s.split(",");
            String c = denLib.StringUtils.removeSpaces(parse[0]);
            String f = denLib.StringUtils.removeSpaces(parse[1]);
            String m = denLib.StringUtils.removeSpaces(parse[2]);
            ClassAndField cf = new ClassAndField(c, f, Integer.valueOf(m));
            r.add(cf);
        }
        return r;
    }

    public static class ClassAndField {

        private String className;
        private String fieldName;
        private int meta;

        public ClassAndField(String className, String fieldName, int meta) {
            this.className = className;
            this.fieldName = fieldName;
            this.meta = meta;
        }

        public String getClassName() {
            return className;
        }

        public String getFieldName() {
            return fieldName;
        }

        public int getMeta() {
            return meta;
        }
    }
}
