package gregsconstruct;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;

@Mod(modid = GregsConstruct.MODID,
        name = GregsConstruct.NAME,
        version = GregsConstruct.VERSION,
        dependencies = "required-after:gregtech;required-after:tconstruct;after:gtadditions")
public class GregsConstruct {
    public static final String MODID = "gtconstruct";
    public static final String NAME = "Greg's Construct";
    public static final String VERSION = "@VERSION@";

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        GCTinkers.unifyAluminium();
        GCTinkers.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GCTinkers.oreDictInit();
        GCTinkers.unifyAluminium();
        GCMaterials.init();
        GCRecipes.furnaceRecipes();
        // Recipe generation is done at init, to ensure that alloy recipes are
        // registered if needed before they are removed from the alloy smelter
        GCTinkers.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        GCTinkers.postInit();
    }

    @Mod.EventBusSubscriber
    public static class events {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
            GCRecipes.glassRecipes();
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void tableCastingRemoval(TinkerRegisterEvent.TableCastingRegisterEvent event) {
            if (event.getRecipe() instanceof CastingRecipe)
                if (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.aluminum, 1))
                        || (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.alubrass, 1))))
                    event.setCanceled(true);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void basinCastingRemoval(TinkerRegisterEvent.BasinCastingRegisterEvent event) {
            if (event.getRecipe() instanceof CastingRecipe)
                if (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.aluminum, 1))
                        || (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.alubrass, 1))))
                    event.setCanceled(true);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void smeltingRemoval(TinkerRegisterEvent.MeltingRegisterEvent event) {
            if (event.getRecipe().getResult().isFluidEqual(new FluidStack(TinkerFluids.aluminum, 1)))
                event.setCanceled(true);
            if (event.getRecipe().getResult().isFluidEqual(new FluidStack(TinkerFluids.alubrass, 1)))
                event.setCanceled(true);
            if (event.getRecipe().matches(new ItemStack(Items.SNOWBALL)) && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(FluidRegistry.WATER, 125)))
                event.setCanceled(true);
            if (event.getRecipe().matches(OreDictUnifier.get(OrePrefix.gem, Materials.Glass)) && Materials.Glass.getMaterialFluid() != null && event.getRecipe().getResult().isFluidStackIdentical(Materials.Glass.getFluid(144)))
                event.setCanceled(true);
            for (ItemStack sand : OreDictionary.getOres("sand"))
                if (event.getRecipe().matches(sand) && event.getRecipe().getResult().getFluid() == TinkerFluids.glass)
                    event.setCanceled(true);
            for (Material mat : Material.MATERIAL_REGISTRY)
                if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0 && (matches(event, OrePrefix.ore, mat) || matches(event, OrePrefix.dust, mat) || matches(event, OrePrefix.dustSmall, mat) || matches(event, OrePrefix.dustTiny, mat)))
                    event.setCanceled(true);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void alloyRemoval(TinkerRegisterEvent.AlloyRegisterEvent event) {
            if (event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.brass, 3))
                    || event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.alubrass, 4)))
                event.setCanceled(true);
        }

        private static boolean matches(TinkerRegisterEvent.MeltingRegisterEvent e, OrePrefix prefix, Material mat) {
            return e.getRecipe().input.matches(NonNullList.withSize(1, OreDictUnifier.get(prefix, mat))).isPresent();
        }
    }
}
