package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGenericManager;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpanCategory;
import denoflionsx.plugins.BlueSilkWorm.LifeSpans.SilkWormLifeSpanCategory;
import java.util.HashMap;

public class SilkWormLifeSpanManager implements ISilkWormGenericManager {

    public static HashMap<String, Object> objects = new HashMap();

    public SilkWormLifeSpanManager() {
        this.register(new SilkWormLifeSpanCategory("Very Short",1,60));
        this.register(new SilkWormLifeSpanCategory("Short",61,120));
        this.register(new SilkWormLifeSpanCategory("Normal",121,180));
        this.register(new SilkWormLifeSpanCategory("Long",181,240));
        this.register(new SilkWormLifeSpanCategory("Very Long",241,300));
    }

    @Override
    public HashMap getHashMap() {
        return objects;
    }

    @Override
    public int getNumberOfRegisteredObjects() {
        return objects.size();
    }

    @Override
    public Object getObject(String name) {
        if (objects.get(name) != null) {
            return objects.get(name);
        } else {
            return null;
        }
    }

    @Override
    public final void register(Object state) {
        if (state instanceof ISilkWormLifeSpanCategory) {
            ISilkWormLifeSpanCategory cat = (ISilkWormLifeSpanCategory) state;
            objects.put(cat.getCategoryLabel(),cat);
        }
    }
}
