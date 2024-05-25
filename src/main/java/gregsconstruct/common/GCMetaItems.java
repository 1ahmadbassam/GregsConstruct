package gregsconstruct.common;

import gregtech.api.items.metaitem.MetaItem;

import java.util.List;

public class GCMetaItems {
    private static final List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();

    public static MetaItem<?>.MetaValueItem COMPRESSED_GROUT;
    public static MetaItem<?>.MetaValueItem SEARED_BRICK_PLATE;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_ANVIL;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_BALL;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_BLOCK;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_BOTTLE;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_COINAGE;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_CYLINDER;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_GEAR;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_INGOT;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_NAME;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_NUGGETS;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_PLATE;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_SMALL_GEAR;
    public static MetaItem<?>.MetaValueItem MOLD_FORM_ROTOR;
    public static MetaItem<?>.MetaValueItem SHAPE_AXE_HEAD;
    public static MetaItem<?>.MetaValueItem SHAPE_BLOCK;
    public static MetaItem<?>.MetaValueItem SHAPE_BOLT;
    public static MetaItem<?>.MetaValueItem SHAPE_BOTTLE;
    public static MetaItem<?>.MetaValueItem SHAPE_CELL;
    public static MetaItem<?>.MetaValueItem SHAPE_FILE_HEAD;
    public static MetaItem<?>.MetaValueItem SHAPE_GEAR;
    public static MetaItem<?>.MetaValueItem SHAPE_HAMMER_HEAD;
    public static MetaItem<?>.MetaValueItem SHAPE_HOE_HEAD;
    public static MetaItem<?>.MetaValueItem SHAPE_INGOT;
    public static MetaItem<?>.MetaValueItem SHAPE_LARGE_PIPE;
    public static MetaItem<?>.MetaValueItem SHAPE_NORMAL_PIPE;
    public static MetaItem<?>.MetaValueItem SHAPE_PICKAXE_HEAD;
    public static MetaItem<?>.MetaValueItem SHAPE_PLATE;
    public static MetaItem<?>.MetaValueItem SHAPE_RING;
    public static MetaItem<?>.MetaValueItem SHAPE_ROD;
    public static MetaItem<?>.MetaValueItem SHAPE_SAW_BLADE;
    public static MetaItem<?>.MetaValueItem SHAPE_SHOVEL_HEAD;
    public static MetaItem<?>.MetaValueItem SHAPE_SMALL_PIPE;
    public static MetaItem<?>.MetaValueItem SHAPE_SWORD_BLADE;
    public static MetaItem<?>.MetaValueItem SHAPE_TINY_PIPE;
    public static MetaItem<?>.MetaValueItem SHAPE_WIRE;

    public static void init() {
        GCMetaItem item = new GCMetaItem();
        item.setRegistryName("gc_meta_item");
    }

    public static void registerOreDict() {
        for (MetaItem<?> item : ITEMS) {
            if (item instanceof GCMetaItem) {
                ((GCMetaItem) item).registerOreDict();
            }
        }
    }
}
