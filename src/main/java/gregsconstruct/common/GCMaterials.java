package gregsconstruct.common;

import com.google.common.collect.ImmutableList;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Loader;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.Collections;

@IMaterialHandler.RegisterMaterialHandler
public class GCMaterials implements IMaterialHandler {
    public static IngotMaterial AluminiumBrass;
    public static DustMaterial Blood;
    public static FluidMaterial Dirt;
    public static FluidMaterial Slime;
    public static IngotMaterial Ardite;
    public static IngotMaterial Manyullyn;

    public static IngotMaterial Kovar;
    public static IngotMaterial Lavium;
    public static IngotMaterial Qivium;

    public static void recipes() {
        ModHandler.addShapelessRecipe("aluminium_brass", OreDictUnifier.get(OrePrefix.dust, AluminiumBrass, 4), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("aluminium_brass_small", OreDictUnifier.get(OrePrefix.dust, AluminiumBrass), new UnificationEntry(OrePrefix.dustSmall, Materials.Aluminium), new UnificationEntry(OrePrefix.dustSmall, Materials.Aluminium), new UnificationEntry(OrePrefix.dustSmall, Materials.Aluminium), new UnificationEntry(OrePrefix.dustSmall, Materials.Copper));
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(450).EUt(8).input(OrePrefix.dust, Materials.Aluminium, 3).input(OrePrefix.dust, Materials.Copper).output(OrePrefix.dust, AluminiumBrass, 4).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(100).EUt(8).input(OrePrefix.dustSmall, Materials.Aluminium, 3).input(OrePrefix.dustSmall, Materials.Copper).output(OrePrefix.dust, AluminiumBrass).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(16).input(OrePrefix.ingot, Materials.Aluminium, 3).input(OrePrefix.ingot, Materials.Copper).output(OrePrefix.ingot, AluminiumBrass, 4).buildAndRegister();
        if (AluminiumBrass.getMaterialFluid() != null)
            TinkerSmeltery.castCreationFluids.add(AluminiumBrass.getFluid(GTValues.L));
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(20).EUt(32).input(Items.ROTTEN_FLESH).fluidOutputs(Blood.getFluid(40)).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(16).input(OrePrefix.ingot, Materials.Cobalt, 2).input(OrePrefix.ingot, Ardite, 2).output(OrePrefix.ingot, Manyullyn, 2).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input("dirt", 1).fluidOutputs(GCMaterials.Dirt.getFluid(144)).buildAndRegister();
    }

    public static void recipesLate() {
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.removeRecipe(RecipeMaps.FLUID_SOLIDFICATION_RECIPES.findRecipe(Integer.MAX_VALUE, Collections.singletonList(MetaItems.SHAPE_MOLD_BLOCK.getStackForm()), Collections.singletonList(Materials.Obsidian.getFluid(144)), Integer.MAX_VALUE));
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(22).EUt(8).fluidInputs(Materials.Obsidian.getFluid(144)).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).output(Blocks.OBSIDIAN).buildAndRegister();
    }

    @Override
    public void onMaterialsInit() {
        long STD_METAL = DustMaterial.MatFlags.GENERATE_PLATE;
        long EXT_METAL = STD_METAL | gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_ROD |
                gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_BOLT_SCREW;
        long EXT2_METAL = EXT_METAL | gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_GEAR |
                gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_FOIL |
                gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_FINE_WIRE;
        AluminiumBrass = new IngotMaterial(500, "aluminium_brass", 0xf1e4c8, MaterialIconSet.METALLIC, 2, ImmutableList.of(new MaterialStack(Materials.Aluminium, 3), new MaterialStack(Materials.Copper, 1)), EXT2_METAL, null, 9.0F, 2.0f, 256);
        Blood = new DustMaterial(501, "blood", 0x431313, MaterialIconSet.METALLIC, 1, ImmutableList.of(), DustMaterial.MatFlags.SMELT_INTO_FLUID, null);
        Ardite = new IngotMaterial(502, "ardite", 0xf38900, MaterialIconSet.METALLIC, 2, ImmutableList.of(), DustMaterial.MatFlags.GENERATE_ORE | EXT2_METAL, null, 3.5f, 3.6f, 1238, 1315);
        Manyullyn = new IngotMaterial(503, "manyullyn", 0xa97de0, MaterialIconSet.DULL, 2, ImmutableList.of(new MaterialStack(Ardite, 2), new MaterialStack(Materials.Cobalt, 2)), EXT2_METAL, null, 7.02f, 8.72f, 1025);
        Dirt = new FluidMaterial(504, "dirt", 0x735137, MaterialIconSet.ROUGH, ImmutableList.of(), DustMaterial.MatFlags.SMELT_INTO_FLUID);
        Slime = new FluidMaterial(505, "greenslime", 0x82c873, MaterialIconSet.SHINY, ImmutableList.of(), DustMaterial.MatFlags.SMELT_INTO_FLUID | DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES);
        AluminiumBrass.setFluidTemperature(500);
        Slime.setFluidTemperature(500);
        Materials.Emerald.addFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID);
        Materials.Obsidian.addFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID);
        Materials.Clay.addFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID);
        Materials.Stone.addFlag(SolidMaterial.MatFlags.GENERATE_ROD);
        Materials.Iron.addFlag(IngotMaterial.MatFlags.GENERATE_ROTOR);

        if (Loader.isModLoaded("tinkers_reforged")) {
            Kovar = new IngotMaterial(506, "kovar", 0xab86cc, MaterialIconSet.METALLIC, 1, ImmutableList.of(), EXT2_METAL | DustMaterial.MatFlags.GENERATE_ORE, null);
            Lavium = new IngotMaterial(507, "lavium", 0x8acc49, MaterialIconSet.METALLIC, 2, ImmutableList.of(new MaterialStack(Materials.Glass, 2), new MaterialStack(Materials.Cobalt, 4), new MaterialStack(GCMaterials.Slime, 1)), EXT2_METAL, null, 12.2f, 12.2f, 1250);
            Qivium = new IngotMaterial(508, "qivium", 0xbf7c98, MaterialIconSet.METALLIC, 2, ImmutableList.of(new MaterialStack(Materials.Glass, 2), new MaterialStack(GCMaterials.Ardite, 4), new MaterialStack(GCMaterials.Slime, 1)), EXT2_METAL, null, 12.2f, 12.2f, 1250);
        }
    }
}
