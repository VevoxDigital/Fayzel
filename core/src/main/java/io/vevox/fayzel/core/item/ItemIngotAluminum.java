package io.vevox.fayzel.core.item;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelItemImpl;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Matthew Struble
 */
public class ItemIngotAluminum extends FayzelItemImpl {

  public ItemIngotAluminum() {
    super(new ResourceLocation(FayzelCore.MOD_ID, "ingot_aluminum"), "core");
    useExtTab();
  }

  @Override
  public String oreDictName() {
    return "ingotAluminum";
  }

  @Override
  public void postRegister() {
    // add the alternate spelling to oreDict
    OreDictionary.registerOre("ingotAluminium", this);
  }

}
