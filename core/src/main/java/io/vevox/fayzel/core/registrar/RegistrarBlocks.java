package io.vevox.fayzel.core.registrar;

import io.vevox.fayzel.core.api.IFayzelBlock;
import io.vevox.fayzel.core.block.BlockOreCopper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("WeakerAccess")
public class RegistrarBlocks implements IRegistrar<IFayzelBlock> {

  public final BlockOreCopper oreCopper;

  {
    oreCopper = new BlockOreCopper();
  }

  @Override
  public Set<IFayzelBlock> registry() {
    Set<IFayzelBlock> r = new HashSet<>();

    r.add(oreCopper);

    return r;
  }
}
