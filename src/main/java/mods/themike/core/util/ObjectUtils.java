package mods.themike.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * ItemBase, a helper class for blocks and items, but not manipulating objects themselves. Right now,
 * all it does it provide a clean way to get a block identifier (as in, minecraft:water).
 *
 * @author TheMike
 */
public class ObjectUtils {

    /**
     * Returns a block identifier. Examples of these are 'minecraft:water', 'xreliquary:lilypad', etc. Will return null
     * if the block itself is null.
     *
     * @param block
     *            The block to get the identifier from.
     */
    public static String getBlockIdentifier(Block block) {
        return block == null ? null : Block.blockRegistry.getNameForObject(block);
    }

    /**
     * Returns a item identifier. Examples of these are 'minecraft:water', 'xreliquary:lilypad', etc. Will return null
     * if the item itself is null.
     *
     * @param item
     *            The item to get the identifier from.
     */
    public static String getItemIdentifier(Item item) {
        return item == null ? null : Item.itemRegistry.getNameForObject(item);
    }

}
