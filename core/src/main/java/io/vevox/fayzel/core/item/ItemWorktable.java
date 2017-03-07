package io.vevox.fayzel.core.item;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelItemImpl;
import io.vevox.fayzel.core.block.BlockWorktable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * @author Matthew Struble
 */
public class ItemWorktable extends FayzelItemImpl {

  public ItemWorktable() {
    super(new ResourceLocation(FayzelCore.MOD_ID, "worktable_item"), "core");
    useExtTab();
  }

  @Override
  @Nonnull
  public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) return EnumActionResult.SUCCESS;
    if (facing != EnumFacing.UP) return EnumActionResult.FAIL;

    if (!worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)) pos = pos.up();

    EnumFacing deskFacing = player.getHorizontalFacing().getOpposite();
    BlockPos posRight = pos.offset(deskFacing.rotateYCCW()),
        posLeft = pos.offset(deskFacing.rotateY());

    ItemStack stack = player.getHeldItem(hand);

    if (player.canPlayerEdit(pos, facing, stack)
        && player.canPlayerEdit(posRight, facing, stack)
        && player.canPlayerEdit(posLeft, facing, stack)) {

      IBlockState state = worldIn.getBlockState(pos),
          stateLeft = worldIn.getBlockState(posLeft),
          stateRight = worldIn.getBlockState(posRight);

      Block block = state.getBlock(),
          blockLeft = stateLeft.getBlock(),
          blockRight = stateRight.getBlock();

      if ((block.isReplaceable(worldIn, pos) || worldIn.isAirBlock(pos))
          && (blockLeft.isReplaceable(worldIn, posLeft) || worldIn.isAirBlock(posLeft))
          && (blockRight.isReplaceable(worldIn, posRight) || worldIn.isAirBlock(posRight))
          && worldIn.getBlockState(pos.down()).getBlock().isBlockSolid(worldIn, pos.down(), EnumFacing.UP)
          && worldIn.getBlockState(posLeft.down()).getBlock().isBlockSolid(worldIn, posLeft.down(), EnumFacing.UP)
          && worldIn.getBlockState(posRight.down()).getBlock().isBlockSolid(worldIn, posRight.down(), EnumFacing.UP)) {

        BlockWorktable worktable = FayzelCore.objects().blocks.worktable;
        IBlockState facingState = worktable.getDefaultState()
            .withProperty(BlockHorizontal.FACING, player.getHorizontalFacing().getOpposite());

        worldIn.setBlockState(posLeft, facingState.withProperty(BlockWorktable.PROPERTY_PART, BlockWorktable.TablePart.LEFT));
        worldIn.setBlockState(posRight, facingState.withProperty(BlockWorktable.PROPERTY_PART, BlockWorktable.TablePart.RIGHT));
        worldIn.setBlockState(pos, facingState.withProperty(BlockWorktable.PROPERTY_PART, BlockWorktable.TablePart.CENTER));

        worldIn.notifyNeighborsRespectDebug(pos, worktable, false);
        worldIn.notifyNeighborsRespectDebug(posLeft, worktable, false);
        worldIn.notifyNeighborsRespectDebug(posRight, worktable, false);

        SoundType sound = state.getBlock().getSoundType(state, worldIn, pos, player);
        worldIn.playSound(null, pos, sound.getPlaceSound(), SoundCategory.BLOCKS,
            (sound.getVolume() + 1.0f) / 2.0f, sound.getPitch() * 0.8f);

        return EnumActionResult.SUCCESS;
      }
    }
    return EnumActionResult.FAIL;
  }
}
