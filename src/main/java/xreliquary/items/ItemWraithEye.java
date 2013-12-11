package xreliquary.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import xreliquary.Reliquary;
import xreliquary.blocks.XRBlocks;
import xreliquary.common.TimeKeeperHandler;
import xreliquary.lib.Names;
import xreliquary.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWraithEye extends ItemSalamanderEye {

    protected ItemWraithEye(int par1) {
        super(par1);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        canRepair = false;
        this.setCreativeTab(Reliquary.tabsXR);
        this.setUnlocalizedName(Names.WRAITH_EYE_NAME);
    }

    @SideOnly(Side.CLIENT)
    private Icon iconOverlay[];

    @SideOnly(Side.CLIENT)
    private Icon iconBase;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconOverlay = new Icon[4];
        iconBase = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                + ":" + Names.WRAITH_EYE_NAME);        
        for (int i = 0; i < 4; i++) {
            iconOverlay[i] = iconRegister.registerIcon(Reference.MOD_ID
                    .toLowerCase() + ":" + Names.WRAITH_EYE_OVERLAY_NAME + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public Icon getIcon(ItemStack itemStack, int renderPass) {
        if (renderPass != 1)
            return iconBase;
        else {
            int i = TimeKeeperHandler.getTime();
            i %= 80;
            if (i < 7) {
                // i == 0, open, i == 3, closed.
                if (i > 2) {
                    i = 6 - i;
                }
                return iconOverlay[i];
            } else
                // base - completely open
                return iconBase;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack ist, World world,
            EntityPlayer player) {
        // teleport player to the wraith node, if it's possible to.
        if (world.getBlockId(getShort("nodeX" + getWorld(player), ist), getShort("nodeY" + getWorld(player), ist),
                getShort("nodeZ" + getWorld(player), ist)) == XRBlocks.wraithNode.blockID) {
            if (findAndRemoveEnderPearl(player)) {
                attemptTeleport(world, getShort("nodeX" + getWorld(player), ist),
                        getShort("nodeY" + getWorld(player), ist), getShort("nodeZ" + getWorld(player), ist), player);
            }
        }
        return ist;
    }

    private boolean findAndRemoveEnderPearl(EntityPlayer player) {
        if (player.capabilities.isCreativeMode)
            return true;
        for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
            if (player.inventory.mainInventory[slot] == null) {
                continue;
            }
            if (player.inventory.mainInventory[slot].getItem() == Item.enderPearl) {
                player.inventory.decrStackSize(slot, 1);
                return true;
            }
        }
        return false;
    }

    private boolean attemptTeleport(World world, int x, int y, int z,
            EntityPlayer player) {
        for (int xD = -2; xD <= 2; xD++) {
            for (int yD = -1; yD <= 1; yD++) {
                for (int zD = -2; zD <= 2; zD++) {
                    if (world.getBlockId(x + xD, y + yD, z + zD) == 0
                            && world.getBlockId(x + xD, y + yD + 1, z + zD) == 0) {
                        player.setPositionAndUpdate(x + xD, y + yD, z + zD);
                        for (int particles = 0; particles < 2; particles++) {
                            world.spawnParticle("portal", player.posX,
                                    player.posY, player.posZ,
                                    world.rand.nextGaussian(),
                                    world.rand.nextGaussian(),
                                    world.rand.nextGaussian());
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack eye, EntityPlayer par2EntityPlayer,
            List par3List, boolean par4) {
        par3List.add("Right clicking a Wraith Node binds to it");
        par3List.add("allowing you to return to it at will,");
        par3List.add("for the cost of an Ender Pearl.");
        String details = EnumChatFormatting.DARK_PURPLE + "Currently bound to ";
        if (par2EntityPlayer.worldObj.getBlockId(getShort("nodeX" + getWorld(par2EntityPlayer), eye),
                getShort("nodeY" + getWorld(par2EntityPlayer), eye), getShort("nodeZ" + getWorld(par2EntityPlayer), eye)) != XRBlocks.wraithNode.blockID) {
            details += "nowhere.";
        } else {
            details += "X: " + getShort("nodeX" + getWorld(par2EntityPlayer), eye) + " Y: "
                    + getShort("nodeY" + getWorld(par2EntityPlayer), eye) + " Z: " + getShort("nodeZ" + getWorld(par2EntityPlayer), eye);
        }
        par3List.add(details);
    }

    @Override
    public boolean onItemUse(ItemStack ist, EntityPlayer player, World world,
            int x, int y, int z, int side, float xOff, float yOff, float zOff) {
        // if right clicking on a wraith node, bind the eye to that wraith node.
        if (world.getBlockId(x, y, z) == XRBlocks.wraithNode.blockID) {
            setWraithNode(ist, x, y, z, player);
            return true;
        } else {
            onItemRightClick(ist, world, player);
            return false;
        }
    }

    public void setWraithNode(ItemStack eye, int x, int y, int z, EntityPlayer player) {
        setShort("nodeX" + getWorld(player), eye, x);
        setShort("nodeY" + getWorld(player), eye, y);
        setShort("nodeZ" + getWorld(player), eye, z);
    }
    
    public String getWorld(EntityPlayer player) {
        return Integer.valueOf(player.worldObj.provider.dimensionId).toString();
    }
}
