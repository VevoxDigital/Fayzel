package io.vevox.fayzel.core;

import io.vevox.fayzel.core.gen.GenFayzelOre;
import io.vevox.fayzel.core.proxy.CommonProxy;
import io.vevox.fayzel.core.register.RegisterObjects;
import io.vevox.fayzel.core.register.RegisterTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("unused")
@Mod(modid = FayzelCore.MOD_ID, version = "0.1.0")
public class FayzelCore {

  /**
   * Forge mod ID
   */
  public static final String MOD_ID = "fayzel-core";

  @SidedProxy(
      clientSide = "io.vevox.fayzel.core.proxy.ClientProxy",
      serverSide = "io.vevox.fayzel.core.proxy.CommonProxy"
  )
  private static CommonProxy proxy;

  private static FayzelCore instance;

  private static RegisterTabs tabs;
  private static RegisterObjects objects;

  private static GenFayzelOre ores;

  private Logger logger;

  {
    instance = this;
  }

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    logger = LogManager.getLogger(e.getModMetadata().name);

    ores = new GenFayzelOre();

    tabs = new RegisterTabs();
    objects = new RegisterObjects();

    proxy.preInit(e);
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.init(e);
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent e) {
    proxy.postInit(e);
  }

  public Logger logger() {
    return logger;
  }

  public static FayzelCore instance() {
    return instance;
  }

  public static RegisterTabs tabs() {
    return tabs;
  }

  public static RegisterObjects objects() {
    return objects;
  }

  public static GenFayzelOre ores() {
    return ores;
  }

}
