package io.vevox.fayzel.core.api;

import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * @author Matthew Struble
 */
public abstract class FayzelGuiContainer extends GuiContainer {

  public final FayzelContainer container;

  public FayzelGuiContainer(FayzelContainer inventorySlotsIn) {
    super(inventorySlotsIn);
    this.container = inventorySlotsIn;
  }

}
