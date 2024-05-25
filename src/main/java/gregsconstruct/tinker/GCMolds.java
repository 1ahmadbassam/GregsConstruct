package gregsconstruct.tinker;

import gregsconstruct.common.GCMetaItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class GCMolds {
    public static final ItemStack[] molds = {
            MetaItems.SHAPE_MOLD_ANVIL.getStackForm(),
            MetaItems.SHAPE_MOLD_BALL.getStackForm(),
            MetaItems.SHAPE_MOLD_BLOCK.getStackForm(),
            MetaItems.SHAPE_MOLD_BOTTLE.getStackForm(),
            MetaItems.SHAPE_MOLD_CREDIT.getStackForm(),
            MetaItems.SHAPE_MOLD_CYLINDER.getStackForm(),
            MetaItems.SHAPE_MOLD_GEAR.getStackForm(),
            MetaItems.SHAPE_MOLD_INGOT.getStackForm(),
            MetaItems.SHAPE_MOLD_NAME.getStackForm(),
            MetaItems.SHAPE_MOLD_NUGGET.getStackForm(),
            MetaItems.SHAPE_MOLD_PLATE.getStackForm(),
            MetaItems.SHAPE_MOLD_GEAR_SMALL.getStackForm(),
            MetaItems.SHAPE_MOLD_ROTOR.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_AXE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_BLOCK.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_BOLT.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_BOTTLE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_CELL.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_FILE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_GEAR.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_HAMMER.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_HOE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_INGOT.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_PIPE_LARGE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_PIPE_MEDIUM.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_PICKAXE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_RING.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_ROD.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_SAW.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_SHOVEL.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_PIPE_SMALL.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_SWORD.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_PIPE_TINY.getStackForm(),
            MetaItems.SHAPE_EXTRUDER_WIRE.getStackForm()
    };

    public static void initTinker() {
        if (Materials.Steel.getMaterialFluid() == null) return;
        ModHandler.removeRecipes(MetaItems.SHAPE_EMPTY.getStackForm());
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EMPTY.getStackForm(), Materials.Steel.getMaterialFluid(), 576, 160));
        for (ItemStack mold : molds)
            ModHandler.removeRecipes(mold);
        ModHandler.addShapedRecipe("anvil_mold_form", GCMetaItems.MOLD_FORM_ANVIL.getStackForm(), "fx ", " M ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_ANVIL.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_ANVIL.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("ball_mold_form", GCMetaItems.MOLD_FORM_BALL.getStackForm(), "f x", " M ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_BALL.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_BALL.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("block_mold_form", GCMetaItems.MOLD_FORM_BLOCK.getStackForm(), "f  ", "xM ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_BLOCK.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_BLOCK.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("bottle_mold_form", GCMetaItems.MOLD_FORM_BOTTLE.getStackForm(), "f  ", " Mx", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_BOTTLE.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_BOTTLE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("coinage_mold_form", GCMetaItems.MOLD_FORM_COINAGE.getStackForm(), "f  ", " M ", "x  ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_CREDIT.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_COINAGE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("cylinder_mold_form", GCMetaItems.MOLD_FORM_CYLINDER.getStackForm(), "f  ", " M ", " x ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_CYLINDER.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("gear_mold_form", GCMetaItems.MOLD_FORM_GEAR.getStackForm(), "f  ", " M ", "  x", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_GEAR.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_GEAR.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("ingot_mold_form", GCMetaItems.MOLD_FORM_INGOT.getStackForm(), "xf ", " M ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_INGOT.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_INGOT.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("name_mold_form", GCMetaItems.MOLD_FORM_NAME.getStackForm(), " fx", " M ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_NAME.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_NAME.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("nugget_mold_form", GCMetaItems.MOLD_FORM_NUGGETS.getStackForm(), " f ", "xM ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_NUGGET.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_NUGGETS.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("plate_mold_form", GCMetaItems.MOLD_FORM_PLATE.getStackForm(), " f ", " Mx", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_PLATE.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_PLATE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("small_gear_mold_form", GCMetaItems.MOLD_FORM_SMALL_GEAR.getStackForm(), " f ", " M ", "x  ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_GEAR_SMALL.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_SMALL_GEAR.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("rotor_mold_form", GCMetaItems.MOLD_FORM_ROTOR.getStackForm(), " f ", " M ", " x ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_MOLD_ROTOR.getStackForm(), RecipeMatch.of(GCMetaItems.MOLD_FORM_ROTOR.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));

        ModHandler.addShapedRecipe("axe_shape", GCMetaItems.SHAPE_AXE_HEAD.getStackForm(), " f ", " M ", " x ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_AXE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_AXE_HEAD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("block_shape", GCMetaItems.SHAPE_BLOCK.getStackForm(), " f ", " M ", "  x", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_BLOCK.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_BLOCK.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("bolt_shape", GCMetaItems.SHAPE_BOLT.getStackForm(), "x f", " M ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_BOLT.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_BOLT.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("bottle_shape", GCMetaItems.SHAPE_BOTTLE.getStackForm(), " xf", " M ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_BOTTLE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_BOTTLE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("cell_shape", GCMetaItems.SHAPE_CELL.getStackForm(), "  f", "xM ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_CELL.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_CELL.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("file_shape", GCMetaItems.SHAPE_FILE_HEAD.getStackForm(), "  f", " Mx", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_FILE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_FILE_HEAD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("gear_shape", GCMetaItems.SHAPE_GEAR.getStackForm(), "  f", " M ", "x  ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_GEAR.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_GEAR.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("hammer_shape", GCMetaItems.SHAPE_HAMMER_HEAD.getStackForm(), "  f", " M ", " x ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_HAMMER.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_HAMMER_HEAD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("hoe_shape", GCMetaItems.SHAPE_HOE_HEAD.getStackForm(), "  f", " M ", "  x", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_HOE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_HOE_HEAD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("ingot_shape", GCMetaItems.SHAPE_INGOT.getStackForm(), "x  ", "fM ", "  x", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_INGOT.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_INGOT.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("large_pipe_shape", GCMetaItems.SHAPE_LARGE_PIPE.getStackForm(), " x ", "fM ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_PIPE_LARGE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_LARGE_PIPE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("medium_pipe_shape", GCMetaItems.SHAPE_NORMAL_PIPE.getStackForm(), "  x", "fM ", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_PIPE_MEDIUM.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_NORMAL_PIPE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("pickaxe_shape", GCMetaItems.SHAPE_PICKAXE_HEAD.getStackForm(), "   ", "fMx", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_PICKAXE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_PICKAXE_HEAD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("plate_shape", GCMetaItems.SHAPE_PLATE.getStackForm(), "   ", "fM ", "x  ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_PLATE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("ring_shape", GCMetaItems.SHAPE_RING.getStackForm(), "   ", "fM ", " x ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_RING.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_RING.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("rod_shape", GCMetaItems.SHAPE_ROD.getStackForm(), "   ", "fM ", "  x", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_ROD.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_ROD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("saw_shape", GCMetaItems.SHAPE_SAW_BLADE.getStackForm(), "x  ", " Mf", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_SAW.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_SAW_BLADE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("shovel_shape", GCMetaItems.SHAPE_SHOVEL_HEAD.getStackForm(), " x ", " Mf", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_SHOVEL.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_SHOVEL_HEAD.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("small_pipe_shape", GCMetaItems.SHAPE_SMALL_PIPE.getStackForm(), "  x", " Mf", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_PIPE_SMALL.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_SMALL_PIPE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("sword_shape", GCMetaItems.SHAPE_SWORD_BLADE.getStackForm(), "   ", "xMf", "   ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_SWORD.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_SWORD_BLADE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("tiny_pipe_shape", GCMetaItems.SHAPE_TINY_PIPE.getStackForm(), "   ", " Mf", "x  ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_PIPE_TINY.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_TINY_PIPE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
        ModHandler.addShapedRecipe("wire_shape", GCMetaItems.SHAPE_WIRE.getStackForm(), "   ", " Mf", " x ", 'M', new ItemStack(TinkerSmeltery.cast));
        TinkerRegistry.registerTableCasting(new CastingRecipe(MetaItems.SHAPE_EXTRUDER_WIRE.getStackForm(), RecipeMatch.of(GCMetaItems.SHAPE_WIRE.getStackForm()), Materials.Steel.getMaterialFluid(), 576, true, false));
    }

    public static void recipes() {
        ModHandler.removeFurnaceSmelting(TinkerCommons.grout);
        ModHandler.addShapelessRecipe("seared_brick", GCMetaItems.COMPRESSED_GROUT.getStackForm(), TinkerCommons.grout, MetaItems.WOODEN_FORM_BRICK);
        ModHandler.addShapedRecipe("eight_seared_brick", GCMetaItems.COMPRESSED_GROUT.getStackForm(8), "BBB", "BFB", "BBB", 'B', TinkerCommons.grout, 'F', MetaItems.WOODEN_FORM_BRICK);
        ModHandler.addSmeltingRecipe(GCMetaItems.COMPRESSED_GROUT.getStackForm(), TinkerCommons.searedBrick);
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2).inputs(TinkerCommons.grout).notConsumable(MetaItems.SHAPE_MOLD_INGOT.getStackForm()).outputs(TinkerCommons.searedBrick).buildAndRegister();
    }
}
