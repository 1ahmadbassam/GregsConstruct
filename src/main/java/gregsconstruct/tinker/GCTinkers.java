package gregsconstruct.tinker;

import gregicadditions.GAMaterials;
import gregsconstruct.GCConfig;
import gregsconstruct.GCUtils;
import gregsconstruct.common.GCMaterials;
import gregsconstruct.common.GCMetaItems;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.smeltery.AlloyRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.shared.block.BlockSlime;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.M;

public class GCTinkers {
    private static final ArrayList<slimeknights.tconstruct.library.materials.Material> ingotMaterials = new ArrayList<>();
    private static final ArrayList<IngotMaterial> GTIngotMaterials = new ArrayList<>();
    private static final ArrayList<slimeknights.tconstruct.library.materials.Material> gemMaterials = new ArrayList<>();
    private static final ArrayList<GemMaterial> GTGemMaterials = new ArrayList<>();

    public static void preInit() {
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat != Materials.NaquadahEnriched) {
                if (mat instanceof IngotMaterial) {
                    if (mat != Materials.Iron
                            && mat != Materials.Gold
                            && mat != Materials.Cobalt
                            && mat != Materials.Copper
                            && mat != Materials.Tin
                            && mat != Materials.Bronze
                            && mat != Materials.Zinc
                            && mat != Materials.Lead
                            && mat != Materials.Nickel
                            && mat != Materials.Silver
                            && mat != Materials.Electrum
                            && mat != Materials.Steel
                            && mat != GCMaterials.Ardite
                            && mat != GCMaterials.Manyullyn) {
                        if (((SolidMaterial) mat).toolDurability > 0) {
                            ingotMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(true).setCraftable(false));
                            GTIngotMaterials.add((IngotMaterial) mat);
                        } else
                            TinkerRegistry.integrate(((IngotMaterial) mat).getMaterialFluid(), GCUtils.upperCase(mat));
                    }
                    if (((IngotMaterial) mat).blastFurnaceTemperature <= 0 && ((IngotMaterial) mat).getMaterialFluid() != null) {
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    }
                }
                if (mat instanceof GemMaterial && ((GemMaterial) mat).toolDurability > 0) {
                    gemMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(false).setCraftable(true));
                    GTGemMaterials.add((GemMaterial) mat);
                }
                if (mat instanceof DustMaterial && !(mat instanceof IngotMaterial)) {
                    DustMaterial dust = (DustMaterial) mat;
                    if (dust.directSmelting != null && dust.directSmelting.getMaterialFluid() != null) {
                        int oreAmount = (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio);
                        if (oreAmount <= 0) oreAmount = (int) (144 * Config.oreToIngotRatio);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), dust.directSmelting.getMaterialFluid(), oreAmount);
                    } else if (dust.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID) && dust.getMaterialFluid() != null && mat != Materials.Glass && mat != Materials.Ice && mat != Materials.Stone) {
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, mat).toString(), dust.getMaterialFluid(), 144);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustSmall, mat).toString(), dust.getMaterialFluid(), 36);
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustTiny, mat).toString(), dust.getMaterialFluid(), 16);
                    }
                    if (dust instanceof GemMaterial && dust.getMaterialFluid() != null) {
                        TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.gem, mat).toString(), dust.getMaterialFluid(), 144);
                    }
                }
            }
        }

        for (int i = 0; i < ingotMaterials.size(); i++) {
            slimeknights.tconstruct.library.materials.Material mat = ingotMaterials.get(i);
            IngotMaterial GtMat = GTIngotMaterials.get(i);
            mat.addCommonItems(GCUtils.upperCase(GtMat));
            mat.setFluid(GtMat.getMaterialFluid());
            mat.addItemIngot(new UnificationEntry(OrePrefix.ingot, GtMat).toString());
            mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.ingot, GtMat));
            TinkerRegistry.addMaterial(mat);
            TinkerRegistry.addMaterialStats(mat,
                    new HeadMaterialStats((int) (GtMat.toolDurability * 0.8), GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel),
                    new HandleMaterialStats((GtMat.harvestLevel - 0.5f) / 2, GtMat.toolDurability / 3),
                    new ExtraMaterialStats(GtMat.toolDurability / 4));
            TinkerRegistry.integrate(mat, mat.getFluid(), GCUtils.upperCase(GtMat)).toolforge();

        }

        for (int i = 0; i < gemMaterials.size(); i++) {
            slimeknights.tconstruct.library.materials.Material mat = gemMaterials.get(i);
            GemMaterial GtMat = GTGemMaterials.get(i);
            mat.addCommonItems(GCUtils.upperCase(GtMat));
            mat.addItemIngot(new UnificationEntry(OrePrefix.gem, GtMat).toString());
            mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.gem, GtMat));
            TinkerRegistry.addMaterial(mat);
            TinkerRegistry.addMaterialStats(mat,
                    new HeadMaterialStats(GtMat.toolDurability, GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel),
                    new HandleMaterialStats(GtMat.harvestLevel - 0.5f, GtMat.toolDurability / 4),
                    new ExtraMaterialStats(GtMat.toolDurability / 100));
            TinkerRegistry.integrate(mat, GCUtils.upperCase(GtMat));
        }

        TinkerRegistry.registerAlloy(Materials.Brass.getFluid(4),
                Materials.Copper.getFluid(3),
                Materials.Zinc.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.Cupronickel.getFluid(2),
                Materials.Copper.getFluid(1),
                Materials.Nickel.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.RedAlloy.getFluid(1),
                Materials.Copper.getFluid(1),
                Materials.Redstone.getFluid(4));

        TinkerRegistry.registerAlloy(Materials.TinAlloy.getFluid(2),
                Materials.Iron.getFluid(1),
                Materials.Tin.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.Invar.getFluid(3),
                Materials.Iron.getFluid(2),
                Materials.Nickel.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.BatteryAlloy.getFluid(5),
                Materials.Lead.getFluid(4),
                Materials.Antimony.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.SolderingAlloy.getFluid(10),
                Materials.Tin.getFluid(9),
                Materials.Antimony.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.Magnalium.getFluid(3),
                Materials.Aluminium.getFluid(2),
                Materials.Magnesium.getFluid(1));

        TinkerRegistry.registerAlloy(Materials.CobaltBrass.getFluid(9),
                Materials.Brass.getFluid(7),
                Materials.Aluminium.getFluid(1),
                Materials.Cobalt.getFluid(1));
    }

    public static void init() {
        // Alloy smelting -> Tinker Alloying
        Collection<Recipe> alloySmeltingRecipes = new ArrayList<>(RecipeMaps.ALLOY_SMELTER_RECIPES.getRecipeList());
        for (Recipe recipe : alloySmeltingRecipes) {
            if (recipe.getInputs().size() != 2 || recipe.getOutputs().size() != 1) continue;
            CountableIngredient input1 = recipe.getInputs().get(0);
            CountableIngredient input2 = recipe.getInputs().get(1);
            ItemStack output = recipe.getOutputs().get(0);
            if (input1.getIngredient().getMatchingStacks().length > 0
                    && OreDictUnifier.getMaterial(input1.getIngredient().getMatchingStacks()[0]) != null
                    && input2.getIngredient().getMatchingStacks().length > 0
                    && OreDictUnifier.getMaterial(input2.getIngredient().getMatchingStacks()[0]) != null
                    && OreDictUnifier.getMaterial(output) != null) {
                MaterialStack input1Material = OreDictUnifier.getMaterial(input1.getIngredient().getMatchingStacks()[0]).copy(M * input1.getCount());
                MaterialStack input2Material = OreDictUnifier.getMaterial(input2.getIngredient().getMatchingStacks()[0]).copy(M * input2.getCount());
                MaterialStack outputMaterial = OreDictUnifier.getMaterial(output).copy(M * output.getCount());
                if (input1Material.material instanceof FluidMaterial && input2Material.material instanceof FluidMaterial && outputMaterial.material instanceof FluidMaterial
                        && ((FluidMaterial) input1Material.material).getMaterialFluid() != null
                        && ((FluidMaterial) input2Material.material).getMaterialFluid() != null
                        && ((FluidMaterial) outputMaterial.material).getMaterialFluid() != null
                        && (input1Material.material != input2Material.material)
                        && (input1Material.material != outputMaterial.material)
                        && (input2Material.material != outputMaterial.material)
                        && input1Material.material != Materials.NaquadahEnriched
                        && input2Material.material != Materials.NaquadahEnriched
                        && outputMaterial.material != Materials.NaquadahEnriched) {
                    FluidStack input1Fluid = ((FluidMaterial) input1Material.material).getFluid((int) (input1Material.amount / M));
                    FluidStack input2Fluid = ((FluidMaterial) input2Material.material).getFluid((int) (input2Material.amount / M));
                    if (getAlloyRecipe(input1Fluid, input2Fluid) == null
                            && !GCUtils.isLateGeneratedFluid(input1Fluid, input2Fluid)) {
                        TinkerRegistry.registerAlloy(
                                ((FluidMaterial) outputMaterial.material).getFluid((int) (outputMaterial.amount / M)),
                                input1Fluid,
                                input2Fluid);
                    }
                }
            }
        }
    }

    public static void postInit() {
        Map<OrePrefix, ItemStack> castMap = new HashMap<>();
        // TinkerCast -> OrePrefix
        castMap.put(OrePrefix.ingot, TinkerSmeltery.castIngot);
        castMap.put(OrePrefix.nugget, TinkerSmeltery.castNugget);
        castMap.put(OrePrefix.gem, TinkerSmeltery.castGem);
        if (!GCConfig.Special.simpleCasting) {
            castMap.put(OrePrefix.plate, TinkerSmeltery.castPlate);
            castMap.put(OrePrefix.gear, TinkerSmeltery.castGear);
        }
        // At post init, everything must've registered
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat != Materials.Glass
                    && mat != Materials.NaquadahEnriched
                    && mat != GCMaterials.Dirt
                    && mat != Materials.Obsidian
                    && mat != GCMaterials.Slime
                    && mat instanceof FluidMaterial && ((FluidMaterial) mat).getMaterialFluid() != null) {
                for (OrePrefix prefix : OrePrefix.values()) {
                    if (!OreDictUnifier.get(prefix, mat).isEmpty()
                            && !(mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0 && GCUtils.blastPrefix.contains(prefix))
                            && prefix.getMaterialAmount(mat) >= M / 9) {
                        registerTinkerMelting(new UnificationEntry(prefix, mat).toString(), ((FluidMaterial) mat).getMaterialFluid(), (int) (prefix.materialAmount * L / M));
                        if (castMap.containsKey(prefix))
                            registerTinkerCasting(OreDictUnifier.get(prefix, mat), castMap.get(prefix), ((FluidMaterial) mat).getMaterialFluid(), (int) (prefix.materialAmount * L / M));
                    }
                }
            }
        }
        if (Materials.Glass.getMaterialFluid() != null) {
            TinkerRegistry.registerMelting(TinkerCommons.blockClearGlass, Materials.Glass.getMaterialFluid(), 1000);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 1000);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.gem, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 1000);
            TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, Materials.Glass.getMaterialFluid(), 1000);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.gemChipped, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 250);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.gemFlawed, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 500);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.gemFlawless, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 2000);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.gemExquisite, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 4000);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.plate, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 1000);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.lens, Materials.Glass).toString(), Materials.Glass.getMaterialFluid(), 750);
            TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, Materials.Quartzite).toString(), Materials.Glass.getMaterialFluid(), 1000);
        }
        TinkerRegistry.registerMelting(Items.SNOWBALL, FluidRegistry.WATER, 250);
        if (Materials.Glowstone.getMaterialFluid() != null)
            TinkerRegistry.registerMelting(Blocks.GLOWSTONE, Materials.Glowstone.getMaterialFluid(), 576);
        Map<Fluid, Integer> plasmaTemps = new HashMap<>();
        plasmaTemps.put(Materials.Iron.getMaterialPlasma(), 7300);
        plasmaTemps.put(Materials.Nickel.getMaterialPlasma(), 9800);
        for (Map.Entry<Fluid, Integer> e : plasmaTemps.entrySet()) {
            e.getKey().setTemperature(e.getValue());
        }
        TinkerRegistry.registerSmelteryFuel(Materials.Steam.getFluid(64), 2);
        TinkerRegistry.registerSmelteryFuel(Materials.SeedOil.getFluid(64), 10);
        if (Loader.isModLoaded("gtadditions")) {
            GAMaterials.FishOil.setFluidTemperature(1974);
            TinkerRegistry.registerSmelteryFuel(GAMaterials.FishOil.getFluid(64), 10);
        }
        TinkerRegistry.registerSmelteryFuel(Materials.Creosote.getFluid(16), 2);
        TinkerRegistry.registerSmelteryFuel(Materials.Biomass.getFluid(16), 2);
        TinkerRegistry.registerSmelteryFuel(Materials.NaquadahEnriched.getFluid(1), 750);
        for (FuelRecipe r : RecipeMaps.DIESEL_GENERATOR_FUELS.getRecipeList()) {
            TinkerRegistry.registerSmelteryFuel(r.getRecipeFluid(), r.getDuration());
        }
        for (FuelRecipe r : RecipeMaps.PLASMA_GENERATOR_FUELS.getRecipeList()) {
            if (r.getRecipeFluid().isFluidEqual(Materials.Oxygen.getPlasma(1))
                    || r.getRecipeFluid().isFluidEqual(Materials.Nitrogen.getPlasma(1))
                    || r.getRecipeFluid().isFluidEqual(Materials.Helium.getPlasma(1))) continue;
            TinkerRegistry.registerSmelteryFuel(r.getRecipeFluid(), r.getDuration());
        }
        TinkerRegistry.registerMelting("obsidian", TinkerFluids.obsidian, 144);
        TinkerRegistry.registerBasinCasting(new ItemStack(Blocks.OBSIDIAN), ItemStack.EMPTY, TinkerFluids.obsidian, 144);
        TinkerRegistry.registerMelting("plateBrickSeared", TinkerFluids.searedStone, 72);
        if (!GCConfig.Special.simpleCasting)
            TinkerRegistry.registerTableCasting(GCMetaItems.SEARED_BRICK_PLATE.getStackForm(), TinkerSmeltery.castPlate, TinkerFluids.searedStone, 72);

        if (GCMaterials.Slime.getMaterialFluid() != null) {
            TinkerRegistry.registerMelting(Items.SLIME_BALL, GCMaterials.Slime.getMaterialFluid(), 250);
            TinkerRegistry.registerMelting(Blocks.SLIME_BLOCK, GCMaterials.Slime.getMaterialFluid(), 2250);
            TinkerRegistry.registerMelting(new ItemStack(TinkerCommons.blockSlime), GCMaterials.Slime.getMaterialFluid(), 2250);
            TinkerRegistry.registerMelting(new ItemStack(TinkerCommons.blockSlimeCongealed), GCMaterials.Slime.getMaterialFluid(), 1000);
        }

        TinkerRegistry.registerMelting(TinkerCommons.matSlimeBallBlue, TinkerFluids.blueslime, 250);
        TinkerRegistry.registerMelting(new ItemStack(TinkerCommons.blockSlime, 1, BlockSlime.SlimeType.BLUE.meta), GCMaterials.Slime.getMaterialFluid(), 2250);
        TinkerRegistry.registerMelting(new ItemStack(TinkerCommons.blockSlime, 1, BlockSlime.SlimeType.BLUE.meta), GCMaterials.Slime.getMaterialFluid(), 1000);
    }

    private static void registerTinkerMelting(String oreDict, Fluid fluid, int amount) {
        if (TinkerRegistry.getMelting(OreDictionary.getOres(oreDict).get(0)) != null) {
            return;
        }
        TinkerRegistry.registerMelting(new MeltingRecipe(new RecipeMatch.Oredict(oreDict, 1, amount), fluid));
    }

    private static void registerTinkerCasting(ItemStack output, ItemStack cast, Fluid fluid, int amount) {
        if (TinkerRegistry.getTableCasting(cast, fluid) != null) {
            return;
        }
        TinkerRegistry.registerTableCasting(output, cast, fluid, amount);
    }


    public static void unifyAluminium() {
        for (OrePrefix p : OrePrefix.values()) {
            if (!OreDictUnifier.get(p, Materials.Aluminium).isEmpty()) {
                OreDictUnifier.registerOre(OreDictUnifier.get(p, Materials.Aluminium), p + "Aluminum");
            }
            if (!OreDictUnifier.get(p, GCMaterials.AluminiumBrass).isEmpty()) {
                OreDictUnifier.registerOre(OreDictUnifier.get(p, GCMaterials.AluminiumBrass), p + "AluminumBrass");
            }
        }
    }


    @Nullable
    private static AlloyRecipe getAlloyRecipe(@Nonnull FluidStack f1, @Nonnull FluidStack f2) {
        for (AlloyRecipe recipe : TinkerRegistry.getAlloys()) {
            if (recipe != null && recipe.isValid() && recipe.getFluids().size() == 2) {
                FluidStack recipe1Fluid = recipe.getFluids().get(0);
                FluidStack recipe2Fluid = recipe.getFluids().get(1);
                if ((recipe1Fluid.isFluidStackIdentical(f1) && recipe2Fluid.isFluidStackIdentical(f2))
                        || (recipe1Fluid.isFluidStackIdentical(f2) && recipe2Fluid.isFluidStackIdentical(f1)))
                    return recipe;
            }
        }
        return null;
    }
}
