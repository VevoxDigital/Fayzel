package io.vevox.fayzel.core.api;

import com.google.common.collect.ImmutableList;
import io.vevox.fayzel.core.FayzelCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A base mod object for all blocks and items belonging to Fayzel.
 *
 * @author Matthew Struble
 */
public interface IFayzelObject extends Comparable<IFayzelObject> {

  /**
   * Attempts to localize the name of the given {@link IFayzelObject}, returning the localized name if available in the
   * current language, the fallback language, or the {@link #name() unlocalized name} if not.
   *
   * @param object The object to localize.
   *
   * @return The localized name, if available.
   *
   * @throws NullPointerException If the given object is null.
   */
  @Nonnull
  @SideOnly(Side.CLIENT)
  static String localize(IFayzelObject object) throws NullPointerException {
    return I18n.format(object.name());
  }

  /**
   * Gets the information list for the given unlocalized name.
   *
   * @param unlocalizedName The unlocalized name.
   *
   * @return The information list.
   */
  @Nonnull
  @SideOnly(Side.CLIENT)
  static List<String> getInformation(String unlocalizedName) {
    List<String> info = new ArrayList<>();
    info.add(I18n.format(unlocalizedName + ".ttip"));
    info.add("");
    info.addAll(ImmutableList.copyOf(GuiScreen.isShiftKeyDown()
        ? FayzelUtil.colorize(I18n.format(unlocalizedName + ".desc")).split("\\\\n")
        : new String[]{FayzelUtil.TOOLTIP}
    ));
    return info;
  }

  default int compareTo(@Nonnull IFayzelObject object) {
    return name().compareTo(object.name());
  }

  /**
   * Gets the unlocalized name of this game object.
   *
   * @return The unlocalized name.
   */
  @Nonnull
  String name();

  /**
   * Gets the {@link #localize(IFayzelObject) localized name} of this game object in the current language.
   *
   * @return The localized name.
   */
  @Nonnull
  @SideOnly(Side.CLIENT)
  default String displayName() {
    return localize(this);
  }

  /**
   * Gets the extension name of the mod this game object is registered to.
   *
   * @return The mod extension.
   */
  @Nonnull
  String modExt();

  /**
   * Gets the ore dictionary name of this game object. Returns null if there is to be no ore dictionary name.
   *
   * @return The ore dictionary name.
   */
  @Nullable
  default String oreDictName() {
    return null;
  }

  /**
   * Gets the {@link CreativeTabs} registered with this object's {@link #modExt()}.
   *
   * @return The creative tab.
   */
  @Nonnull
  default CreativeTabs tab() {
    return FayzelCore.tabs().get(modExt());
  }

  /**
   * Gets the max meta value for render registration and other uses, ranging from 0 to the returned value. In most
   * cases, any meta max less than 0 will be risen to 0.
   *
   * @return The max meta.
   */
  default int metaMax() {
    return 0;
  }

  /**
   * Gets the map of meta integers to name for texture loading.
   *
   * @return The map.
   */
  @Nonnull
  default Map<Integer, String> metaMap() {
    Map<Integer, String> map = new HashMap<>();
    map.put(0, name().substring(5));
    return map;
  }

  /**
   * Called when all game objects have been registered to their respective register.
   */
  default void postRegister() {
  }

}
