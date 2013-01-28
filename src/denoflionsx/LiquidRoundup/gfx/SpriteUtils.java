package denoflionsx.LiquidRoundup.gfx;

import cpw.mods.fml.client.SpriteHelper;
import cpw.mods.fml.common.Mod.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.LiquidRoundup.API.Annotations.Wrapper;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.denLib;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.minecraft.DoNotMoveMe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Property;

@SideOnly(Side.CLIENT)
public class SpriteUtils {

    private static final String x = "x";
    private static final String y = "y";
    private static final HashMap<Integer, HashMap<String, Integer>> coordinates = new HashMap();

    static {
        int a = Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution();
        int currentIndex = -1;
        int currentX = 0;
        int currentY = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                currentIndex++;
                HashMap<String, Integer> coords = new HashMap();
                coords.put(x, currentX);
                coords.put(y, currentY);
                coordinates.put(currentIndex, coords);
                currentX += a;
            }
            currentX = 0;
            currentY += a;
        }
    }

    public static void addBlankSheetToMap(String spritesheet) {
        String line = "0000000000000000";
        String l = "";
        for (int i = 0; i < 15; i++) {
            l = l + line.replaceAll("0", "1");
        }
        SpriteHelper.registerSpriteMapForFile(spritesheet, l);
    }

    public static Color FigureOutSpriteColor(String sprite) {
        BufferedImage buf = ImageIOWrapper.read(new File(sprite));
        if (buf == null) {
            try {
                URL Url = Item.class.getResource(sprite);
                Toolkit tk = Toolkit.getDefaultToolkit();
                Image i = tk.getImage(Url);
                buf = toBufferedImage(i);
            } catch (Exception ex) {
                LiquidRoundup.Proxy.print("Something is seriously messed up here!");
                LiquidRoundup.Proxy.print("Forcefully kicking this liquid in the ass...");
                return new Color(0, 0, 0);
            }
        }
        int c = buf.getRGB(5, 5);
        int red = (c & 0x00ff0000) >> 16;
        int green = (c & 0x0000ff00) >> 8;
        int blue = c & 0x000000ff;
        String[] p = sprite.split("/");
        String parse = p[p.length - 1];
        parse = parse.replace(".png", "");
        Property r = LiquidRoundup.Core.config.get("colors.liquids." + parse, "red", red);
        Property g = LiquidRoundup.Core.config.get("colors.liquids." + parse, "green", green);
        Property b = LiquidRoundup.Core.config.get("colors.liquids." + parse, "blue", blue);
        Color color = new Color(r.getInt(), g.getInt(), b.getInt());
        return color;

    }

    public static BufferedImage toBufferedImage(Image image) {

        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels
        boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the
        // screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha == true) {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
        } // No screen

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha == true) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            return ((BufferedImage) image).getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        return pg.getColorModel().hasAlpha();
    }

    public static String ExtractSprite(ItemStack item) {
        String name = "";
        try {
            URL Url = DoNotMoveMe.class.getResource(item.getItem().getTextureFile());
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image i = tk.getImage(Url);
            BufferedImage buf = toBufferedImage(i);
            int[] coords = TranslateIndexToCoords(item.getIconIndex());
            BufferedImage clone = buf.getSubimage(coords[0], coords[1], Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution(), Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution());
            name = denLib.Hash(item.itemID + " | " + item.getItemDamage());
            File f = new File(LiquidRoundup.Proxy.getConfigDir() + "gfx/" + name + ".png");
            if (!f.exists()) {
                f.mkdirs();
                ImageIOWrapper.write(clone, "png", f);
            }
        } catch (Exception ex) {
            return createNewLiquidSprite(denLib.Hash(item.itemID + " | " + item.getItemDamage()), 0, 0, 0);
        }
        return LiquidRoundup.Proxy.getConfigDir() + "gfx/" + denLib.Hash(item.itemID + " | " + item.getItemDamage()) + ".png";
    }

    public static int[] TranslateIndexToCoords(int index) {
        try {
            HashMap<String, Integer> coords = coordinates.get(index);
            return new int[]{coords.get(x), coords.get(y)};
        } catch (Exception ex) {
            LiquidRoundup.Proxy.print("Index translation failure. Failed on: " + index + ". Replacing with 0.");
            return TranslateIndexToCoords(0);
        }
    }

    public static void ripSpritesheetApart(String path) {
        URL Url = Item.class.getResource(path);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image i = tk.getImage(Url);
        BufferedImage buf = toBufferedImage(i);
        for (int a = 0; a < 256; a++) {
            int[] coords = TranslateIndexToCoords(a);
            BufferedImage clone = buf.getSubimage(coords[0], coords[1], Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution(), Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution());
            String coordname = "Sprite-" + coords[0] + "_" + coords[1];
            File f = new File(LiquidRoundup.Proxy.getConfigDir() + "SpritesheetRip" + File.separator + coordname + ".png");
            if (!f.exists()) {
                f.mkdirs();
                ImageIOWrapper.write(clone, "png", f);
            }
        }
    }

    public static String createNewLiquidSprite(String name, int r, int g, int b) {
        LiquidRoundup.Proxy.print("Generating generic texture for " + name + ".");
        try {
            BufferedImage sprite = new BufferedImage(Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution(), Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getTexturePackResolution(), BufferedImage.TYPE_INT_RGB);
            Color target = new Color(r, g, b);
            for (int X = 0; X < sprite.getWidth(); X++) {
                for (int Y = 0; Y < sprite.getHeight(); Y++) {
                    sprite.setRGB(X, Y, target.getRGB());
                }
            }
            File f = new File(LiquidRoundup.Proxy.getConfigDir() + "gfx/" + name + ".png");
            if (!f.exists()) {
                f.mkdirs();
                ImageIOWrapper.write(sprite, "png", f);
            }
            return LiquidRoundup.Proxy.getConfigDir() + "gfx/" + name + ".png";
        } catch (Exception ex) {
            LiquidRoundup.Proxy.print("Sprite creation failed!");
            return "oh crap!";
        }
    }

    public static String createNewSpritesheetFromTemplate(String name) {
        URL Url = Item.class.getResource("/denoflionsx/LiquidRoundup/gfx/template.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image i = tk.getImage(Url);
        BufferedImage buf = toBufferedImage(i);
        File f = new File(LiquidRoundup.Proxy.getConfigDir() + "gfx/" + name);
        if (!f.exists()) {
            f.mkdirs();
            ImageIOWrapper.write(buf, "png", f);
        }
        return "/denoflionsx/LiquidRoundup/gfx/" + name;
    }

    @Wrapper
    public static class ImageIOWrapper {

        // Wrap all this crap in Try/Catch blocks so that I don't have to do it
        // every time I use these methods.
        public static void write(BufferedImage image, String type, File f) {
            try {
                if (f.exists()) {
                    f.delete();
                }
                ImageIO.write(image, type, f);
            } catch (Exception ex) {
                //ex.printStackTrace();
                // LiquidRoundup.Proxy.print("Image write crapped out!");
            }
        }

        public static BufferedImage read(File f) {
            try {
                return ImageIO.read(f);
            } catch (Exception ex) {
                //ex.printStackTrace();
                //LiquidRoundup.Proxy.print("Image read crapped out!");
                return null;
            }
        }
    }
}
