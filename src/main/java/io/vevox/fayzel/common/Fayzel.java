package io.vevox.fayzel.common;

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
@Mod(modid = Fayzel.MOD_ID)
@SuppressWarnings("unused WeakerAccess")
public class Fayzel {

  /**
   * The mod ID this mod should tie to.
   */
  public static final String MOD_ID = "fayzel";

  @SidedProxy(serverSide = "io.vevox.fayzel.common.CommonProxy", clientSide = "io.vevox.fayzel.client.ClientProxy")
  private static CommonProxy proxy;
  private static Fayzel instance;

  /**
   * Get the current instance of Fayzel.
   *
   * @return The instance.
   */
  public static Fayzel get() {
    return instance;
  }


  private Logger logger;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    logger = LogManager.getLogger(this);
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent e) {

  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent e) {

  }

  /**
   * Get the logger attached to this mod.
   *
   * @return The logger.
   */
  public Logger getLogger() {
    return logger;
  }
}
