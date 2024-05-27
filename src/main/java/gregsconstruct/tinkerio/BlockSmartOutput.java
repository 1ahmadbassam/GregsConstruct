package gregsconstruct.tinkerio;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tinker_io.TinkerIO;
import tinker_io.plugins.theoneprob.TOPInfoProvider;
import tinker_io.tileentity.TileEntitySmartOutput;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockSmartOutput extends BlockTileEntity<TileEntitySmartOutput> implements TOPInfoProvider {
    public BlockSmartOutput(String name, String registryName) {
        super(Material.ROCK, name, registryName);
        this.setHardness(3.0F);
    }

    public Class<TileEntitySmartOutput> getTileEntityClass() {
        return TileEntitySmartOutput.class;
    }

    @Nullable
    public TileEntitySmartOutput createTileEntity(World world, IBlockState state) {
        return new TileEntitySmartOutput();
    }

    public boolean onBlockActivated(World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityPlayer player, @Nonnull EnumHand hand, @Nonnull EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                return false;
            }

            player.openGui(TinkerIO.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TileEntity te = world.getTileEntity(data.getPos());
        if (te instanceof TileEntitySmartOutput) {
            TileEntitySmartOutput tile = (TileEntitySmartOutput) te;
            ItemStack itemStack = tile.getTargetItemStack();
            if (itemStack != null && !itemStack.isEmpty()) {
                probeInfo.horizontal().item(itemStack).text(itemStack.getDisplayName());
                probeInfo.vertical().progress(tile.getProgress(100) % 100, 100, probeInfo.defaultProgressStyle().suffix("%"));
            }
        }

    }
}
