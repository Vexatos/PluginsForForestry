package net.minecraft.server.denoflionsx.denLib.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FileWrite
{
    public static void write(String var0, ArrayList var1)
    {
        try
        {
            FileWriter var2 = new FileWriter(var0);
            PrintWriter var3 = new PrintWriter(var2);
            Iterator var4 = var1.iterator();

            while (var4.hasNext())
            {
                var3.println(var4.next().toString());
            }

            var3.close();
        }
        catch (IOException var5)
        {
            var5.printStackTrace();
        }
    }
}
