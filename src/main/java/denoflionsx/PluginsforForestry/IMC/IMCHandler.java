package denoflionsx.PluginsforForestry.IMC;

import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import java.util.ArrayList;

public class IMCHandler {
    
    public void onIMCMessage(IMCMessage m) {
        if (m.key.equals("blacklist")) {
            String[] parse = m.getStringValue().split("@");
            if (parse.length < 1) {
                PfF.Proxy.severe("Recieved an invalid IMC message: " + m.getStringValue() + " from " + m.getSender());
            } else {
                if (PluginLR.blackLists.get(parse[0]) == null) {
                    PluginLR.blackLists.put(parse[0], new ArrayList());
                    PfF.Proxy.print("Created new blacklist: " + parse[0]);
                }
                PluginLR.blackLists.get(parse[0]).add(parse[1]);
                PfF.Proxy.print("Processed blacklist request: " + m.getStringValue());
            }
        }
    }
}
