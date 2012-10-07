package denoflionsx.API.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// You can use this annotation in place of implemeting a listener interface.
// It is used like so: @PfFSubscribe(Event = PfFEventType.PLUGIN_LOADED) over a method with the proper event parameter.
// YOU STILL NEED TO REGISTER YOUR INSTANCE WITH THE LISTENER MANAGER!

// One note: Java methods never inherit the superclass method's annotations!
// If you need inheritance you need to implement the normal interfaces instead.

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
public @interface PfFSubscribe {
    PfFEventTypes Event();
}
