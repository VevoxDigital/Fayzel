package io.vevox.fayzel.core.api;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

import javax.annotation.Nonnull;

/**
 * A forge-register-able object acting as the parent interface for all
 * {@link Block Blocks} in Fayzel.
 *
 * @author Matthew Struble
 */
public interface IFayzelBlock extends IFayzelObject, IForgeRegistryEntry<Block> {

  /**
   * Gets the {@link FayzelItemBlock} for this block for using the block
   * as an item.
   * @return The item block.
   */
  @Nonnull
  default FayzelItemBlock itemBlock() {
    return new FayzelItemBlock(this);
  }

}
