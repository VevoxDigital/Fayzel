package io.vevox.fayzel.core.inventory;

import io.vevox.fayzel.core.api.FayzelContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;


/**
 * @author Matthew Struble
 */
public class ContainerWorktable extends FayzelContainer {

  enum WorktableSlot {
    ETCHING_INPUT(102, 8),
    ETCHING_OUTPUT(102, 80),
    CRAFTING_SECONDARY(129, 8);

    final int x, y;

    WorktableSlot(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * SLOTS:
   *
   * 0 ...... Etching Input
   * 1 ...... Etching Output
   * 2 ...... Crafting Secondary Input
   * 3-7 .... Research Input
   * 8-9 .... Research Output
   * 10-34 .. Crafting Grid
   * 35-61 .. Player Main Inventory
   * 62-70 .. Player Hot-bar
   *
   * @param playerInventory
   * @param worktableInventory
   */
  public ContainerWorktable(InventoryPlayer playerInventory, IInventory worktableInventory) {
    super(playerInventory, worktableInventory);

    // single slots, from enum
    Arrays.stream(WorktableSlot.values())
        .forEach(s -> addSlotToContainer(new Slot(inventory, s.ordinal(), s.x, s.y)));

    int index = WorktableSlot.values().length;

    // research
    for (int i = 0; i < 5; i++)
      addSlotToContainer(new Slot(inventory, index++, 8 + (18 * i), 110));
    for (int i = 0; i < 2; i++)
      addSlotToContainer(new Slot(inventory, index++, 205 + (18 * i), 110));

    // crafting
    for (int i = 0; i < 25; i++)
      addSlotToContainer(new Slot(inventory, index++,
          151 + ((i == 0 ? 0 : i % 5) * 18),
          8 + ((i == 0 ? 0 : i / 5) * 18)));

    // player inventory
    for (int i = 0; i < 27; i++)
      addSlotToContainer(new Slot(playerInventory, i + 9,
          44 + ((i == 0 ? 0 : i % 9) * 18),
          140 + ((i == 0 ? 0 : i / 9) * 18)));
    for (int i = 0; i < 9; i++)
      addSlotToContainer(new Slot(playerInventory, i, 44 + (i * 18), 198));
  }

  @Override
  public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
    return inventory.isUsableByPlayer(playerIn);
  }

  @Nonnull
  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    ItemStack prev = ItemStack.EMPTY;
    Slot slot = inventorySlots.get(index);

    if (slot != null && slot.getHasStack()) {
      ItemStack target = slot.getStack();
      prev = target.copy();

      // custom behavior
      if (index <= 35) {
        if (!mergeItemStack(target, 35, 71, true))
          return ItemStack.EMPTY;
      } else {
        // TODO Place research kits and crystals in their proper slots.
        if (!mergeItemStack(target, 10, 35, false))
          return ItemStack.EMPTY;
      }

      if (target.getCount() == 0) slot.putStack(ItemStack.EMPTY);
      else slot.onSlotChanged();

      if (target.getCount() == prev.getCount()) return ItemStack.EMPTY;
      slot.onTake(playerIn, target);
    }

    return prev;
  }
}
