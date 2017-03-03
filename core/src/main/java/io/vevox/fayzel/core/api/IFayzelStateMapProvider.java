package io.vevox.fayzel.core.api;

import net.minecraft.client.renderer.block.statemap.IStateMapper;

/**
 * An interface denoting an object that provides a {@link IStateMapper}.
 * @author Matthew Struble
 */
public interface IFayzelStateMapProvider {

  /**
   * Gets the mapper for the {@link net.minecraftforge.client.model.ModelLoader}.
   * @return The state mapper.
   */
  IStateMapper mapper();

}
