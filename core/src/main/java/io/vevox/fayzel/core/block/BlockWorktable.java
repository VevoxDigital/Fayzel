package io.vevox.fayzel.core.block;

import io.vevox.fayzel.core.FayzelCore;
import io.vevox.fayzel.core.api.FayzelBlockImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * @author Matthew Struble
 */
public class BlockWorktable extends FayzelBlockImpl {

  public static final PropertyEnum<TablePart> PROPERTY_PART = PropertyEnum.create("part", TablePart.class);

  public enum TablePart implements IStringSerializable {
    LEFT,
    RIGHT,
    CENTER;

    @Override
    public String getName() {
      return this.toString().toLowerCase();
    }
  }

  public BlockWorktable() {
    super(new ResourceLocation(FayzelCore.MOD_ID, "worktable"), Material.WOOD, MapColor.WOOD, "core");
    setDefaultState(blockState.getBaseState()
        .withProperty(PROPERTY_PART, TablePart.CENTER)
        .withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
  }

  @Override
  public boolean isBlockSolid(IBlockAccess worldIn, @Nonnull BlockPos pos, EnumFacing facing) {
    return facing == EnumFacing.UP;
  }

  private BlockPos getCenterPart(IBlockAccess worldIn, BlockPos pos) {
    IBlockState state = worldIn.getBlockState(pos);

    if (!(state.getBlock() instanceof BlockWorktable)) return pos;

    EnumFacing facing = state.getValue(BlockHorizontal.FACING);

    switch (state.getValue(PROPERTY_PART)) {
      case LEFT:
        return pos.offset(facing.rotateYCCW());
      case RIGHT:
        return pos.offset(facing.rotateY());
      default:
        return pos;
    }
  }

  @Override
  @SuppressWarnings("deprecation")
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
    if (state.getValue(PROPERTY_PART) == TablePart.CENTER) {
      EnumFacing facing = state.getValue(BlockHorizontal.FACING);

      if (worldIn.getBlockState(pos.offset(facing.rotateY())).getBlock() != this
          || worldIn.getBlockState(pos.offset(facing.rotateYCCW())).getBlock() != this) {
        worldIn.setBlockToAir(pos);

        if (!worldIn.isRemote) this.dropBlockAsItem(worldIn, pos, state, 0);
      }
    } else if (worldIn.getBlockState(getCenterPart(worldIn, pos)).getBlock() != this)
      worldIn.setBlockToAir(pos);
  }

  @Override
  public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
    if (player.capabilities.isCreativeMode && state.getValue(PROPERTY_PART) != TablePart.CENTER) {
      BlockPos center = getCenterPart(worldIn, pos);
      if (worldIn.getBlockState(center).getBlock() == this) worldIn.setBlockToAir(center);
    }
  }

  @Nonnull
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return state.getValue(PROPERTY_PART) == TablePart.CENTER ? FayzelCore.objects().items.worktable : Items.AIR;
  }

  @Override
  public void dropBlockAsItemWithChance(World worldIn, @Nonnull BlockPos pos,
                                        @Nonnull IBlockState state, float chance, int fortune) {
    if (state.getValue(PROPERTY_PART) == TablePart.CENTER)
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Nonnull
  @Override
  @SuppressWarnings("deprecation")
  public EnumPushReaction getMobilityFlag(IBlockState state) {
    return EnumPushReaction.DESTROY;
  }

  @Nonnull
  @Override
  public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target,
                                @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player) {
    return new ItemStack(FayzelCore.objects().items.worktable);
  }

  @Override
  public int metaMax() {
    return 12;
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return (4 * state.getValue(PROPERTY_PART).ordinal())
        + (state.getValue(BlockHorizontal.FACING).ordinal() - 2);
  }

  @Nonnull
  @Override
  @SuppressWarnings("deprecation")
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState()
        .withProperty(PROPERTY_PART, TablePart.values()[meta / 4])
        .withProperty(BlockHorizontal.FACING, EnumFacing.values()[(meta % 4) + 2]);
  }

  @Nonnull
  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, BlockHorizontal.FACING, PROPERTY_PART);
  }
}
