package io.vevox.fayzel.core.gen;

import io.vevox.fayzel.core.api.FayzelUtil;
import io.vevox.fayzel.core.api.IBlockOre;
import io.vevox.fayzel.core.api.IFayzelObject;
import io.vevox.fayzel.core.register.AbstractRegister;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;
import java.util.function.Function;

/**
 * @author Matthew Struble
 */
public class GenFayzelOre extends AbstractRegister<String, IBlockOre> implements IWorldGenerator {

  @Override
  public Function<IBlockOre, String> keyMap() {
    return IFayzelObject::name;
  }

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world,
                       IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    entrySet().stream()
        .filter(entry -> entry.getValue().genDimension().test(world.provider.getDimension()))
        .forEach(entry -> {
          IBlockOre o = entry.getValue();
          for (int tries = 0; tries < o.tries(); tries++) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = o.heightMin() + random.nextInt(o.heightMax() - o.heightMin());
            new WorldGenMinable(o.genState(), o.size(), FayzelUtil.toGuavaPredicate(o.genPredicate()))
                .generate(world, random, new BlockPos(x, y, z));
          }
        });
  }
}
