package io.vevox.fayzel.core.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * @author Matthew Struble
 */
public class FayzelBlockImpl extends Block implements IFayzelBlock {

  private final String modExt;

  public FayzelBlockImpl(ResourceLocation registryName, Material material, MapColor color, String modExt) {
    super(material, color);
    this.modExt = modExt;

    setRegistryName(registryName);
    setUnlocalizedName(registryName.getResourcePath());
  }

  @Override
  public String name() {
    return getUnlocalizedName();
  }

  @Override
  public String modExt() {
    return modExt;
  }

  protected void useExtTab() {
    setCreativeTab(tab());
  }
}
