package denoflionsx.ColorDBGenerator;

import denoflionsx.denLib.Lib.denLib;

public class ColorDBGenerator {

    public static final ColorDBGenerator instance = new ColorDBGenerator();

    public static void main(String[] args) {
        instance.generate(args[0], args[1]);
    }

    public void generate(String key, String value) {
        denLib.SQLHelper.putStringInDB(new String[]{"ColorOverlayValues.db", "ColorValues"}, new Object[]{key, value});
    }
}
