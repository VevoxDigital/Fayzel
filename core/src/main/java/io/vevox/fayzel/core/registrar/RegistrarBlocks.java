package io.vevox.fayzel.core.registrar;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.IFayzelBlock;
import io.vevox.fayzel.core.block.BlockOreAluminum;
import io.vevox.fayzel.core.block.BlockOreCopper;
import io.vevox.fayzel.core.block.BlockOreTin;
import io.vevox.fayzel.core.block.BlockWorktable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("WeakerAccess")
public class RegistrarBlocks implements IRegistrar<IFayzelBlock> {

  public final BlockOreCopper oreCopper;
  public final BlockOreAluminum oreAluminum;
  public final BlockOreTin oreTin;

  public final BlockWorktable worktable;

  {
    oreCopper = new BlockOreCopper();
    FayzelCore.ores().register(oreCopper);

    oreAluminum = new BlockOreAluminum();
    FayzelCore.ores().register(oreAluminum);

    oreTin = new BlockOreTin();
    FayzelCore.ores().register(oreTin);

    worktable = new BlockWorktable();
  }

  @Override
  public Set<IFayzelBlock> registry() {
    Set<IFayzelBlock> r = new HashSet<>();

    r.add(oreCopper);
    r.add(oreAluminum);
    r.add(oreTin);

    r.add(worktable);

    return r;
  }
}
