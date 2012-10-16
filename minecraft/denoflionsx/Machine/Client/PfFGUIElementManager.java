package denoflionsx.Machine.Client;

import java.util.HashMap;

public class PfFGUIElementManager {
    
    private static HashMap<String, PfFGUIElement> elements = new HashMap();
    
    public static void addElement(String name, PfFGUIElement element){
        elements.put(name,element);
    }
    
    public static PfFGUIElement getElement(String name){
        if (elements.get(name) != null){
            return elements.get(name);
        }else{
            return null;
        }
    }
    
}
