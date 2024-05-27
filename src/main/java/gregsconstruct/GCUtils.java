package gregsconstruct;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.shared.block.BlockClearStainedGlass;

import javax.annotation.Nullable;
import java.util.*;

public class GCUtils {
    public static final FluidStack[] sawLubricants = {
            Materials.Lubricant.getFluid(1),
            Materials.DistilledWater.getFluid(3),
            Materials.Water.getFluid(4)
    };

    public static final MaterialStack[] lapisLike = {
            new MaterialStack(Materials.Lapis, 1),
            new MaterialStack(Materials.Lazurite, 1),
            new MaterialStack(Materials.Sodalite, 1)
    };

    public static final Set<OrePrefix> blastPrefix = new HashSet<OrePrefix>() {{
        add(OrePrefix.dust);
        add(OrePrefix.dustSmall);
        add(OrePrefix.dustTiny);
        add(OrePrefix.dustImpure);
        add(OrePrefix.dustPure);
        add(OrePrefix.crushed);
        add(OrePrefix.crushedCentrifuged);
        add(OrePrefix.crushedPurified);
        add(OrePrefix.ingotHot);
    }};

    private static final List<Tuple<FluidStack, FluidStack>> lateGeneratedFluids = new ArrayList<Tuple<FluidStack, FluidStack>>() {{
        add(new Tuple<>(new FluidStack(TinkerFluids.copper, 3), new FluidStack(TinkerFluids.tin, 1)));
        add(new Tuple<>(new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.silver, 1)));
        add(new Tuple<>(new FluidStack(TinkerFluids.ardite, 2), new FluidStack(TinkerFluids.cobalt, 2)));
    }};

    public static String upperCase(Material mat) {
        return mat.toCamelCaseString().substring(0, 1).toUpperCase() + mat.toCamelCaseString().substring(1);
    }


    public static boolean isLateGeneratedFluid(FluidStack f1, FluidStack f2) {
        for (Tuple<FluidStack, FluidStack> r : lateGeneratedFluids)
            if ((r.getFirst().isFluidStackIdentical(f1) && r.getSecond().isFluidStackIdentical(f2))
                    || (r.getFirst().isFluidStackIdentical(f2) && r.getSecond().isFluidStackIdentical(f1)))
                return true;
        return false;
    }

    public static String getColorFromMeta(int i) {
        StringBuilder color = new StringBuilder(BlockClearStainedGlass.EnumGlassColor.values()[i].getName().toLowerCase());
        if (color.toString().contains("_")) {
            int idx = color.toString().indexOf('_');
            color.deleteCharAt(idx);
            color.setCharAt(idx, Character.toUpperCase(color.charAt(idx)));
        }
        if (color.length() > 0)
            color.setCharAt(0, Character.toUpperCase(color.charAt(0)));
        if (color.toString().equals("Silver")) {
            color.setLength(0);
            color.append("LightGray");
        }
        return color.toString();
    }

    public static ItemStack getNightVisionPotion() {
        ItemStack potion = new ItemStack(Items.POTIONITEM);
        PotionUtils.addPotionToItemStack(potion, PotionTypes.NIGHT_VISION);
        return potion;
    }

    public static ItemStack getInvisibilityPotion() {
        ItemStack potion = new ItemStack(Items.POTIONITEM);
        PotionUtils.addPotionToItemStack(potion, PotionTypes.INVISIBILITY);
        return potion;
    }
}
