package io.vevox.fayzel.core.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * An abstract implementation class for {@link Block}, specific for Fayzel blocks.
 *
 * Fayzel blocks have an "extension" used for creative tabs and deciding which child mod
 * the block belongs to. For example, {@link io.vevox.fayzel.core.block.BlockOreCopper},
 * has the "core" extension, belonging to "fayzel-core"
 *
 * @author Matthew Struble
 */
public abstract class FayzelBlockImpl extends Block implements IFayzelBlock {

  private final String modExt;

  /**
   * Constructs a new {@link FayzelBlockImpl}, using the given properties
   *
   * @param registryName The resource location of the block
   * @param material     The block's material
   * @param color        The block's map color
   * @param modExt       The mod extension of the block
   */
  public FayzelBlockImpl(@Nonnull ResourceLocation registryName,
                         @Nonnull Material material, @Nonnull MapColor color, @Nonnull String modExt) {
    super(material, color);
    this.modExt = modExt;

    setRegistryName(registryName);
    setUnlocalizedName(registryName.getResourcePath());
  }

  @Nonnull
  @Override
  public String name() {
    return getUnlocalizedName();
  }

  @Nonnull
  @Override
  public String modExt() {
    return modExt;
  }

  /**
   * Sets this {@link Block}'s creative tab to the {@link #modExt() mod}'s default creative tab.
   */
  protected void useExtTab() {
    setCreativeTab(tab());
  }
}
