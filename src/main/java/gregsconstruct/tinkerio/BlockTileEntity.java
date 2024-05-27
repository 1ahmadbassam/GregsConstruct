package gregsconstruct.tinkerio;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {
    public BlockTileEntity(Material material, String name, String registryName) {
        super(material, name, registryName);
    }

    public abstract Class<TE> getTileEntityClass();

    public TileEntity getTileEntity(IBlockAccess world, BlockPos pos) {
        return world.getTileEntity(pos);
    }

    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Nullable
    public abstract TE createTileEntity(@Nonnull World var1, @Nonnull IBlockState var2);

    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntity tile = this.getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (itemHandler != null && itemHandler.getSlots() > 0) {
            for (int i = 0; i < itemHandler.getSlots(); ++i) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    world.spawnEntity(item);
                }
            }

            super.breakBlock(world, pos, state);
        }
    }
}
