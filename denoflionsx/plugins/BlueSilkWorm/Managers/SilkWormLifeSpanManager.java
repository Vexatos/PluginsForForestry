package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGenericManager;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpan;
import denoflionsx.plugins.BlueSilkWorm.LifeSpans.SilkWormGenericLifeSpan;
import java.util.HashMap;

public class SilkWormLifeSpanManager implements ISilkWormGenericManager{

    public static HashMap<String, ISilkWormLifeSpan> Lifespans = new HashMap();

    public SilkWormLifeSpanManager() {
        register(new SilkWormGenericLifeSpan("Shortest",1));
        register(new SilkWormGenericLifeSpan("Short",2));
        register(new SilkWormGenericLifeSpan("Normal",3));
        register(new SilkWormGenericLifeSpan("Long",4));
        register(new SilkWormGenericLifeSpan("Longest",5));
    }

    @Override
    public Object getObject(String name) {
        if (Lifespans.get(name) != null){
            return Lifespans.get(name);
        }else{
            return null;
        }
    }

    @Override
    public final void register(Object state) {
        ISilkWormLifeSpan s = (ISilkWormLifeSpan)state;
        Lifespans.put(s.getLifeSpanName(),s);
    }

    @Override
    public int getNumberOfRegisteredObjects() {
        return Lifespans.size();
    }

    @Override
    public HashMap getHashMap() {
        return Lifespans;
    }

}
