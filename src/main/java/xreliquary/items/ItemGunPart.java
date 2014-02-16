package xreliquary.items;

import java.util.List;

import mods.themike.core.item.ItemBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import xreliquary.Reliquary;
import xreliquary.lib.Names;
import xreliquary.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGunPart extends ItemBase {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	protected ItemGunPart() {
		super(Reference.MOD_ID, Names.GUNPART_NAME);
		this.setCreativeTab(Reliquary.CREATIVE_TAB);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		canRepair = false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[3];
		for (int i = 0; i < 3; i++) {
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Names.GUNPART_NAME + i);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 3)
			return icons[meta];
		return icons[0];
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "item.gunPart0";
		case 1:
			return "item.gunPart1";
		case 2:
			return "item.gunPart2";
		default:
			return "item.gunPart0";
		}
	}
}
