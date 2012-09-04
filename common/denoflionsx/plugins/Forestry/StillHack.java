package denoflionsx.plugins.Forestry;

import java.util.ArrayList;

public class StillHack {

    public static void engage() {

        try {
            Class[] a = Class.forName("forestry.factory.MachineDistillation").getDeclaredClasses();

            int RecipeManagerID = -1;

            for (int i = 0; i < a.length; i++) {

                if (a[i].getName().equals("forestry.factory.MachineDistillation$RecipeManager")) {

                    RecipeManagerID = i;

                }

            }


            ArrayList editable = (ArrayList) a[RecipeManagerID].getField("recipes").get(null);
            ArrayList passBack = editable;

            editable.remove(editable.size() - 1);

            a[RecipeManagerID].getField("recipes").set(passBack, editable);
        } catch (Exception ex) {
        }


    }
}
