package gregsconstruct.common;

import gregicadditions.GAConfig;
import gregicadditions.GAUtils;
import gregsconstruct.GCConfig;
import gregsconstruct.GCUtils;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.shared.block.BlockSlime;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockSeared;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.block.BlockSlimeDirt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.GTValues.W;

public class GCRecipes {
    public static final List<Tuple<String, ItemStack>> oreDictionaryRemovals = new ArrayList<>();

    public static void oreDictInit() {
        oreDictionaryRemovals.add(new Tuple<>("blockGlass", new ItemStack(TinkerCommons.blockClearGlass, 1, W)));
        oreDictionaryRemovals.add(new Tuple<>("blockGlass", new ItemStack(TinkerCommons.blockClearStainedGlass, 1, W)));
        if (!Loader.isModLoaded("gtadditions"))
            oreDictionaryRemovals.add(new Tuple<>("plankWood", OreDictUnifier.get(OrePrefix.plate, Materials.Wood)));
        for (int i = 0; i <= 15; i++)
            oreDictionaryRemovals.add(new Tuple<>("blockGlass" + GCUtils.getColorFromMeta(i), new ItemStack(TinkerCommons.blockClearStainedGlass, 1, i)));

        for (Tuple<String, ItemStack> entry : oreDictionaryRemovals) {
            for (ItemStack contained : OreDictionary.getOres(entry.getFirst())) {
                if (contained.getItem() == entry.getSecond().getItem() && contained.getMetadata() == entry.getSecond().getMetadata()) {
                    OreDictionary.getOres(entry.getFirst()).remove(contained);
                    break;
                }
            }
        }
        OreDictUnifier.registerOre(TinkerCommons.mudBrick, OrePrefix.ingot, GCMaterials.Dirt);
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.block, Materials.Obsidian), "obsidian");
        OreDictUnifier.registerOre(GCMetaItems.SEARED_BRICK_PLATE.getStackForm(), "plateBrickSeared");
        OreDictUnifier.registerOre(new ItemStack(TinkerCommons.blockClearGlass, 1, W), "blockGlassClear");
        OreDictUnifier.registerOre(new ItemStack(TinkerCommons.blockClearStainedGlass, 1, W), "blockGlassClear");
        OreDictUnifier.registerOre(new ItemStack(TinkerGadgets.stoneStick, 1, W), "stickStone");
    }

    public static void furnaceRecipes() {
        ModHandler.removeFurnaceSmelting(new ItemStack(Blocks.SAND, 1, GTValues.W));
        GameRegistry.addSmelting(new ItemStack(TinkerCommons.blockClearGlass), new ItemStack(Blocks.GLASS), 0.1f);
        ModHandler.removeFurnaceSmelting(TinkerCommons.graveyardSoil);
        ModHandler.removeFurnaceSmelting(TinkerCommons.slimyMudBlue);
        ModHandler.removeFurnaceSmelting(TinkerCommons.slimyMudGreen);
        ModHandler.removeFurnaceSmelting(TinkerCommons.slimyMudMagma);
        ModHandler.removeFurnaceSmelting(new ItemStack(TinkerSmeltery.searedBlock, 1, BlockSeared.SearedType.BRICK.getMeta()));
        for (int i = 0; i <= 4; i++)
            ModHandler.removeFurnaceSmelting(new ItemStack(TinkerCommons.blockSlimeCongealed, 1, i));
        GameRegistry.addSmelting(OreDictUnifier.get(OrePrefix.dust, Materials.Glass, 8), new ItemStack(TinkerCommons.blockClearGlass), 0f);
    }

    public static void initEnhancedIntegration() {
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(320).EUt(32).inputs(TinkerCommons.mudBrickBlock).fluidOutputs(GCMaterials.Dirt.getFluid(576)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(20).EUt(8).fluidInputs(GCMaterials.Dirt.getFluid(144)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(TinkerCommons.mudBrick).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(80).EUt(8).fluidInputs(GCMaterials.Dirt.getFluid(144)).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).output(Blocks.DIRT).buildAndRegister();

        ModHandler.removeRecipes(TinkerGadgets.fancyFrame);
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.nugget, Materials.Bronze, 4).input("obsidian", 1).outputs(new ItemStack(TinkerGadgets.fancyFrame)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.nugget, GCMaterials.AluminiumBrass, 4).input("obsidian", 1).outputs(new ItemStack(TinkerGadgets.fancyFrame, 1, 1)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.nugget, Materials.Cobalt, 4).input("obsidian", 1).outputs(new ItemStack(TinkerGadgets.fancyFrame, 1, 2)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.nugget, GCMaterials.Ardite, 4).input("obsidian", 1).outputs(new ItemStack(TinkerGadgets.fancyFrame, 1, 3)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.nugget, GCMaterials.Manyullyn, 4).input("obsidian", 1).outputs(new ItemStack(TinkerGadgets.fancyFrame, 1, 4)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.nugget, Materials.Gold, 4).input("obsidian", 1).outputs(new ItemStack(TinkerGadgets.fancyFrame, 1, 5)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(234).input(OrePrefix.dust, Materials.Glass, 4).input(TinkerCommons.blockClearGlass, 1).outputs(new ItemStack(TinkerGadgets.fancyFrame, 1, 6)).buildAndRegister();

        if (!Loader.isModLoaded("gtadditions"))
            ModHandler.addShapelessRecipe("wood_plate_saw", OreDictUnifier.get(OrePrefix.plate, Materials.Wood), 's', new UnificationEntry(OrePrefix.plank, Materials.Wood));

        ModHandler.removeRecipes(ItemBlock.getItemFromBlock(TinkerGadgets.woodenHopper));
        ModHandler.addShapedRecipe("tconstruct_wooden_hopper", new ItemStack(TinkerGadgets.woodenHopper), "PwP", "PCP", " P ", 'P', new UnificationEntry(OrePrefix.plate, Materials.Wood), 'C', "chestWood");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2).input(OrePrefix.plate, Materials.Wood, 5).input("chestWood", 1).output(TinkerGadgets.woodenHopper).buildAndRegister();
        ModHandler.removeRecipes(TinkerCommons.graveyardSoil);
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(32).duration(60).input("dirt", 1).fluidInputs(GCMaterials.Blood.getFluid(40)).input(OrePrefix.dust, Materials.Bone).outputs(TinkerCommons.graveyardSoil).buildAndRegister();
        ModHandler.removeRecipes(TinkerCommons.consecratedSoil);
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(240).inputs(TinkerCommons.graveyardSoil).fluidInputs(GCMaterials.Slime.getFluid(250)).outputs(TinkerCommons.consecratedSoil).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(32).duration(400).input(OrePrefix.plank, Materials.Wood, 1).fluidInputs(Materials.Lava.getFluid(250)).outputs(TinkerCommons.lavawood).buildAndRegister();
        ModHandler.removeRecipes(TinkerCommons.firewood);
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(32).duration(400).inputs(TinkerCommons.lavawood).fluidInputs(Materials.Blaze.getFluid(288)).outputs(TinkerCommons.firewood).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(2).duration(400).inputs(new ItemStack(TinkerCommons.blockClearStainedGlass, 1, W)).fluidInputs(Materials.Chlorine.getFluid(50)).output(TinkerCommons.blockClearGlass).buildAndRegister();

        ModHandler.removeRecipes(TinkerCommons.slimyMudGreen);
        ModHandler.removeRecipes(TinkerCommons.slimyMudBlue);
        ModHandler.removeRecipes(TinkerCommons.slimyMudMagma);

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().EUt(24).duration(4800).inputs(TinkerCommons.slimyMudGreen).fluidInputs(Materials.Water.getFluid(1000)).outputs(TinkerCommons.matSlimeCrystalGreen).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().EUt(24).duration(4800).inputs(TinkerCommons.slimyMudBlue).fluidInputs(Materials.Water.getFluid(1000)).outputs(TinkerCommons.matSlimeCrystalBlue).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().EUt(24).duration(4800).inputs(TinkerCommons.slimyMudMagma).fluidInputs(Materials.Water.getFluid(1000)).outputs(TinkerCommons.matSlimeCrystalMagma).buildAndRegister();

        for (FluidStack lube : GCUtils.sawLubricants) {
            int duration = 80 * lube.amount;
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().EUt(512).duration(duration).inputs(TinkerCommons.slimyMudGreen).fluidInputs(lube).outputs(TinkerCommons.matSlimeCrystalGreen).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().EUt(512).duration(duration).inputs(TinkerCommons.slimyMudBlue).fluidInputs(lube).outputs(TinkerCommons.matSlimeCrystalBlue).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().EUt(512).duration(duration).inputs(TinkerCommons.slimyMudMagma).fluidInputs(lube).outputs(TinkerCommons.matSlimeCrystalMagma).buildAndRegister();
        }

        //Necessary override
        if (Loader.isModLoaded("gtadditions") && GAConfig.Misc.Packager2x2Recipes)
            RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(10).EUt(12).input("slimeballGreen", 4).notConsumable(new IntCircuitIngredient(2)).outputs(new ItemStack(TinkerCommons.blockSlimeCongealed)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(40).input("sand", 1).input("gravel", 1).fluidInputs(Materials.Clay.getFluid(144)).fluidOutputs(new FluidStack(TinkerFluids.searedStone, 144)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(160).input("blockSeared", 1).fluidOutputs(new FluidStack(TinkerFluids.searedStone, 288)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(40).inputs(TinkerCommons.searedBrick).fluidOutputs(new FluidStack(TinkerFluids.searedStone, 72)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(40).inputs(TinkerCommons.grout).fluidOutputs(new FluidStack(TinkerFluids.searedStone, 72)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(140).input(Items.SLIME_BALL).fluidOutputs(GCMaterials.Slime.getFluid(250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).input(Blocks.SLIME_BLOCK).fluidOutputs(GCMaterials.Slime.getFluid(2250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).input(TinkerCommons.blockSlime).fluidOutputs(GCMaterials.Slime.getFluid(2250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).input(TinkerCommons.blockSlimeCongealed).fluidOutputs(GCMaterials.Slime.getFluid(1000)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(140).inputs(TinkerCommons.matSlimeBallBlue).fluidOutputs(new FluidStack(TinkerFluids.blueslime, 250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).inputs(new ItemStack(TinkerCommons.blockSlime, 1, BlockSlime.SlimeType.BLUE.getMeta())).fluidOutputs(new FluidStack(TinkerFluids.blueslime, 2250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).inputs(new ItemStack(TinkerCommons.blockSlimeCongealed, 1, BlockSlime.SlimeType.BLUE.getMeta())).fluidOutputs(new FluidStack(TinkerFluids.blueslime, 1000)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(140).inputs(TinkerCommons.matSlimeBallPurple).fluidOutputs(new FluidStack(TinkerFluids.purpleSlime, 250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).inputs(new ItemStack(TinkerCommons.blockSlime, 1, BlockSlime.SlimeType.PURPLE.getMeta())).fluidOutputs(new FluidStack(TinkerFluids.purpleSlime, 2250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(1260).inputs(new ItemStack(TinkerCommons.blockSlimeCongealed, 1, BlockSlime.SlimeType.PURPLE.getMeta())).fluidOutputs(new FluidStack(TinkerFluids.purpleSlime, 1000)).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(16).duration(100).input("sand", 1).input("dirt", 1).fluidInputs(GCMaterials.Slime.getFluid(1000)).outputs(TinkerCommons.slimyMudGreen).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(16).duration(100).input("sand", 1).input("dirt", 1).fluidInputs(new FluidStack(TinkerFluids.blueslime, 1000)).outputs(TinkerCommons.slimyMudBlue).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(32).duration(100).input(Blocks.SOUL_SAND).inputs(GTUtility.copyAmount(2, TinkerCommons.matSlimeBallMagma)).fluidInputs(GCMaterials.Slime.getFluid(500), Materials.Blaze.getFluid(288)).outputs(TinkerCommons.slimyMudMagma).buildAndRegister();

        //Knightslime
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(TinkerCommons.matSlimeBallPurple).fluidInputs(Materials.Iron.getFluid(144), new FluidStack(TinkerFluids.searedStone, 288)).fluidOutputs(new FluidStack(TinkerFluids.knightslime, 144)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(80).input("ingotKnightslime", 1).fluidOutputs(new FluidStack(TinkerFluids.knightslime, 144)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(720).input("blockKnightslime", 1).fluidOutputs(new FluidStack(TinkerFluids.knightslime, 1296)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(8).input("nuggetKnightslime", 1).fluidOutputs(new FluidStack(TinkerFluids.knightslime, 16)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(10).fluidInputs(new FluidStack(TinkerFluids.knightslime, 144)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(TinkerCommons.ingotKnightSlime).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(90).fluidInputs(new FluidStack(TinkerFluids.knightslime, 1296)).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).outputs(TinkerCommons.blockKnightSlime).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(40).fluidInputs(new FluidStack(TinkerFluids.knightslime, 144)).notConsumable(MetaItems.SHAPE_MOLD_NUGGET).outputs(GTUtility.copyAmount(9, TinkerCommons.nuggetKnightSlime)).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(32).duration(6).input("ingotKnightslime", 9).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).outputs(TinkerCommons.blockKnightSlime).buildAndRegister();
        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().EUt(64).duration(10).input("ingotKnightslime", 9).notConsumable(MetaItems.SHAPE_EXTRUDER_BLOCK).outputs(TinkerCommons.blockKnightSlime).buildAndRegister();

        //Pigiron
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(16).duration(100).input(Items.CLAY_BALL).fluidInputs(Materials.Iron.getFluid(288), new FluidStack(TinkerFluids.blood, 80)).fluidOutputs(new FluidStack(TinkerFluids.pigIron, 288)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(80).input("ingotPigiron", 1).fluidOutputs(new FluidStack(TinkerFluids.pigIron, 144)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(720).input("blockPigiron", 1).fluidOutputs(new FluidStack(TinkerFluids.pigIron, 1296)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(8).input("nuggetPigiron", 1).fluidOutputs(new FluidStack(TinkerFluids.pigIron, 16)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(10).fluidInputs(new FluidStack(TinkerFluids.pigIron, 144)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(TinkerCommons.ingotPigIron).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(90).fluidInputs(new FluidStack(TinkerFluids.pigIron, 1296)).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).outputs(TinkerCommons.blockPigIron).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(40).fluidInputs(new FluidStack(TinkerFluids.pigIron, 144)).notConsumable(MetaItems.SHAPE_MOLD_NUGGET).outputs(GTUtility.copyAmount(9, TinkerCommons.nuggetPigIron)).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(32).duration(6).input("ingotPigiron", 9).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).outputs(TinkerCommons.blockPigIron).buildAndRegister();
        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().EUt(64).duration(10).input("ingotPigiron", 9).notConsumable(MetaItems.SHAPE_EXTRUDER_BLOCK).outputs(TinkerCommons.blockPigIron).buildAndRegister();

        ModHandler.removeRecipes(TinkerCommons.matExpanderH);
        ModHandler.removeRecipes(TinkerCommons.matExpanderW);
        for (MaterialStack lapis : GAUtils.lapisLike) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(58).input("craftingPiston", 2).input(OrePrefix.gem, lapis.material, 2).inputs(TinkerCommons.matSlimeBallPurple).circuitMeta(1).outputs(TinkerCommons.matExpanderH).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(58).input("craftingPiston", 2).input(OrePrefix.gem, lapis.material, 2).inputs(TinkerCommons.matSlimeBallPurple).circuitMeta(2).outputs(TinkerCommons.matExpanderW).buildAndRegister();
        }

        ModHandler.removeRecipes(TinkerCommons.matReinforcement);
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(32).duration(240).input("obsidian", 8).input("cast", 1).outputs(TinkerCommons.matReinforcement).buildAndRegister();

        ModHandler.removeRecipes(TinkerCommons.matSilkyCloth);
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(1920).duration(200).input(OrePrefix.ingot, Materials.Polycaprolactam, 8).input(OrePrefix.ingot, Materials.Gold).outputs(TinkerCommons.matSilkyCloth).buildAndRegister();
        ModHandler.removeRecipes(TinkerCommons.matSilkyJewel);
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().EUt(1920).duration(185).inputs(GTUtility.copyAmount(4, TinkerCommons.matSilkyCloth)).fluidInputs(Materials.Emerald.getFluid(144)).outputs(TinkerCommons.matSilkyJewel).buildAndRegister();

        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(64).duration(135).input("bone", 1).fluidInputs(Materials.OilHeavy.getFluid(250)).outputs(TinkerCommons.matNecroticBone).buildAndRegister();

        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(25).fluidInputs(GCMaterials.Blood.getFluid(160)).notConsumable(MetaItems.SHAPE_MOLD_BALL).outputs(TinkerCommons.matSlimeBallBlood).buildAndRegister();

        //Seared block types
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(10).fluidInputs(new FluidStack(TinkerFluids.searedStone, 72)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(TinkerCommons.searedBrick).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(10).fluidInputs(new FluidStack(TinkerFluids.searedStone, 72)).notConsumable(MetaItems.SHAPE_MOLD_PLATE).outputs(GCMetaItems.SEARED_BRICK_PLATE.getStackForm()).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(40).fluidInputs(new FluidStack(TinkerFluids.searedStone, 288)).notConsumable(MetaItems.SHAPE_MOLD_BLOCK).outputs(new ItemStack(TinkerSmeltery.searedBlock, 1, BlockSeared.SearedType.STONE.getMeta())).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(30).fluidInputs(new FluidStack(TinkerFluids.searedStone, 216)).input("cobblestone", 1).outputs(new ItemStack(TinkerSmeltery.searedBlock, 1, BlockSeared.SearedType.COBBLE.getMeta())).buildAndRegister();
        ModHandler.removeRecipes(ItemBlock.getItemFromBlock(TinkerSmeltery.searedFurnaceController));
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(80).fluidInputs(new FluidStack(TinkerFluids.searedStone, 576)).input("craftingFurnace", 1).outputs(new ItemStack(TinkerSmeltery.searedFurnaceController)).buildAndRegister();
        ModHandler.removeRecipes(ItemBlock.getItemFromBlock(TinkerSmeltery.searedGlass));
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(40).fluidInputs(new FluidStack(TinkerFluids.searedStone, 288)).input(OrePrefix.block, Materials.Glass).outputs(new ItemStack(TinkerSmeltery.searedGlass)).buildAndRegister();
        if (GCConfig.General.alternateSlimeDirtRecipes) {
            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(32).duration(80).input("dirt", 1).fluidInputs(GCMaterials.Slime.getFluid(1000)).outputs(new ItemStack(TinkerWorld.slimeDirt, 1, BlockSlimeDirt.DirtType.GREEN.getMeta())).buildAndRegister();
            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(32).duration(80).input("dirt", 1).fluidInputs(new FluidStack(TinkerFluids.blueslime, 1000)).outputs(new ItemStack(TinkerWorld.slimeDirt, 1, BlockSlimeDirt.DirtType.BLUE.getMeta())).buildAndRegister();
            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(32).duration(80).input("dirt", 1).fluidInputs(new FluidStack(TinkerFluids.purpleSlime, 1000)).outputs(new ItemStack(TinkerWorld.slimeDirt, 1, BlockSlimeDirt.DirtType.PURPLE.getMeta())).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(32).duration(160).input("dirt", 1).inputs(GTUtility.copyAmount(4, TinkerCommons.matSlimeBallMagma)).fluidInputs(GCMaterials.Slime.getFluid(500), Materials.Blaze.getFluid(288)).outputs(new ItemStack(TinkerWorld.slimeDirt, 1, BlockSlimeDirt.DirtType.MAGMA.getMeta())).buildAndRegister();
        }
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(200).inputs(new ItemStack(TinkerSmeltery.searedBlock, 1, BlockSeared.SearedType.BRICK.getMeta())).outputs(new ItemStack(TinkerSmeltery.searedBlock, 1, BlockSeared.SearedType.BRICK_CRACKED.getMeta())).buildAndRegister();

        if (GCConfig.General.alternateSlimeballRecipes) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(90).input(Items.SLIME_BALL).fluidInputs(Materials.Blaze.getFluid(144), GCMaterials.Blood.getFluid(160), Materials.Lava.getFluid(250)).outputs(TinkerCommons.matSlimeBallMagma).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(90).input(Items.SLIME_BALL).input("dyeBlue", 1).fluidInputs(Materials.Cobalt.getFluid(144)).outputs(TinkerCommons.matSlimeBallBlue).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(90).input(Items.SLIME_BALL).input("dyePurple", 1).fluidInputs(Materials.BlackBronze.getFluid(144)).outputs(TinkerCommons.matSlimeBallPurple).buildAndRegister();
        }

        if (GCConfig.General.alternateSlimeTreeRecipes) {
            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(40).input("treeSapling", 1).fluidInputs(GCMaterials.Slime.getFluid(500)).outputs(new ItemStack(TinkerWorld.slimeSapling, 1, 0)).buildAndRegister();
            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(40).input("treeSapling", 1).fluidInputs(new FluidStack(TinkerFluids.purpleSlime, 500)).outputs(new ItemStack(TinkerWorld.slimeSapling, 1, 1)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(32).duration(40).input("treeSapling", 1).inputs(GTUtility.copyAmount(2, TinkerCommons.matSlimeBallMagma)).fluidInputs(GCMaterials.Slime.getFluid(250), Materials.Blaze.getFluid(144)).outputs(new ItemStack(TinkerWorld.slimeSapling, 1, 2)).buildAndRegister();
        }

        ModHandler.removeRecipes(TinkerTools.pattern);
        ModHandler.addShapedRecipe("tconstruct_pattern", new ItemStack(TinkerTools.pattern, 2), "s", "P", "P", 'P', new UnificationEntry(OrePrefix.plank, Materials.Wood));

        ModHandler.removeRecipes(new ItemStack(TinkerTools.toolTables));
        ModHandler.addShapedRecipe("tconstruct_workbench", new ItemStack(TinkerTools.toolTables), "W", "s", 'W', "workbench");

        //Stained Clear Glass
        for (int i = 0; i <= 15; i++) {
            String color = GCUtils.getColorFromMeta(i);
            ModHandler.addShapelessRecipe("stained_clear_glass_shapeless_" + color.toLowerCase(), new ItemStack(TinkerCommons.blockClearStainedGlass, 1, i), TinkerCommons.blockClearGlass, "dye" + color);
        }

        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.searedTank));
        ModHandler.addShapedRecipe("seared_tank", new ItemStack(TinkerSmeltery.searedTank), "BBB", "BGB", "BBB", 'B', "ingotBrickSeared", 'G', "blockGlassClear");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.searedTank, 1, 1));
        ModHandler.addShapedRecipe("seared_gauge", new ItemStack(TinkerSmeltery.searedTank, 1, 1), " P ", "PWP", " P ", 'P', "plateBrickSeared", 'W', "blockGlassClear");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.searedTank, 1, 2));
        ModHandler.addShapedRecipe("seared_window", new ItemStack(TinkerSmeltery.searedTank, 1, 2), "PWP", "PWP", "PWP", 'P', "plateBrickSeared", 'W', "blockGlassClear");


        ModHandler.addShapedRecipe("seared_plate", GCMetaItems.SEARED_BRICK_PLATE.getStackForm(), "h", "S", "S", 'S', TinkerCommons.searedBrick);
        RecipeMaps.BENDER_RECIPES.recipeBuilder().duration(30).EUt(8).input("ingotBrickSeared", 1).notConsumable(new IntCircuitIngredient(0)).outputs(GCMetaItems.SEARED_BRICK_PLATE.getStackForm()).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32).duration(40).input("plateBrickSeared", 1).fluidOutputs(new FluidStack(TinkerFluids.searedStone, 72)).buildAndRegister();

        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.smelteryController));
        ModHandler.addShapedRecipe("tconstruct_smeltery_controller", new ItemStack(TinkerSmeltery.smelteryController), "SPS", "PTP", "dPw", 'S', new UnificationEntry(OrePrefix.screw, Materials.Iron), 'P', "plateBrickSeared", 'T', new ItemStack(TinkerSmeltery.tinkerTankController));
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.faucet));
        ModHandler.addShapedRecipe("tconstruct_faucet", new ItemStack(TinkerSmeltery.faucet), "BhB", " P ", 'B', "ingotBrickSeared", 'P', "plateBrickSeared");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.channel, 3));
        ModHandler.addShapedRecipe("tconstruct_channel", new ItemStack(TinkerSmeltery.channel, 3), "BhB", "PPP", 'B', "ingotBrickSeared", 'P', "plateBrickSeared");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.castingBlock));
        ModHandler.addShapedRecipe("tconstruct_casting_table", new ItemStack(TinkerSmeltery.castingBlock), "PPP", "BhB", "B B", 'B', "ingotBrickSeared", 'P', "plateBrickSeared");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.castingBlock, 1, 1));
        ModHandler.addShapedRecipe("tconstruct_casting_basin", new ItemStack(TinkerSmeltery.castingBlock, 1, 1), "PBP", "PhP", "PPP", 'B', "ingotBrickSeared", 'P', "plateBrickSeared");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.smelteryIO));
        ModHandler.addShapedRecipe("tconstruct_smeltery_io", new ItemStack(TinkerSmeltery.smelteryIO), "BPB", "PwP", "BPB", 'B', "ingotBrickSeared", 'P', "plateBrickSeared");
        ModHandler.removeRecipes(new ItemStack(TinkerSmeltery.tinkerTankController));
        ModHandler.addShapedRecipe("tconstruct_tinker_tank_controller", new ItemStack(TinkerSmeltery.tinkerTankController), "BPB", "PCP", "BRB", 'B', "ingotBrickSeared", 'P', "plateBrickSeared", 'C', Items.BUCKET, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Iron));

        ModHandler.removeRecipes(TinkerGadgets.stoneStick);
        ModHandler.addShapedRecipe("tconstruct_stone_rod", new ItemStack(TinkerGadgets.stoneStick), "f ", " C", 'C', "cobblestone");
        RecipeMaps.LATHE_RECIPES.recipeBuilder().EUt(16).duration(158).input("cobblestone", 1).output(TinkerGadgets.stoneStick, 2).buildAndRegister();
        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().EUt(48).duration(158).input("cobblestone", 1).notConsumable(MetaItems.SHAPE_EXTRUDER_ROD).output(TinkerGadgets.stoneStick, 2).buildAndRegister();
        for (int i = 0; i <= 4; i++) {
            for (FluidStack lube : GCUtils.sawLubricants) {
                int duration = lube.amount > 1 ? 60 * (int) (lube.amount / 1.25) : 60;
                RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(duration).EUt(32).inputs(new ItemStack(TinkerCommons.blockSlimeCongealed, 1, i)).fluidInputs(lube).outputs(new ItemStack(TinkerGadgets.slimeChannel, 3, i)).buildAndRegister();
            }
        }

        ModHandler.removeRecipes(TinkerGadgets.throwball);
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(18).duration(14).input(Items.SNOWBALL).fluidInputs(Materials.Glowstone.getFluid(18), Materials.Blaze.getFluid(18)).outputs(new ItemStack(TinkerGadgets.throwball)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(64).inputs(MetaItems.GELLED_TOLUENE.getStackForm(2), GTUtility.copyAmount(2, TinkerCommons.matSlimeBallBlood)).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(new ItemStack(TinkerGadgets.throwball, 2, 1)).buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().EUt(16).duration(4000).input(Items.WHEAT, 16).fluidInputs(Materials.Water.getFluid(1000)).output(TinkerGadgets.spaghetti).buildAndRegister();

        ModHandler.addShapedRecipe("rubber_drop_stone_torch", new ItemStack(TinkerGadgets.stoneTorch, 3), "X", "Y", 'X', MetaItems.RUBBER_DROP, 'Y', new UnificationEntry(OrePrefix.stick, Materials.Stone));
        ModHandler.addShapedRecipe("lignite_coal_stone_torch", new ItemStack(TinkerGadgets.stoneTorch, 4), "X", "Y", 'X', new UnificationEntry(OrePrefix.gem, Materials.Lignite), 'Y', new UnificationEntry(OrePrefix.stick, Materials.Stone));
        ModHandler.addShapedRecipe("stone_torch_sulfur", new ItemStack(TinkerGadgets.stoneTorch, 2), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Sulfur), 'S', new UnificationEntry(OrePrefix.stick, Materials.Stone));
        ModHandler.addShapedRecipe("stone_torch_phosphor", new ItemStack(TinkerGadgets.stoneTorch, 6), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Phosphorus), 'S', new UnificationEntry(OrePrefix.stick, Materials.Stone));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.stick, Materials.Stone).inputs(new ItemStack(Items.COAL, 1, W)).outputs(new ItemStack(TinkerGadgets.stoneTorch, 4)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.stick, Materials.Stone).input(OrePrefix.dust, Materials.Sulfur, 1).outputs(new ItemStack(TinkerGadgets.stoneTorch, 2)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.stick, Materials.Stone).input(OrePrefix.dust, Materials.Phosphorus, 1).outputs(new ItemStack(TinkerGadgets.stoneTorch, 6)).duration(400).buildAndRegister();
    }

    public static void glassRecipes() {
        removeRecipesByInputs(new ItemStack(Blocks.GLASS));
        removeRecipesByInputs(new ItemStack(Blocks.STAINED_GLASS));
        removeRecipesByInputs(new ItemStack(Blocks.GLASS_PANE));
        removeRecipesByInputs(new ItemStack(Blocks.STAINED_GLASS_PANE));
        removeRecipesByInputs(new ItemStack(Items.GLASS_BOTTLE));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.dust, Materials.Quartzite));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.dust, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gem, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemFlawless, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemFlawed, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemChipped, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.plate, Materials.Glass));
        removeRecipesByInputs(OreDictUnifier.get(OrePrefix.lens, Materials.Glass));

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(Blocks.GLASS)).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(Blocks.GLASS_PANE)).fluidOutputs(Materials.Glass.getFluid(375)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(Blocks.STAINED_GLASS)).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(Blocks.STAINED_GLASS_PANE)).fluidOutputs(Materials.Glass.getFluid(375)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.dust, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.gem, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(Items.GLASS_BOTTLE)).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(20).EUt(32).input(OrePrefix.gemChipped, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(40).EUt(32).input(OrePrefix.gemFlawed, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(500)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(32).input(OrePrefix.gemFlawless, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(2000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(320).EUt(32).input(OrePrefix.gemExquisite, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(4000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.plate, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.lens, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(750)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(28).input(OrePrefix.dust, Materials.Quartzite).fluidOutputs(Materials.Glass.getFluid(500)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(TinkerCommons.blockClearGlass)).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();

        removeRecipesByInputs(new ItemStack[]{MetaItems.SHAPE_MOLD_BLOCK.getStackForm()}, new FluidStack[]{Materials.Glass.getFluid(144)});
        removeRecipesByInputs(new ItemStack[]{MetaItems.SHAPE_MOLD_PLATE.getStackForm()}, new FluidStack[]{Materials.Glass.getFluid(144)}, 4);
        removeRecipesByInputs(new ItemStack[]{MetaItems.SHAPE_MOLD_PLATE.getStackForm()}, new FluidStack[]{Materials.Glass.getFluid(144)}, 8);

        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(18).EUt(4).notConsumable(MetaItems.SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(Materials.Glass.getFluid(1000)).outputs(new ItemStack(TinkerCommons.blockClearGlass)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(18).EUt(4).notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(Materials.Glass.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder().duration(480).EUt(30).inputs(new ItemStack(TinkerCommons.blockClearGlass)).fluidInputs(Materials.Oxygen.getFluid(480)).outputs(new ItemStack(Blocks.GLASS)).buildAndRegister();
        RecipeMaps.PLASMA_ARC_FURNACE_RECIPES.recipeBuilder().duration(30).EUt(10).inputs(new ItemStack(TinkerCommons.blockClearGlass)).fluidInputs(Materials.Argon.getPlasma(1)).fluidOutputs(Materials.Argon.getFluid(1)).outputs(new ItemStack(Blocks.GLASS)).buildAndRegister();
        RecipeMaps.PLASMA_ARC_FURNACE_RECIPES.recipeBuilder().duration(30).EUt(10).inputs(new ItemStack(TinkerCommons.blockClearGlass)).fluidInputs(Materials.Nitrogen.getPlasma(2)).fluidOutputs(Materials.Argon.getFluid(2)).outputs(new ItemStack(Blocks.GLASS)).buildAndRegister();
    }

    private static void removeRecipesByInputs(ItemStack... itemInputs) {
        List<ItemStack> inputs = new ArrayList<>(Arrays.asList(itemInputs));
        RecipeMaps.FLUID_EXTRACTION_RECIPES.removeRecipe(RecipeMaps.FLUID_EXTRACTION_RECIPES.findRecipe(Integer.MAX_VALUE, inputs, new ArrayList<>(), Integer.MAX_VALUE));
    }

    private static void removeRecipesByInputs(ItemStack[] itemInputs, FluidStack[] fluidInputs) {
        List<ItemStack> itemIn = new ArrayList<>(Arrays.asList(itemInputs));
        List<FluidStack> fluidIn = new ArrayList<>(Arrays.asList(fluidInputs));
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.removeRecipe(RecipeMaps.FLUID_SOLIDFICATION_RECIPES.findRecipe(Integer.MAX_VALUE, itemIn, fluidIn, Integer.MAX_VALUE));
    }

    private static void removeRecipesByInputs(ItemStack[] itemInputs, FluidStack[] fluidInputs, int voltage) {
        List<ItemStack> itemIn = new ArrayList<>(Arrays.asList(itemInputs));
        List<FluidStack> fluidIn = new ArrayList<>(Arrays.asList(fluidInputs));
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.removeRecipe(RecipeMaps.FLUID_SOLIDFICATION_RECIPES.findRecipe(voltage, itemIn, fluidIn, Integer.MAX_VALUE));
    }
}
