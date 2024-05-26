package gregsconstruct;

import net.minecraftforge.common.config.Config;

@Config(modid = GregsConstruct.MODID)

public class GCConfig {
    @Config.Comment("General items")
    public static General General = new General();

    @Config.Comment("Special items")
    public static Special Special = new Special();

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

    public static class Special {
        @Config.Comment("Should plate and gear casts be removed?\nThis will make plate-making significantly harder until GregTech's LV machines are available.")
        @Config.Name("Disable plate and gear casting")
        public boolean simpleCasting = false;
    }
}
