package io.vevox.fayzel.core.registrar;

import io.vevox.fayzel.core.api.IFayzelItem;
import io.vevox.fayzel.core.item.ItemIngotAluminum;
import io.vevox.fayzel.core.item.ItemIngotCopper;
import io.vevox.fayzel.core.item.ItemIngotTin;
import io.vevox.fayzel.core.item.ItemWorktable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("WeakerAccess")
public class RegistrarItems implements IRegistrar<IFayzelItem> {

  public final ItemIngotCopper ingotCopper;
  public final ItemIngotAluminum ingotAluminum;
  public final ItemIngotTin ingotTin;

  public final ItemWorktable worktable;

  {
    ingotCopper = new ItemIngotCopper();
    ingotAluminum = new ItemIngotAluminum();
    ingotTin = new ItemIngotTin();

    worktable = new ItemWorktable();
  }

  @Override
  public Set<IFayzelItem> registry() {
    Set<IFayzelItem> items = new HashSet<>();

    items.add(ingotCopper);
    items.add(ingotAluminum);
    items.add(ingotTin);

    items.add(worktable);

    return items;
  }

}
