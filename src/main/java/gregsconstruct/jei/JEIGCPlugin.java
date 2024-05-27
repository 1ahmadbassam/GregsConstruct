package gregsconstruct.jei;

import gregsconstruct.tinkerio.GCBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import tinker_io.plugins.jei.smartOutput.SmartOutputRecipeCategory;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIGCPlugin implements IModPlugin {
    public static final List<ItemStack> removedItems = new ArrayList<>();

    @Override
    public void register(IModRegistry registry) {
        for (ItemStack item : removedItems) {
            registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(item);
        }
        if (Loader.isModLoaded("tinker_io") && Loader.isModLoaded("tcomplement")) {
            if (Loader.isModLoaded("ceramics"))
                registry.addRecipeCatalyst(new ItemStack(GCBlocks.smartOutputPorcelain), SmartOutputRecipeCategory.CATEGORY);
            registry.addRecipeCatalyst(new ItemStack(GCBlocks.smartOutputScorched), SmartOutputRecipeCategory.CATEGORY);
        }
    }
}
