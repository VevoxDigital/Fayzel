package io.vevox.fayzel.core.api;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;

/**
 * @author Matthew Struble
 */
public abstract class FayzelContainer extends Container {

  public final InventoryPlayer playerInventory;
  public final IInventory inventory;

  public FayzelContainer(InventoryPlayer playerInventory, IInventory inv) {
    this.playerInventory = playerInventory;
    this.inventory = inv;
  }

}
