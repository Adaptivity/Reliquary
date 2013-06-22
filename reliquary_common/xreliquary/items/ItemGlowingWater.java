package xreliquary.items;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xreliquary.entities.EntityGlowingWater;
import xreliquary.lib.Names;

public class ItemGlowingWater extends ItemXR {
	protected ItemGlowingWater(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		canRepair = false;
		this.setUnlocalizedName(Names.GLOWING_WATER_NAME);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("Destroys undead. Glowifies stuff.");
		par3List.add("Used in many recipes.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par2World.isRemote) return par1ItemStack;
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		par2World.spawnEntityInWorld(new EntityGlowingWater(par2World, par3EntityPlayer));
		return par1ItemStack;
	}
}
