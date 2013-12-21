package xreliquary.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import xreliquary.Reliquary;
import xreliquary.lib.Names;
import xreliquary.lib.Reference;
import xreliquary.util.LanguageHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagicbane extends ItemSword {

	protected ItemMagicbane(int par1) {
		super(par1, EnumToolMaterial.GOLD);
		this.setMaxDamage(16);
		this.setMaxStackSize(1);
		canRepair = true;
		this.setCreativeTab(Reliquary.CREATIVE_TAB);
		GameRegistry.registerItem(this, Reference.MOD_ID + ":" + Names.MAGICBANE_NAME);
		this.setUnlocalizedName(Names.MAGICBANE_NAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String formatter = LanguageHelper.getLocalization("item." + Names.MAGICBANE_NAME + ".tooltip");
		for (String result : formatter.split("\n"))
			par3List.add(result);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public float getDamageVsEntity(Entity par1Entity, ItemStack stack) {
		return super.getDamageVsEntity(par1Entity, stack);
	}

	/**
	 * Returns the strength of the stack against a given block. 1.0F base,
	 * (Quality+1)*2 if correct blocktype, 1.5F if sword
	 */
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 1.5F;
	}

	/**
	 * Current implementations of this method in child classes do not use the
	 * entry argument beside ev. They just raise the damage on the stack.
	 */
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		if (par2EntityLivingBase instanceof EntityLivingBase) {
			EntityLivingBase e = par2EntityLivingBase;
			int random = e.worldObj.rand.nextInt(16);
			switch (random) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				e.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 2));
			case 5:
			case 6:
			case 7:
			case 8:
				e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2));
				break;
			case 9:
			case 10:
			case 11:
				e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2));
				break;
			case 12:
			case 13:
				e.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 2));
				e.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 2));
				break;
			case 14:
				e.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 2));
				e.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 2));
				break;
			default:
				break;
			}
		}
		if (par3EntityLivingBase instanceof EntityPlayer) {
			NBTTagList enchants = par1ItemStack.getEnchantmentTagList();
			int bonus = 0;
			if (enchants != null) {
				for (int enchant = 0; enchant < enchants.tagCount(); enchant++) {
					bonus += ((NBTTagCompound) enchants.tagAt(enchant)).getShort("lvl");
				}
			}
			par2EntityLivingBase.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLivingBase), bonus + 4);
		}
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		return true;
	}
}
