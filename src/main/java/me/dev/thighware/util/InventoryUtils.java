package me.dev.thighware.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketHeldItemChange;

import javax.annotation.Nonnull;
import java.util.List;

public class InventoryUtils implements Util{

    public static void switchToHotbarSlot(int slot, boolean silent) {
        if (InventoryUtils.mc.player.inventory.currentItem == slot || slot < 0) {
            return;
        }
        if (silent) {
            InventoryUtils.mc.player.connection.sendPacket(new CPacketHeldItemChange(slot));
            InventoryUtils.mc.playerController.updateController();
        } else {
            InventoryUtils.mc.player.connection.sendPacket(new CPacketHeldItemChange(slot));
            InventoryUtils.mc.player.inventory.currentItem = slot;
            InventoryUtils.mc.playerController.updateController();
        }
    }


    public static int findHotbarBlock(Class clazz) {
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack stack = InventoryUtils.mc.player.inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY) continue;
            if (clazz.isInstance(stack.getItem())) {
                return i;
            }
            if (!(stack.getItem() instanceof ItemBlock) || !clazz.isInstance(block = ((ItemBlock) stack.getItem()).getBlock()))
                continue;
            return i;
        }
        return -1;
    }

    public static int findObsidianSlot(boolean offHandActived, boolean activeBefore) {
        int slot = -1;
        List<ItemStack> mainInventory = mc.player.inventory.mainInventory;

        if (offHandActived ) {
            return 9;
        }

        for (int i = 0; i < 9; i++) {
            ItemStack stack = mainInventory.get(i);

            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock)) {
                continue;
            }

            Block block = ((ItemBlock) stack.getItem()).getBlock();
            if (block instanceof BlockObsidian) {
                slot = i;
                break;
            }
        }
        return slot;
    }

    public static int getItemFromHotbar(Item item) {
        int slot = -1;

        for (int i = 8; i >= 0; --i) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == item) {
                slot = i;
                break;
            }
        }

        return slot;
    }

    public static int getItemSlot(final Item input) {
        if (input == mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 36; i >= 0; --i) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if (item == input) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }

    public static void swapItemsOffhand(final int slot) {
        if (slot == -1) {
            return;
        }
        mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        mc.playerController.updateController();
    }
    public static boolean isHolding(@Nonnull final Class<?> item) {
        return item.isInstance(mc.player.getHeldItemMainhand().getItem()) || item.isInstance(mc.player.getHeldItemOffhand().getItem());
    }
}
