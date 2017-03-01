package io.vevox.fayzel.core.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Matthew Struble
 */
public class FayzelItemBlock extends ItemBlock implements IFayzelItem {

  public FayzelItemBlock(IFayzelBlock block) {
    super((Block) block);
    if (block.metaMax() > 0) setHasSubtypes(true);

    //noinspection ConstantConditions
    setRegistryName(block.getRegistryName());
  }

  @Override
  public String name() {
    return block.getUnlocalizedName();
  }

  @Override
  public String modExt() {
    return block instanceof IFayzelBlock ? ((IFayzelBlock) block).modExt() : "core";
  }

  @Override
  public void addInformation(@Nullable ItemStack stack, @Nullable EntityPlayer playerIn,
                             @Nullable List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    if (tooltip != null) tooltip.addAll(IFayzelObject.getInformation(getUnlocalizedName(stack)));
  }

  @Override
  @Nonnull
  public String getUnlocalizedName(ItemStack stack) {
    if (!(block instanceof IFayzelBlock)) return block.getUnlocalizedName();
    IFayzelBlock block = (IFayzelBlock) getBlock();
    return block.metaMap().containsKey(stack.getMetadata())
        ? "tile."+block.metaMap().get(stack.getMetadata())
        : super.getUnlocalizedName(stack);
  }
}
