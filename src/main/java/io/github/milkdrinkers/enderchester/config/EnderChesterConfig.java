package io.github.milkdrinkers.enderchester.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class EnderChesterConfig {
    public CheckSection check = new CheckSection();

    @ConfigSerializable
    public static class CheckSection {
        @Comment("Should we check the player for the permission \"enderchester.use\"")
        public boolean permission = true;

        @Comment("Should we disallow opening enderchests through the plugin when in creative mode?")
        public boolean creative = false;

        @Comment("Close the inventory if the player moves. This prevents using mods to access enderchests while moving.")
        public boolean disableMoveWhileOpen = false;

        @Comment("A list of worldnames where players won't be able to open enderchests through the plugin")
        public BlackListSection blacklist = new BlackListSection();

        @ConfigSerializable
        public static class BlackListSection {
            @Comment("Whether the blacklist should be used or not")
            public boolean enabled = false;

            public List<String> worlds = new ArrayList<>(List.of(
                "worldname1",
                "someworldname2"
            ));
        }

        @Comment("A list of worldnames where players will be able to open enderchests through the plugin")
        public WhiteListSection whitelist = new WhiteListSection();

        @ConfigSerializable
        public static class WhiteListSection {
            @Comment("Whether the whitelist should be used or not")
            public boolean enabled = false;

            public List<String> worlds = new ArrayList<>(List.of(
                "worldname1",
                "someworldname2"
            ));
        }
    }

    public SoundSection sound = new SoundSection();

    @ConfigSerializable
    public static class SoundSection {
        @Comment("Should a ender chest opening sound be played when it is opened through the plugin?")
        public boolean opening = true;

        @Comment("Can be any value between 0 and 1. For example, 0.8 would be at 80% volume.")
        public float volume = 1.0f;

        @Comment("The sound that will be played. If the selected sound does not exist, it will default to ender chest opening sound")
        public String effect = "minecraft:block.ender_chest.open";
    }

    @Comment("Do not change this value!")
    public int configVersion = 3;
}
