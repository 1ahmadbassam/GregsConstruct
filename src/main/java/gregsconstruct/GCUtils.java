package gregsconstruct;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.shared.TinkerFluids;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GCUtils {
    public static final FluidStack[] sawLubricants = {
            Materials.Lubricant.getFluid(1),
            Materials.DistilledWater.getFluid(3),
            Materials.Water.getFluid(4)
    };

    private static final List<Tuple<FluidStack, FluidStack>> lateGeneratedFluids = new ArrayList<Tuple<FluidStack, FluidStack>>() {{
        add(new Tuple<>(new FluidStack(TinkerFluids.copper, 3), new FluidStack(TinkerFluids.tin, 1)));
        add(new Tuple<>(new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.silver, 1)));
        add(new Tuple<>(new FluidStack(TinkerFluids.ardite, 2), new FluidStack(TinkerFluids.cobalt, 2)));
    }};

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


    public static String upperCase(Material mat) {
        return mat.toCamelCaseString().substring(0, 1).toUpperCase() + mat.toCamelCaseString().substring(1);
    }


    protected static boolean isLateGeneratedFluid(FluidStack f1, FluidStack f2) {
        for (Tuple<FluidStack, FluidStack> r : lateGeneratedFluids)
            if ((r.getFirst().isFluidStackIdentical(f1) && r.getSecond().isFluidStackIdentical(f2))
                    || (r.getFirst().isFluidStackIdentical(f2) && r.getSecond().isFluidStackIdentical(f1)))
                return true;
        return false;
    }
}
