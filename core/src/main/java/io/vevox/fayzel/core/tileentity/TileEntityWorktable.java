package io.vevox.fayzel.core.tileentity;

import io.vevox.fayzel.core.inventory.ContainerWorktable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Matthew Struble
 */
public class TileEntityWorktable extends TileEntityLockable implements ITickable, IInventory {

  private NonNullList<ItemStack> worktableItemStacks = NonNullList.withSize(35, ItemStack.EMPTY);

  private int researchTicks, researchMaxTicks;

  @Override
  public int getSizeInventory() {
    return worktableItemStacks.size();
  }

  @Override
  public boolean isEmpty() {
    return worktableItemStacks.isEmpty();
  }

  @Nonnull
  @Override
  public ItemStack getStackInSlot(int index) {
    return worktableItemStacks.get(index);
  }

  @Nonnull
  @Override
  public ItemStack decrStackSize(int index, int count) {
    ItemStack stack = getStackInSlot(index);
    if (stack.getCount() >= count)
      return removeStackFromSlot(index);
    else {
      stack.setCount(stack.getCount() - count);
      ItemStack removed = stack.copy();
      removed.setCount(count);
      return removed;
    }
  }

  @Nonnull
  @Override
  public ItemStack removeStackFromSlot(int index) {
    ItemStack stack = getStackInSlot(index);
    setInventorySlotContents(index, ItemStack.EMPTY);
    return stack;
  }

  @Override
  public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
    worktableItemStacks.set(index, stack);
  }

  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  @Override
  public boolean isUsableByPlayer(@Nonnull EntityPlayer player) {
    return world.getTileEntity(pos) == this && player.getDistanceSqToCenter(pos) <= 12;
  }

  @Override
  public void openInventory(@Nonnull EntityPlayer player) {
    // no-op
  }

  @Override
  public void closeInventory(@Nonnull EntityPlayer player) {
    // no-op
  }

  @Override
  public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
    if (index == 0 || (index >= 10 && index <= 34)) return true;

    return false;
  }

  @Override
  public int getField(int id) {
    switch (id) {
      case 0:
        return researchTicks;
      case 1:
        return researchMaxTicks;
      default:
        return 0;
    }
  }

  @Override
  public void setField(int id, int value) {
    switch (id) {
      case 0:
        researchTicks = value;
      case 1:
        researchMaxTicks = value;
    }
  }

  @Override
  public int getFieldCount() {
    return 2;
  }

  @Override
  public void clear() {
    worktableItemStacks.clear();
  }

  @Override
  public void update() {
    // TODO Actually research things.
  }

  @Nonnull
  @Override
  public Container createContainer(@Nonnull InventoryPlayer playerInventory, @Nonnull EntityPlayer playerIn) {
    return new ContainerWorktable(playerInventory, this);
  }

  @Nonnull
  @Override
  public String getGuiID() {
    return "fayzel-core:worktable";
  }

  @Nonnull
  @Override
  public String getName() {
    return "container.worktable";
  }

  @Override
  public boolean hasCustomName() {
    return false;
  }

  @Nonnull
  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);

    compound.setInteger("ResearchTime", researchTicks);
    ItemStackHelper.saveAllItems(compound, worktableItemStacks);

    return compound;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    ItemStackHelper.loadAllItems(compound, worktableItemStacks);

    researchTicks = compound.getInteger("ResearchTime");
  }

  @Nullable
  @Override
  @SuppressWarnings("unchecked")
  public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
    // TODO Proper capabilities
    if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      return (T) new InvWrapper(this);
    else return super.getCapability(capability, facing);
  }

}
