package io.vevox.fayzel.core.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author Matthew Struble
 */
public abstract class FayzelItemImpl extends Item implements IFayzelItem {

  private final String modExt;

  public FayzelItemImpl(ResourceLocation key, String modExt) {
    this.modExt = modExt;
    setRegistryName(key);
    setUnlocalizedName(key.getResourcePath());
  }

  @Override
  public String name() {
    return getUnlocalizedName();
  }

  @Override
  public String modExt() {
    return modExt;
  }

  protected void useExtTab() {
    setCreativeTab(tab());
  }

  @Override
  @SuppressWarnings("NullableProblems")
  public String getUnlocalizedName(ItemStack stack) {
    return metaMap().containsKey(stack.getMetadata())
        ? "item." + metaMap().get(stack.getMetadata())
        : super.getUnlocalizedName(stack);
  }



}
