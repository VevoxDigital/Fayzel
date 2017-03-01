package io.vevox.fayzel.core.proxy;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelItemImpl;
import io.vevox.fayzel.core.api.IFayzelObject;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;

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

    logger.info("Registering objects...");
    FayzelCore.objects().entrySet().forEach(entry -> {
      GameRegistry.register(entry.getValue());

      if (entry.getValue() instanceof IFayzelObject) {
        IFayzelObject object = (IFayzelObject) entry.getValue();
        ItemStack is = null;

        if (object instanceof FayzelItemImpl) is = new ItemStack((FayzelItemImpl) object);

        if (object.oreDictName() != null && is != null) OreDictionary.registerOre(object.oreDictName(), is);
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
