package gregsconstruct;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import static com.google.common.collect.ImmutableList.of;
import static gregtech.api.unification.material.MaterialIconSet.METALLIC;

@IMaterialHandler.RegisterMaterialHandler
public class GCMaterials implements IMaterialHandler {
    public static IngotMaterial AluminiumBrass;

    public static void init() {
        ModHandler.addShapelessRecipe("aluminium_brass", OreDictUnifier.get(OrePrefix.dust, AluminiumBrass, 4), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("aluminium_brass_small", OreDictUnifier.get(OrePrefix.dust, AluminiumBrass), new UnificationEntry(OrePrefix.dustSmall, Materials.Aluminium), new UnificationEntry(OrePrefix.dustSmall, Materials.Aluminium), new UnificationEntry(OrePrefix.dustSmall, Materials.Aluminium), new UnificationEntry(OrePrefix.dustSmall, Materials.Copper));
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(450).EUt(8).input(OrePrefix.dust, Materials.Aluminium, 3).input(OrePrefix.dust, Materials.Copper).output(OrePrefix.dust, AluminiumBrass, 4).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(100).EUt(8).input(OrePrefix.dustSmall, Materials.Aluminium, 3).input(OrePrefix.dustSmall, Materials.Copper).output(OrePrefix.dust, AluminiumBrass).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(16).input(OrePrefix.ingot, Materials.Aluminium, 3).input(OrePrefix.ingot, Materials.Copper).output(OrePrefix.ingot, AluminiumBrass, 4).buildAndRegister();
        if (AluminiumBrass.getMaterialFluid() != null)
            TinkerSmeltery.castCreationFluids.add(AluminiumBrass.getFluid(GTValues.L));
    }

    @Override
    public void onMaterialsInit() {
        long STD_METAL = DustMaterial.MatFlags.GENERATE_PLATE;
        long EXT_METAL = STD_METAL | gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_ROD |
                gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_BOLT_SCREW;
        long EXT2_METAL = EXT_METAL | gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_GEAR |
                gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_FOIL |
                gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_FINE_WIRE;
        AluminiumBrass = new IngotMaterial(500, "aluminium_brass", 0xf1e4c8, METALLIC, 2, of(new MaterialStack(Materials.Aluminium, 3), new MaterialStack(Materials.Copper, 1)), EXT2_METAL, null, 9.0F, 2.0f, 256);
        AluminiumBrass.setFluidTemperature(500);
    }
}
