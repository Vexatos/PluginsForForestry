package denoflionsx.PluginsforForestry.API.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class TunableManager {

    private Configuration config = null;

    public TunableManager(Configuration config) {
        this.config = config;
    }

    public void registerTunable(Class clazz) {
        try {
            Class[] clazzes = clazz.getDeclaredClasses();
            for (Class c : clazzes) {
                Field[] fields = c.getDeclaredFields();
                for (Field f : fields) {
                    Annotation[] anns = f.getDeclaredAnnotations();
                    for (Annotation a : anns) {
                        if (a instanceof Tunable) {
                            Property p = null;
                            f.setAccessible(true);
                            if (f.getType().getName().toLowerCase().equals("int")) {
                                int tuneable = f.getInt(null);
                                String name = f.getName();
                                Tunable t = (Tunable) a;
                                String comment = t.comment();
                                p = config.get(t.category(), name, tuneable);
                                if (!comment.equals("Tunable Value")) {
                                    p.comment = comment;
                                }
                                f.set(null, p.getInt());
                            } else if (f.getType().getName().toLowerCase().equals("boolean")) {
                                boolean tuneable = f.getBoolean(null);
                                String name = f.getName();
                                Tunable t = (Tunable) a;
                                String comment = t.comment();
                                p = config.get(t.category(), name, tuneable);
                                if (!comment.equals("Tunable Value")) {
                                    p.comment = comment;
                                }
                                f.set(null, p.getBoolean(tuneable));
                            } else if (f.getType().getName().toLowerCase().equals("double")) {
                                double tuneable = f.getDouble(null);
                                String name = f.getName();
                                Tunable t = (Tunable) a;
                                String comment = t.comment();
                                p = config.get(t.category(), name, tuneable);
                                if (!comment.equals("Tunable Value")) {
                                    p.comment = comment;
                                }
                                f.set(null, p.getDouble(tuneable));
                            }
                            config.save();
                        } else if (a instanceof DepricatedTunable) {
                            ArrayList<String> removes = new ArrayList();
                            DepricatedTunable t = (DepricatedTunable) a;
                            ConfigCategory q = config.getCategory(t.category());
                            Iterator i = q.getValues().entrySet().iterator();
                            String name = f.getName();
                            while (i.hasNext()) {
                                Map.Entry pairs = (Map.Entry) i.next();
                                String key = String.valueOf(pairs.getKey());
                                if (key.equals(name)) {
                                    removes.add(key);
                                    //PfF.Proxy.print("Pruning removed config option from file: " + key);
                                }
                            }
                            for (String s : removes) {
                                q.getValues().remove(s);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}