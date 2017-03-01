package io.vevox.fayzel.core.register;

import io.vevox.fayzel.core.registrar.RegistrarBlocks;
import io.vevox.fayzel.core.registrar.RegistrarItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

import java.util.function.Function;

/**
 * @author Matthew Struble
 */
public class RegisterObjects extends AbstractRegister<ResourceLocation, IForgeRegistryEntry> {

  public final RegistrarItems items;
  public final RegistrarBlocks blocks;

  public RegisterObjects() {
    blocks = new RegistrarBlocks();
    items = new RegistrarItems();

    register(items);
    register(blocks);
  }

  @Override
  public Function<IForgeRegistryEntry, ResourceLocation> keyMap() {
    return IForgeRegistryEntry::getRegistryName;
  }

}
