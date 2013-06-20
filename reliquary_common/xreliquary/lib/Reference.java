package xreliquary.lib;

public class Reference {
    // class for all the mod related constants
    public static final String VERSION = "1.5.2";

    public static final String MOD_ID = "xreliquary";
    public static final String MOD_NAME = "Xeno's Reliquary";

    public static final String CLIENT_PROXY = "xreliquary.client.ClientProxy";
    public static final String COMMON_PROXY = "xreliquary.common.CommonProxy";

    public static final int WATER_SPRITE = 0;
    public static final int SPLASH_POTION_SPRITE = 1;
    public static final int GRENADE_SPRITE = 12;

    public static final int DESTRUCTION_CATALYST_COST = 3; // gunpowder cost
    public static final int CAPACITY_UPGRADE_INCREMENT = 64;

    public static final String ART_PATH_ENTITIES = "/mods/xreliquary/textures/entities/";
    public static final String THROWN_ITEM_SPRITES = "thrownItemsSheet.png";

    public static final String MODEL_TEXTURE_PATH = "/mods/xreliquary/textures/models/";
    public static final String HANDGUN_TEXTURE = MODEL_TEXTURE_PATH
            + "handgun.png";

    public static final int SPLASH_META = 0;
    public static final int APHRODITE_META = 1;
    public static final int POISON_META = 2;
    public static final int ACID_META = 3;
    public static final int CONFUSION_META = 4;
    public static final int SLOWING_META = 5;
    public static final int WEAKNESS_META = 6;
    public static final int WITHER_META = 7;
    public static final int BLINDING_META = 8;
    public static final int RUINATION_META = 9;
    public static final int FERTILIZER_META = 10;

    public static final int EMPTY_VIAL_META = 11;
    public static final int POTION_META = 12;
    public static final int SPEED_META = 13;
    public static final int DIGGING_META = 14;
    public static final int STRENGTH_META = 15;
    public static final int HEALING_META = 16;
    public static final int BOUNDING_META = 17;
    public static final int REGENERATION_META = 18;
    public static final int RESISTANCE_META = 19;
    public static final int FIRE_WARDING_META = 20;
    public static final int BREATHING_META = 21;
    public static final int INVISIBILITY_META = 22;
    public static final int INFRAVISION_META = 23;
    public static final int PROTECTION_META = 24;
    public static final int POTENCE_META = 25;
    public static final int CELERITY_META = 26;
    public static final int PANACEA_META = 27;
    public static final int WATER_META = 28;

    public static final String SOUND_RESOURCE_LOCATION = "mods/xreliquary/sound/";
    public static final String SOUND_PREFIX = "mods.xreliquary.sound.";
    public static final String LOAD_SOUND = SOUND_PREFIX + "load";
    public static final String SHOT_SOUND = SOUND_PREFIX + "shot";
    public static final String[] soundFiles = {
            SOUND_RESOURCE_LOCATION + "load.ogg",
            SOUND_RESOURCE_LOCATION + "shot.ogg" };

    // Misc options for configuration
    public static final boolean DISABLE_COIN_AUDIO_DEFAULT = false;

    public static final String NEUTRAL = ART_PATH_ENTITIES + "neutralShot.png";
    public static final String EXORCISM = ART_PATH_ENTITIES
            + "exorcismShot.png";
    public static final String BLAZE = ART_PATH_ENTITIES + "blazeShot.png";
    public static final String ENDER = ART_PATH_ENTITIES + "enderShot.png";
    public static final String CONCUSSIVE = ART_PATH_ENTITIES
            + "concussiveShot.png";
    public static final String BUSTER = ART_PATH_ENTITIES + "busterShot.png";
    public static final String SEEKER = ART_PATH_ENTITIES + "seekerShot.png";
    public static final String SAND = ART_PATH_ENTITIES + "sandShot.png";
    public static final String STORM = ART_PATH_ENTITIES + "stormShot.png";

}
