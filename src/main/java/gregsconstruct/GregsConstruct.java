package gregsconstruct;

import gregsconstruct.common.GCMaterials;
import gregsconstruct.common.GCMetaItems;
import gregsconstruct.common.GCRecipes;
import gregsconstruct.tinker.GCMolds;
import gregsconstruct.tinker.GCTinkers;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import knightminer.tcomplement.library.events.TCompRegisterEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

@Mod(modid = GregsConstruct.MODID,
        name = GregsConstruct.NAME,
        version = GregsConstruct.VERSION,
        dependencies = "required-after:gregtech;required-after:tconstruct;after:gtadditions;after:tcomplement")
public class GregsConstruct {
    public static final String MODID = "gtconstruct";
    public static final String NAME = "Greg's Construct";
    public static final String VERSION = "@VERSION@";

    public static Logger logger;

    public GregsConstruct() {
        if (Loader.isModLoaded("tcomplement"))
            MinecraftForge.EVENT_BUS.register(new TinkerComplementEventBus());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();
        GCMetaItems.init();

        GCTinkers.unifyAluminium();
        GCTinkers.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GCMolds.recipes();
        GCRecipes.oreDictInit();
        GCTinkers.unifyAluminium();
        GCRecipes.furnaceRecipes();
        GCRecipes.initEnhancedIntegration();
        if (Loader.isModLoaded("tcomplement"))
            GCRecipes.initTComplementIntegration();
        // Recipe generation is done at init, to ensure that alloy recipes are
        // registered if needed before they are removed from the alloy smelter
        GCTinkers.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        GCTinkers.postInit();
        if (Loader.isModLoaded("tcomplement"))
            GCTinkers.postInitTComplement();
    }

