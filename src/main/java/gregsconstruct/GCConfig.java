package gregsconstruct;

import net.minecraftforge.common.config.Config;

@Config(modid = GregsConstruct.MODID)

public class GCConfig {
    @Config.Comment("General items")
    public static General General = new General();

    public static class General {
        @Config.Comment("Should alternate slimeball recipes be provided? Useful for worlds which do not have slime island generation")
        @Config.Name("Generate alternate slimeball recipes")
        public boolean alternateSlimeballRecipes = true;

        @Config.Comment("Should alternate slime dirt recipes be provided? Useful for worlds which do not have slime island generation")
        @Config.Name("Generate alternate slime dirt recipes")
        public boolean alternateSlimeDirtRecipes = true;

        @Config.Comment("Should alternate slime tree recipes be provided? Useful for worlds which do not have slime island generation")
        @Config.Name("Generate alternate slime tree recipes")
        public boolean alternateSlimeTreeRecipes = true;

    }
}
