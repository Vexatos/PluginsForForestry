package denoflionsx.PluginsforForestry.Plugins.Wiki.Items;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class WikiBook {

    private ItemStack book = new ItemStack(Item.writtenBook);

    public WikiBook(String author, String title, String target) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("author", author);
        nbt.setString("title", title);
        NBTTagList pages = new NBTTagList();
        pages.appendTag(new NBTTagString("1", title + " \n"));
        int i = 1;
        String[] content = denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, target, this);
        for (String s : content){
            i++;
            pages.appendTag(new NBTTagString(String.valueOf(i), s));
        }
        nbt.setTag("pages", pages);
        book.setTagCompound(nbt);
    }

    public ItemStack getBook() {
        return book;
    }
}