    @Mod.EventBusSubscriber
    public static class events {
        @SubscribeEvent
        public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
            GCMetaItems.registerOreDict();
            GCMaterials.recipes();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
            GCMolds.initTinker();
            GCRecipes.glassRecipes();
            GCMaterials.recipesLate();
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void tableCastingRemoval(TinkerRegisterEvent.TableCastingRegisterEvent event) {
            if (GCConfig.Special.simpleCasting) {
                if (event.getRecipe() instanceof CastingRecipe
                        && (event.getRecipe().matches(TinkerSmeltery.castPlate, ((CastingRecipe) event.getRecipe()).getFluid().getFluid())
                        || event.getRecipe().matches(TinkerSmeltery.castGear, ((CastingRecipe) event.getRecipe()).getFluid().getFluid())
                        || ItemStack.areItemStacksEqual(((CastingRecipe) event.getRecipe()).getResult(), TinkerSmeltery.castPlate)
                        || ItemStack.areItemStacksEqual(((CastingRecipe) event.getRecipe()).getResult(), TinkerSmeltery.castGear)))
                    event.setCanceled(true);
            }
            if (event.getRecipe() instanceof CastingRecipe) {
                if (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.aluminum, 1))
                        || (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.alubrass, 1))
                        || (TinkerFluids.emerald != null && (((CastingRecipe) event.getRecipe()).getFluid().isFluidStackIdentical(new FluidStack(TinkerFluids.emerald, 666))))))
                    event.setCanceled(true);
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void basinCastingRemoval(TinkerRegisterEvent.BasinCastingRegisterEvent event) {
            if (event.getRecipe() instanceof CastingRecipe)
                if (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.aluminum, 1))
                        || (((CastingRecipe) event.getRecipe()).getFluid().isFluidEqual(new FluidStack(TinkerFluids.alubrass, 1)))
                        || (((CastingRecipe) event.getRecipe()).getFluid().isFluidStackIdentical(new FluidStack(TinkerFluids.obsidian, 288))
                        || (TinkerFluids.emerald != null && (((CastingRecipe) event.getRecipe()).getFluid().isFluidStackIdentical(new FluidStack(TinkerFluids.emerald, 5994))))))
                    event.setCanceled(true);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void smeltingRemoval(TinkerRegisterEvent.MeltingRegisterEvent event) {
            smeltingRemovalGlobal(event);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void alloyRemoval(TinkerRegisterEvent.AlloyRegisterEvent event) {
            if (event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.brass, 3))
                    || event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.alubrass, 4)))
                event.setCanceled(true);
        }

        protected static void smeltingRemovalGlobal(TinkerRegisterEvent<? extends MeltingRecipe> event) {
            if (event.getRecipe().getResult().isFluidEqual(new FluidStack(TinkerFluids.aluminum, 1)))
                event.setCanceled(true);
            if (event.getRecipe().getResult().isFluidEqual(new FluidStack(TinkerFluids.alubrass, 1)))
                event.setCanceled(true);
            if (event.getRecipe().matches(new ItemStack(Items.SNOWBALL)) && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(FluidRegistry.WATER, 125)))
                event.setCanceled(true);
            if (event.getRecipe().matches(OreDictUnifier.get(OrePrefix.gem, Materials.Glass)) && Materials.Glass.getMaterialFluid() != null && event.getRecipe().getResult().isFluidStackIdentical(Materials.Glass.getFluid(144)))
                event.setCanceled(true);
            if (event.getRecipe().matches(OreDictUnifier.get(OrePrefix.plate, Materials.Stone)) && event.getRecipe().getResult().isFluidEqual(new FluidStack(TinkerFluids.searedStone, 144)))
                event.setCanceled(true);
            if (event.getRecipe().matches(OreDictUnifier.get(OrePrefix.gem, Materials.Emerald)) && TinkerFluids.emerald != null && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.emerald, 666)))
                event.setCanceled(true);
            if (event.getRecipe().matches(OreDictUnifier.get(OrePrefix.block, Materials.Emerald)) && TinkerFluids.emerald != null && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.emerald, 5994)))
                event.setCanceled(true);
            if ((event.getRecipe().matches(new ItemStack(Blocks.STONE)) || event.getRecipe().matches(new ItemStack(Blocks.COBBLESTONE))) && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.searedStone, 72)))
                event.setCanceled(true);
            for (ItemStack sand : OreDictionary.getOres("sand"))
                if (event.getRecipe().matches(sand) && event.getRecipe().getResult().getFluid() == TinkerFluids.glass)
                    event.setCanceled(true);
            for (ItemStack obs : OreDictionary.getOres("obsidian"))
                if (event.getRecipe().matches(obs) && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.obsidian, 288)))
                    event.setCanceled(true);
            for (ItemStack ore : OreDictionary.getOres("oreEmerald"))
                if (event.getRecipe().matches(ore) && TinkerFluids.emerald != null && event.getRecipe().getResult().isFluidStackIdentical(new FluidStack(TinkerFluids.emerald, (int) (666 * Config.oreToIngotRatio))))
                    event.setCanceled(true);
            for (Material mat : Material.MATERIAL_REGISTRY)
                if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0 && (matches(event, OrePrefix.ore, mat) || matches(event, OrePrefix.dust, mat) || matches(event, OrePrefix.dustSmall, mat) || matches(event, OrePrefix.dustTiny, mat)))
                    event.setCanceled(true);
        }

        private static boolean matches(TinkerRegisterEvent<? extends MeltingRecipe> e, OrePrefix prefix, Material mat) {
            return e.getRecipe().input.matches(NonNullList.withSize(1, OreDictUnifier.get(prefix, mat))).isPresent();
        }
    }

    public static class TinkerComplementEventBus {
        @SubscribeEvent(priority = EventPriority.HIGH)
        public void highOvenMixingRemoval(TCompRegisterEvent.HighOvenMixRegisterEvent event) {
            event.setCanceled(true);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public void meltingRemoval(TCompRegisterEvent.MelterOverrideRegisterEvent event) {
            events.smeltingRemovalGlobal(event);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public void highOvenMeltingRemoval(TCompRegisterEvent.HighOvenOverrideRegisterEvent event) {
            events.smeltingRemovalGlobal(event);
        }
    }
}
