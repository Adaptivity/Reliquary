package xreliquary.blocks.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import xreliquary.lib.Reference;

import java.util.List;

public class TileEntityMortar extends TileEntity implements IInventory {
    //counts the number of times the player has right clicked the block
    //arbitrarily setting the number of times the player needs to grind the materials to five.
    private int pestleUsedCounter;
    private String customInventoryName;

    private ItemStack[] itemStacks;

	public TileEntityMortar() {
		pestleUsedCounter = 0;
        itemStacks = new ItemStack[4];
	}

	@Override
	public void updateEntity() {
        //do stuff on tick? I don't think we need this to tick.
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.itemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemStacks.length)
            {
                this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.pestleUsedCounter = par1NBTTagCompound.getShort("pestleUsed");

        if (par1NBTTagCompound.hasKey("CustomName", 8))
        {
            this.customInventoryName = par1NBTTagCompound.getString("CustomName");
        }
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("pestleUsed", (short)this.pestleUsedCounter);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.itemStacks.length; ++i)
        {
            if (this.itemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.itemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            par1NBTTagCompound.setString("CustomName", this.getInventoryName());
        }
	}

    //gets the contents of the tile entity as an array of itemstacks
    public ItemStack[] getItemStacks() {
        return itemStacks;
    }

    //increases the "pestleUsed" counter, checks to see if it is at its limit
    public void usePestle() {
        pestleUsedCounter++;

        if (pestleUsedCounter >= Reference.PESTLE_USAGE_MAX) {
            //do things
        }
    }

    @Override
    public int getSizeInventory() {
        return itemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return itemStacks[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (this.itemStacks[var1] != null)
        {
            ItemStack itemstack;

            if (this.itemStacks[var1].stackSize <= var2)
            {
                itemstack = this.itemStacks[var1];
                this.itemStacks[var1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.itemStacks[var1].splitStack(var2);

                if (this.itemStacks[var1].stackSize == 0)
                {
                    this.itemStacks[var1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if (this.itemStacks[var1] != null)
        {
            ItemStack itemstack = this.itemStacks[var1];
            this.itemStacks[var1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.itemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return customInventoryName;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return customInventoryName != null;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        //don't allow essence in slots after the second one.
        //eventually would be nice to replace this with something that was more elaborate about
        //allowing potions to mix, and rejecting items that aren't even ingredients.
        //isIngredient would be nice.
        return var1 >=  2 && isItemEssence(var2) ? false : true;
    }


    public boolean isItemEssence(ItemStack ist) {
        //essence not quite a thing just yet, force return true.
        return false;
    }
}
