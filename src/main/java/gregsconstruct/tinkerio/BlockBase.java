package gregsconstruct.tinkerio;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import tinker_io.TinkerIO;

import java.util.Objects;

public class BlockBase extends Block {
    protected String name;

    public BlockBase(Material material, String name, String registryName) {
        super(material);
        this.name = name;
        this.setTranslationKey(name);
        this.setRegistryName(registryName);
        this.setCreativeTab(TinkerIO.creativeTabs);
    }

    public void registerItemModel(Item itemBlock) {
        TinkerIO.proxy.registerItemRenderer(itemBlock, 0, this.name);
    }

    public Item createItemBlock() {
        return (new ItemBlock(this)).setRegistryName(Objects.requireNonNull(this.getRegistryName()));
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector((float)(entity.posX - (double)clickedBlock.getX()), (float)(entity.posY - (double)clickedBlock.getY()), (float)(entity.posZ - (double)clickedBlock.getZ()));
    }
}
