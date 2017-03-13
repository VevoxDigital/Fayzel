package io.vevox.fayzel.core.api;

import io.vevox.fayzel.core.client.gui.inventory.GuiWorktable;
import io.vevox.fayzel.core.inventory.ContainerWorktable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * @author Matthew Struble
 */
public class FayzelGuiHandler implements IGuiHandler {

  public enum Guis {
    WORKTABLE
  }

  @Nullable
  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch (Guis.values()[ID]) {
      case WORKTABLE: return new ContainerWorktable(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
      default: return null;
    }
  }

  @Nullable
  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch (Guis.values()[ID]) {
      case WORKTABLE: return new GuiWorktable((ContainerWorktable) getServerGuiElement(ID, player, world, x, y, z));
      default: return null;
    }
  }

}
