package io.vevox.fayzel.core.register;

import io.vevox.fayzel.core.tab.TabFayzelCore;
import net.minecraft.creativetab.CreativeTabs;

import java.util.function.Function;

/**
 * @author Matthew Struble
 */
public class RegisterTabs extends AbstractRegister<String, CreativeTabs> {

  public RegisterTabs() {
    register(new TabFayzelCore());
  }

  @Override
  public Function<CreativeTabs, String> keyMap() {
    return tab -> tab.getTabLabel().substring("fayzel.".length());
  }
}
