package denoflionsx.Beta;

import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesTest {

    public static Properties prop = new Properties();

    public static void set() {
        prop.setProperty("Test1", "Rawr");
        prop.setProperty("Test2", "Hai");
        prop.setProperty("Test3","Added!");
        try {
            prop.storeToXML(new FileOutputStream("Test.xml"), null);
            prop.store(new FileOutputStream("Test.properties"), null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
