package io.vevox.fayzel.core.api;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;

/**
 * An extension of {@link ITileEntityProvider} that also provides the class
 * of the tile entity provided.
 * @author Matthew Struble
 */
public interface IFayzelTileEntityProvider extends ITileEntityProvider {

  /**
   * Gets the class for the tile entity this provider provides.
   * @return The tile entity.
   */
  Class<? extends TileEntity> tileClass();

}
