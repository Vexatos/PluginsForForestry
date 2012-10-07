package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.plugins.BlueSilkWorm.Genders.SilkWormGenericGender;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGender;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGenericManager;
import java.util.HashMap;

public class SilkWormGenderManager implements ISilkWormGenericManager{

    public static HashMap<String, ISilkWormGender> genders = new HashMap();

    public SilkWormGenderManager() {
        register(new SilkWormGenericGender("Male"));
        register(new SilkWormGenericGender("Female"));
    }

    @Override
    public HashMap getHashMap() {
        return genders;
    }

    @Override
    public int getNumberOfRegisteredObjects() {
        return genders.size();
    }

    @Override
    public Object getObject(String name) {
        if (genders.get(name) != null){
            return genders.get(name);
        }else{
            return null;
        }
    }

    @Override
    public final void register(Object state) {
        ISilkWormGender g = (ISilkWormGender)state;
        genders.put(g.getGender(),g);
    }
 
}
