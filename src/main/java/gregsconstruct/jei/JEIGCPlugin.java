package gregsconstruct.jei;

import gregsconstruct.tinkerio.GCBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIGCPlugin implements IModPlugin {
    public static final List<ItemStack> removedItems = new ArrayList<>();

    @Override
    public void register(@Nonnull IModRegistry registry) {
        for (ItemStack item : removedItems) {
            registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(item);
        }
        if (Loader.isModLoaded("tinker_io"))
            GCBlocks.loadJEI(registry);
    }
}
