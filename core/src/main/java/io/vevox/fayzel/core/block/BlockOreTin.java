package io.vevox.fayzel.core.block;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelBlockImpl;
import io.vevox.fayzel.core.api.IBlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Matthew Struble
 */
public class BlockOreTin extends FayzelBlockImpl implements IBlockOre {

  public BlockOreTin() {
    super(new ResourceLocation(FayzelCore.MOD_ID, "ore_tin"),
        Material.ROCK, MapColor.STONE, "core");
    setHardness(2f).setResistance(10f).setHarvestLevel("pickaxe", 1);
    useExtTab();
  }

  @Override
  public String oreDictName() {
    return "oreTin";
  }

  @Override
  public int tries() {
    return 20;
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
    GameRegistry.addSmelting(this, new ItemStack(FayzelCore.objects().items.ingotTin), 0.8f);
  }

}
