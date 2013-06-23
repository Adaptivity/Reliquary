package xreliquary.lib;

public class Reference {
	// class for all the mod related constants
	public static final String VERSION = "1.5.2";
	public static final String MOD_ID = "xreliquary";
	public static final String MOD_NAME = "Xeno's Reliquary";
	public static final String CLIENT_PROXY = "xreliquary.client.ClientProxy";
	public static final String COMMON_PROXY = "xreliquary.common.CommonProxy";
	// these are ugly, but thankfully I'll probably be getting rid of them soon.
	public static final int WATER_SPRITE = 0;
	public static final int SPLASH_POTION_SPRITE = 1;
	public static final int GRENADE_SPRITE = 12;
	// gunpowder cost, may eventually be made configurable.
	public static final int DESTRUCTION_CATALYST_COST = 3;
	// the amount of capacity each upgrade yields. may eventually be made
	// configurable.
	public static final int CAPACITY_UPGRADE_INCREMENT = 64;
	// the limit to any amount of capacity increases. this will have a hard
	// coded config
	// cap of 32000, regardless of how high it is set.
	public static final int CAPACITY_UPGRADE_MAXIMUM = 32000;
	// art path constants
	public static final String ART_PATH_ENTITIES = "/mods/xreliquary/textures/entities/";
	public static final String THROWN_ITEM_SPRITES = "thrownItemsSheet.png";
	public static final String MODEL_TEXTURE_PATH = "/mods/xreliquary/textures/models/";
	public static final String HANDGUN_TEXTURE = MODEL_TEXTURE_PATH + "handgun.png";
	// these are the shot paths which will probably be removed soon
	public static final String NEUTRAL = ART_PATH_ENTITIES + "neutralShot.png";
	public static final String EXORCISM = ART_PATH_ENTITIES + "exorcismShot.png";
	public static final String BLAZE = ART_PATH_ENTITIES + "blazeShot.png";
	public static final String ENDER = ART_PATH_ENTITIES + "enderShot.png";
	public static final String CONCUSSIVE = ART_PATH_ENTITIES + "concussiveShot.png";
	public static final String BUSTER = ART_PATH_ENTITIES + "busterShot.png";
	public static final String SEEKER = ART_PATH_ENTITIES + "seekerShot.png";
	public static final String SAND = ART_PATH_ENTITIES + "sandShot.png";
	public static final String STORM = ART_PATH_ENTITIES + "stormShot.png";
	// sound path constants
	public static final String SOUND_RESOURCE_LOCATION = "mods/xreliquary/sound/";
	public static final String SOUND_PREFIX = "mods.xreliquary.sound.";
	public static final String LOAD_SOUND = SOUND_PREFIX + "load";
	public static final String SHOT_SOUND = SOUND_PREFIX + "shot";
	public static final String[] soundFiles = { SOUND_RESOURCE_LOCATION + "load.ogg", SOUND_RESOURCE_LOCATION + "shot.ogg" };
	// Misc options for configuration
	public static final boolean DISABLE_COIN_AUDIO_DEFAULT = false;
	public static final boolean DISABLE_GUN_ITEMS_DEFAULT = false;
	public static final boolean DISABLE_POTION_ITEMS_DEFAULT = false;
}
