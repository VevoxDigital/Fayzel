package io.vevox.fayzel.core.api;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * @author Matthew Struble
 */
public interface IFayzelBlock extends IFayzelObject, IForgeRegistryEntry<Block> {

  default ItemBlock itemBlock() {
    return new FayzelItemBlock(this);
  }

}
