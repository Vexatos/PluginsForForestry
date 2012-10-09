package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.HashMap;

public interface ISilkWormGenericManager {
    
    public void register(Object state);
    
    public Object getObject(String name);
    
    public int getNumberOfRegisteredObjects();
    
    @InternalUseOnly
    public HashMap getHashMap();
    
}
