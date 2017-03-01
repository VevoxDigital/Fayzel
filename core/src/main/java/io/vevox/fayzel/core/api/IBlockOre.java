package io.vevox.fayzel.core.api;

import io.vevox.fayzel.core.FayzelCore;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.function.Predicate;

/**
 * @author Matthew Struble
 */
public interface IBlockOre extends IFayzelBlock {

  /**
   * Number of tries to place this ore in the chunk.
   *
   * @return The number of tries.
   */
  int tries();

  /**
   * The max height this ore is to generate at.
   *
   * @return The max height.
   */
  int heightMax();

  /**
   * The minimum height this ore is to generate at.
   *
   * @return The min height.
   */
  default int heightMin() {
    return 0;
  }

  /**
   * The maximum size of the ore vein.
   *
   * @return The max vein size.
   */
  int size();

  /**
   * A predicate for valid generation dimensions.
   *
   * @return The dimensions predicate
   */
  default Predicate<Integer> genDimension() {
    return d -> d != -1;
  }

  /**
   * Gets the block state this block should generate with. Defaults to the {@link Block#getDefaultState()} method if not
   * overridden.
   *
   * @return The block state.
   */
  default IBlockState genState() {
    return ((Block) FayzelCore.objects().get(getRegistryName())).getDefaultState();
  }

  /**
   * Gets the generator predicate for this block's generation. Defaults to permitting only vanilla stone if not
   * overridden.
   *
   * @return The generator predicate.
   */
  default Predicate<IBlockState> genPredicate() {
    return s -> s.getBlock().getUnlocalizedName().equals(Blocks.STONE.getUnlocalizedName());
  }

}
