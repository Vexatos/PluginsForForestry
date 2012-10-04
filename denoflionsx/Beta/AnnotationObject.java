package denoflionsx.Beta;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.core.core;

public class AnnotationObject {
    
    @PfFSubscribe(Event = PfFEventTypes.PLUGIN_LOADED)
    public void AnnotationTest(String s){
        core.print(s);
    }
    
}
