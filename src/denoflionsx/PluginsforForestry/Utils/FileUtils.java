package denoflionsx.PluginsforForestry.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtils {

    public static String readFileFromNet(URL url) {
        try {
            // Create a URL for the desired page
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                return str;
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return "No Data Found.";
    }
}
