package xreliquary.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import lib.enderwizards.sandstone.mod.config.ConfigReference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import xreliquary.Reliquary;
import xreliquary.blocks.tile.TileEntityAltar;
import xreliquary.common.gui.GUIHandler;
import xreliquary.entities.*;
import xreliquary.event.CommonEventHandler;
import xreliquary.init.XRRecipes;
import xreliquary.items.ItemDestructionCatalyst;
import xreliquary.lib.Names;
import xreliquary.lib.Reference;
import xreliquary.util.alkahestry.Alkahestry;

import java.util.ArrayList;

public class CommonProxy {


    public void preInit() {
        try {
            XRRecipes.init();
            Alkahestry.init();
        } catch (Exception e) {
            e.printStackTrace();
            FMLCommonHandler.instance().raiseException(e, "Reliquary failed to initiate recipies.", true);
        }

        FluidContainerRegistry.registerFluidContainer(new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME / 8), XRRecipes.potion(Reference.WATER_META), XRRecipes.potion(Reference.EMPTY_VIAL_META));
    }

    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(Reliquary.INSTANCE, new GUIHandler());
        FMLCommonHandler.instance().bus().register(new CommonEventHandler());
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

        this.registerEntities();
        this.registerTileEntities();
    }

    public void initOptions() {
        Reliquary.CONFIG.require(Names.fortune_coin, "disableAudio", new ConfigReference(false));
        Reliquary.CONFIG.require(Names.emperor_chalice, "multiplier", new ConfigReference(1).setMinimumValue(0));
        Reliquary.CONFIG.require(Names.alkahestry_tome, "redstoneLimit", new ConfigReference(256).setMinimumValue(0));
        Reliquary.CONFIG.require(Names.hero_medallion, "xpLevelCap", new ConfigReference(30).setMinimumValue(0));
        Reliquary.CONFIG.require(Names.twilight_cloak, "maxLightLevel", new ConfigReference(4).setMinimumValue(0).setMaximumValue(15));
        Reliquary.CONFIG.require(Names.handgun, "hudPosition", new ConfigReference(3).setMinimumValue(0).setMaximumValue(4));
        Reliquary.CONFIG.require(Names.sojourner_staff, "torches", new ConfigReference(new ArrayList<String>()));
        Reliquary.CONFIG.require(Names.destruction_catalyst, "mundane_blocks", new ConfigReference(new ArrayList<String>(ItemDestructionCatalyst.ids)));
        Reliquary.CONFIG.require(Names.destruction_catalyst, "cost", new ConfigReference(3).setMinimumValue(0));

        Reliquary.CONFIG.save();
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityAltar.class, "reliquaryAltar");
        // GameRegistry.registerTileEntity(TileEntityMortar.class, "apothecaryMortar");
        // GameRegistry.registerTileEntity(TileEntityCauldron.class, "reliquaryCauldron");
    }

    public void registerEntities() {
        EntityRegistry.registerModEntity(EntityHolyHandGrenade.class, "entityHGrenade", 0, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityGlowingWater.class, "entityHolyWater", 1, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntitySpecialSnowball.class, "entitySpecialSnowball", 2, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityNeutralShot.class, "entityNeutralShot", 3, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityExorcismShot.class, "entityExorcismShot", 4, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityBlazeShot.class, "entityBlazeShot", 5, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityEnderShot.class, "entityEnderShot", 6, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityConcussiveShot.class, "entityConcussiveShot", 7, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityBusterShot.class, "entityBusterShot", 8, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntitySeekerShot.class, "entitySeekerShot", 9, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntitySandShot.class, "entitySandShot", 10, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityStormShot.class, "entityStormShot", 11, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashAphrodite.class, "entitySplashAphrodite", 12, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashBlindness.class, "entitySplashBlindness", 13, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashConfusion.class, "entitySplashConfusion", 14, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashHarm.class, "entitySplashHarm", 15, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashPoison.class, "entitySplashPoison", 16, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashRuin.class, "entitySplashRuin", 17, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashSlowness.class, "entitySplashSlowness", 18, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashWeakness.class, "entitySplashWeakness", 19, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedSplashWither.class, "entitySplashWither", 20, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityCondensedFertility.class, "entitySplashFertility", 21, Reliquary.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntityKrakenSlime.class, "entityKSlime", 22, Reliquary.INSTANCE, 128, 5, true);
    }

}
