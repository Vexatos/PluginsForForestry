package net.minecraft.server.denoflionsx.denLib.Config;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileRead
{
    public static ArrayList Read(String[] var0)
    {
        ArrayList var1 = new ArrayList();

        try
        {
            FileInputStream var2 = new FileInputStream(var0[0]);
            DataInputStream var3 = new DataInputStream(var2);
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var3));
            String var5;

            while ((var5 = var4.readLine()) != null)
            {
                var1.add(var5);
            }

            var3.close();
        }
        catch (Exception var6)
        {
            System.err.println("Error: " + var6.getMessage());
        }

        return var1;
    }
}
