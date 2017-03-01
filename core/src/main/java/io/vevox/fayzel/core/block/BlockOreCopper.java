package io.vevox.fayzel.core.block;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelBlockImpl;
import io.vevox.fayzel.core.api.IBlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * @author Matthew Struble
 */
public class BlockOreCopper extends FayzelBlockImpl implements IBlockOre {

  public BlockOreCopper() {
    super(new ResourceLocation(FayzelCore.MOD_ID + ":ore_copper"),
        Material.ROCK, MapColor.STONE, "core");
    setHardness(2f).setResistance(10f).setHarvestLevel("pickaxe", 1);
    useExtTab();
  }

  @Override
  public String oreDictName() {
    return "oreCopper";
  }


  @Override
  public int tries() {
    return 20;
  }

  @Override
  public int heightMax() {
    return 64;
  }

  @Override
  public int size() {
    return 9;
  }
}
