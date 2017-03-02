package io.vevox.fayzel.core.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * An abstract implementation class for {@link Item}, specific for Fayzel items.
 *
 * Fayzel items have an "extension" used for creative tabs and deciding which child mod
 * the item belongs to. For example, {@link io.vevox.fayzel.core.item.ItemIngotCopper},
 * has the "core" extension, belonging to "fayzel-core"
 *
 * @author Matthew Struble
 */
public abstract class FayzelItemImpl extends Item implements IFayzelItem {

  private final String modExt;

  /**
   * Constructs a new {@link FayzelItemImpl}, using the given properties
   *
   * @param key The resource location for this item
   * @param modExt This item's mod extension
   */
  public FayzelItemImpl(ResourceLocation key, String modExt) {
    this.modExt = modExt;
    setRegistryName(key);
    setUnlocalizedName(key.getResourcePath());
  }

  @Nonnull
  @Override
  public String name() {
    return getUnlocalizedName();
  }

  @Nonnull
  @Override
  public String modExt() {
    return modExt;
  }

  /**
   * Sets this {@link Item}'s creative tab to the {@link #modExt() mod}'s default creative tab.
   */
  protected void useExtTab() {
    setCreativeTab(tab());
  }

  @Override
  public void addInformation(@Nullable ItemStack stack, @Nullable EntityPlayer playerIn,
                             @Nullable List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    if (tooltip != null) tooltip.addAll(IFayzelObject.getInformation(getUnlocalizedName(stack)));
  }

  @Nonnull
  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return metaMap().containsKey(stack.getMetadata())
        ? "item." + metaMap().get(stack.getMetadata())
        : super.getUnlocalizedName(stack);
  }


}
