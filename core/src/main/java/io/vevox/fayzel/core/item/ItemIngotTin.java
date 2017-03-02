package io.vevox.fayzel.core.item;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelItemImpl;
import net.minecraft.util.ResourceLocation;

/**
 * @author Matthew Struble
 */
public class ItemIngotTin extends FayzelItemImpl {

  public ItemIngotTin() {
    super(new ResourceLocation(FayzelCore.MOD_ID, "ingot_tin"), "core");
    useExtTab();
  }

  @Override
  public String oreDictName() {
    return "ingotTin";
  }

}
