package net.minecraft.src.denoflionsx.Achievements;

import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.src.Achievement;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class AchievementParser {

    public InputStream in = getClass().getResourceAsStream("test.txt");
    public InputStream in2 = getClass().getResourceAsStream("Test2.txt");

    public void test() {
        parse(in);
        parse(in2);
    }

    public void parse(InputStream input) {
        String t = convertStreamToString(input);
        String a[] = t.split(";");
        ArrayList<String> lines = new ArrayList();
        for (String z : a) {
            if (z.contains("[") || z.contains("]")) {
            } else {
                lines.add(z.replace("\n", "").replace("\r", ""));
            }
        }
        String name = lines.get(0);
        String desc = lines.get(1);
        int ID = Integer.valueOf(lines.get(2));
        int x = Integer.valueOf(lines.get(3));
        int y = Integer.valueOf(lines.get(4));
        ItemStack icon = API.getItem(lines.get(5));
        String getID = lines.get(6);
        Achievement acheesement;
        if (getID.contains("linkto")){
            String[] parsing = getID.split(":");
            getID = parsing[1];
            acheesement = PfFAchievement.achievements.get(Integer.valueOf(getID));
        }else{
            acheesement = PfFAchievement.unlinked;
        }
        PfFAchievement New = new PfFAchievement(ID + 5000,denLib.toLowerCaseNoSpaces(name),x,y,icon,acheesement,name,desc,false,false,false);
    }

    public static String convertStreamToString(java.io.InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }
}
