package denoflionsx.PluginsforForestry.IMC;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Core.PfF;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class IMCHandler {
    
    public HashMap<String, ArrayList<String>> banLists = new HashMap();
    
    public IMCHandler() {
        String[] s = PfFAPI.getContainerTagsForBanRequest();
        for (String list : s) {
            banLists.put(list, new ArrayList());
        }
    }
    
    public void onIMC(IMCEvent event) {
        for (IMCMessage msg : event.getMessages()) {
            if (msg.isStringMessage()) {
                if (banLists.get(msg.key) == null) {
                    PfF.Proxy.warning("Recieved an invalid IMC message from " + msg.getSender() + ".");
                    return;
                }
                banLists.get(msg.key).add(msg.getStringValue());
                PfF.Proxy.print("Recieved blacklist request from " + msg.getSender() + ". Banning " + msg.getStringValue() + " from " + msg.key + ".");
            }
        }
    }

    // This just wraps the old blacklists to the new IMC system.
    public void setupBanList(Class c) {
        try {
            for (Field f : c.getDeclaredFields()) {
                Object o = f.get(null);
                String[] list = (String[]) o;
                for (String s : list) {
                    PfFAPI.sendLiquidBanRequest(f.getName(), s);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // This wraps the lists to arrays so they work with the current manager code.
    public String[] getBanListAsArray(String key) {
        ArrayList<String> list = banLists.get(key);
        return list.toArray(new String[list.size()]);
    }
}
