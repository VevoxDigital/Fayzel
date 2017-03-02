package io.vevox.fayzel.core.registrar;

import io.vevox.fayzel.core.api.IFayzelItem;
import io.vevox.fayzel.core.item.ItemIngotAluminum;
import io.vevox.fayzel.core.item.ItemIngotCopper;
import io.vevox.fayzel.core.item.ItemIngotTin;

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

  {
    ingotCopper = new ItemIngotCopper();
    ingotAluminum = new ItemIngotAluminum();
    ingotTin = new ItemIngotTin();
  }

  @Override
  public Set<IFayzelItem> registry() {
    Set<IFayzelItem> items = new HashSet<>();

    items.add(ingotCopper);
    items.add(ingotAluminum);
    items.add(ingotTin);

    return items;
  }

}
