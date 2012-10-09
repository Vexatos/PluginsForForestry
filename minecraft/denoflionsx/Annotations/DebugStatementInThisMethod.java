package denoflionsx.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

// Easily searchable debug statement marker.

@Target(ElementType.METHOD) 
public @interface DebugStatementInThisMethod {}
