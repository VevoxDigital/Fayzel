package io.vevox.fayzel.core.proxy;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.IFayzelObject;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Matthew Struble
 */
public class ClientProxy extends CommonProxy {

  private Logger logger;

  @Override
  public void preInit(FMLPreInitializationEvent e) {
    super.preInit(e);
    logger = FayzelCore.instance().logger();
    logger.info("!! Client Pre-Init start  !!");



    logger.info("!! Client Pre-Init finish !!");
  }

  @Override
  public void init(FMLInitializationEvent e) {
    super.init(e);
    logger.info("!! Client Init start  !!");

    logger.info("Registering model resources...");
    FayzelCore.objects().entrySet().stream()
        .filter(entry -> entry.getValue() instanceof IFayzelObject)
        // see note in CommonProxy about sequential-ization
        .sequential()
        .sorted(Comparator.comparing(a -> a.getKey().getResourcePath()))
        .forEach(entry -> {
          IFayzelObject o = (IFayzelObject) entry.getValue();
          String n = o.name().substring(5);

          List<ModelResourceLocation> variants = new ArrayList<>();

          logger.debug(String.format(" * Registering %s (%s) with %d meta",
              o.getClass().getSimpleName(), o.modExt(), o.metaMax()));

          for (int i = 0; i <= o.metaMax(); i++) {
            String mn = o.metaMap().containsKey(i) ? o.metaMap().get(i) : n;
            logger.debug(String.format(" >> Using %s:%d from %s", o.getClass().getSimpleName(), i, mn));
            ModelResourceLocation resourceLocation =
                new ModelResourceLocation(FayzelCore.MOD_ID + ":" + mn, "inventory");
            variants.add(resourceLocation);
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
                o instanceof Item ? (Item) o : Item.getItemFromBlock((Block) o), i,
                resourceLocation
            );
          }
          if (o instanceof Item)
            ModelBakery.registerItemVariants(
                (Item) o, (ResourceLocation[]) variants.toArray(new ResourceLocation[variants.size()])
            );
        });

    logger.info("!! Client Init finish !!");
  }

  @Override
  public void postInit(FMLPostInitializationEvent e) {
    super.postInit(e);
    logger.info("!! Client Post-Init start  !!");



    logger.info("!! Client Post-Init finish !!");
  }

}
