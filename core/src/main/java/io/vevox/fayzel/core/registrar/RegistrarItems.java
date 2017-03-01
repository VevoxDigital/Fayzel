package io.vevox.fayzel.core.registrar;

import io.vevox.fayzel.core.api.IFayzelItem;
import io.vevox.fayzel.core.item.ItemIngotCopper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("WeakerAccess")
public class RegistrarItems implements IRegistrar<IFayzelItem> {

  public final ItemIngotCopper ingotCopper;

  {
    ingotCopper = new ItemIngotCopper();
  }

  @Override
  public Set<IFayzelItem> registry() {
    Set<IFayzelItem> items = new HashSet<>();

    items.add(ingotCopper);

    return items;
  }

}
