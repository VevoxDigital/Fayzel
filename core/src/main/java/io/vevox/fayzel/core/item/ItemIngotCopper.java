package io.vevox.fayzel.core.item;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelItemImpl;
import net.minecraft.util.ResourceLocation;

/**
 * @author Matthew Struble
 */
public class ItemIngotCopper extends FayzelItemImpl {

  public ItemIngotCopper() {
    super(new ResourceLocation(FayzelCore.MOD_ID + ":ingot_copper"), "core");
    useExtTab();
  }

  @Override
  public String oreDictName() {
    return getUnlocalizedName();
  }

}
