package io.vevox.fayzel.core.block;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelBlockImpl;
import io.vevox.fayzel.core.api.IBlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Matthew Struble
 */
public class BlockOreAluminum extends FayzelBlockImpl implements IBlockOre {


  public BlockOreAluminum() {
    super(new ResourceLocation(FayzelCore.MOD_ID, "ore_aluminum"),
        Material.ROCK, MapColor.STONE, "core");
    setHardness(3f).setResistance(10f).setHarvestLevel("pickaxe", 1);
    useExtTab();
  }

  @Override
  public String oreDictName() {
    return "oreAluminum";
  }

  @Override
  public int tries() {
    return 8;
  }

  @Override
  public int heightMax() {
    return 32;
  }

  @Override
  public int size() {
    return 8;
  }

  @Override
  public void postRegister() {
    // add alternate spelling to OreDict
    OreDictionary.registerOre("oreAluminium", this);

    GameRegistry.addSmelting(this, new ItemStack(FayzelCore.objects().items.ingotAluminum), 1);
  }
}
