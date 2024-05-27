package gregsconstruct.tinkerio;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import tinker_io.TinkerIO;

public class GCBlocks {
    private static final String porcelainName = "smart_output_porcelain";
    private static final String scorchedName = "smart_output_scorched";
    public static final BlockSmartOutput smartOutputPorcelain = new BlockSmartOutput(porcelainName, TinkerIO.MOD_ID + ":" + porcelainName);
    public static final BlockSmartOutput smartOutputScorched = new BlockSmartOutput(scorchedName, TinkerIO.MOD_ID + ":" + scorchedName);

    private GCBlocks() {}

    public static void register(IForgeRegistry<Block> registry) {
        if (Loader.isModLoaded("tcomplement")) {
            if (Loader.isModLoaded("ceramics")) {
                registry.register(smartOutputPorcelain);
            }
            registry.register(smartOutputScorched);
        }
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        if (Loader.isModLoaded("tcomplement")) {
            if (Loader.isModLoaded("ceramics")) {
                registry.register(smartOutputPorcelain.createItemBlock());
            }
            registry.register(smartOutputScorched.createItemBlock());
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        if (Loader.isModLoaded("tcomplement")) {
            if (Loader.isModLoaded("ceramics"))
                smartOutputPorcelain.registerItemModel(ItemBlock.getItemFromBlock(smartOutputPorcelain));
            smartOutputScorched.registerItemModel(ItemBlock.getItemFromBlock(smartOutputScorched));
        }
    }
}
