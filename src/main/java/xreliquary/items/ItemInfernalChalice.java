package xreliquary.items;

import lib.enderwizards.sandstone.init.ContentInit;
import lib.enderwizards.sandstone.items.ItemToggleable;
import xreliquary.Reliquary;
import xreliquary.lib.Names;

/**
 * Created by Xeno on 10/11/2014.
 */
@ContentInit
public class ItemInfernalChalice extends ItemToggleable {
    public ItemInfernalChalice() {
        super(Names.infernal_chalice);
        this.setCreativeTab(Reliquary.CREATIVE_TAB);
        this.setMaxDamage(513);
        this.setMaxStackSize(1);
        canRepair = false;
    }
}
