package io.vevox.fayzel.core.tab;

import io.vevox.fayzel.core.FayzelCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @author Matthew Struble
 */
public class TabFayzelCore extends CreativeTabs {

  public TabFayzelCore() {
    super("fayzel.core");
  }

  @Override
  @SuppressWarnings("NullableProblems")
  public ItemStack getTabIconItem() {
    return new ItemStack(FayzelCore.objects().items.ingotCopper);
  }

}
