package io.vevox.fayzel.core.proxy;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * @author Matthew Struble
 */
public class CommonProxy {

  private Logger logger;

  public void preInit(FMLPreInitializationEvent e) {
    logger = FayzelCore.instance().logger();
    logger.info("!! Common Pre-Init start  !!");


    logger.info("!! Common Pre-Init finish !!");
  }

  public void init(FMLInitializationEvent e) {
    logger.info("!! Common Init start  !!");

    NetworkRegistry.INSTANCE.registerGuiHandler(FayzelCore.instance(), new FayzelGuiHandler());

    logger.info("Registering generators...");
    GameRegistry.registerWorldGenerator(FayzelCore.ores(), 5);

    logger.info("Registering objects...");
    FayzelCore.objects().entrySet().stream()
        .filter(entry -> entry.getValue() instanceof IFayzelObject)
        // sequential-ize the stream in hopes to alphabetize the keys
        // the order is otherwise pretty dumb
        .sequential()
        .sorted(Comparator.comparing(a -> a.getKey().getResourcePath()))
        .forEach(entry -> {
          GameRegistry.register(entry.getValue());

          if (entry.getValue() instanceof IFayzelObject) {
            IFayzelObject object = (IFayzelObject) entry.getValue();
            ItemStack is = null;

            // register the actual object
            if (object instanceof FayzelItemImpl) {
              FayzelItemImpl item = (FayzelItemImpl) object;

              is = new ItemStack(item);
              if (item.tab() != null) //noinspection ConstantConditions
                item.setCreativeTab(item.tab());
            } else if (object instanceof FayzelBlockImpl) {
              FayzelBlockImpl block = (FayzelBlockImpl) object;

              is = new ItemStack(block);
              if (block.tab() != null) //noinspection ConstantConditions
                block.setCreativeTab(block.tab());
              GameRegistry.register(block.itemBlock());
            }

            // register any tile entities
            if (object instanceof IFayzelTileEntityProvider)
              GameRegistry.registerTileEntity(((IFayzelTileEntityProvider) object).tileClass(), object.name());

            // register any ore dictionary values
            if (object.oreDictName() != null && is != null) {
              logger.info(String.format(" * Registering oreDict for %s as '%s'", object.getClass().getSimpleName(), object.oreDictName()));
              OreDictionary.registerOre(object.oreDictName(), is);
            }

            // register custom state map
            if (object instanceof IFayzelStateMapProvider && object instanceof Block)
              ModelLoader.setCustomStateMapper((Block) object, ((IFayzelStateMapProvider) object).mapper());
          }
        });

    logger.info("Invoking post-register...");
    FayzelCore.objects().entrySet().forEach(entry -> {
      if (entry.getValue() instanceof IFayzelObject)
        ((IFayzelObject) entry.getValue()).postRegister();
    });

    logger.info("!! Common Init finish !!");
  }

  public void postInit(FMLPostInitializationEvent e) {
    logger.info("!! Common Post-Init start  !!");


    logger.info("!! Common Post-Init finish !!");
  }

}
