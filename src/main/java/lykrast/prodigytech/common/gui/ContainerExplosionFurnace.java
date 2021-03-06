package lykrast.prodigytech.common.gui;

import lykrast.prodigytech.common.recipe.ExplosionFurnaceManager;
import lykrast.prodigytech.common.tileentity.TileExplosionFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerExplosionFurnace extends ContainerMachine<TileExplosionFurnace> {
	public ContainerExplosionFurnace(InventoryPlayer userInv, TileExplosionFurnace tile) {
		super(tile);
		
		//Slot IDs
		//Tile - Explosives - Explosives 0	: 0
		//Tile - Explosives - Reactant 1	: 1
		//Tile - Input 2-4					: 2-4
		//Tile - Reagent 5					: 5
		//Tile - Output 6-8					: 6-8
		//Player - Inventory 9-35			: 9-35
		//Player - Hotbar 0-8				: 36-44
		
		//Explosion Furnace slots
		//Explosive
    	this.addSlotToContainer(new SlotExplosionFurnaceExplosive(tile, 0, 17, 26));
    	//Reactant
    	this.addSlotToContainer(new SlotExplosionFurnaceReactant(tile, 1, 17, 44));
		//Input
        for (int i = 0; i < 3; ++i)
        {
        	this.addSlotToContainer(new SlotExplosionFurnaceInput(tile, i + 2, 53, 17 + i * 18));
        }
        //Reagent
        this.addSlotToContainer(new SlotExplosionFurnaceReagent(tile, 5, 89, 17));
		//Output
        for (int i = 0; i < 3; ++i)
        {
        	this.addSlotToContainer(new SlotOutput(userInv.player, tile, i + 6, 125, 17 + i * 18));
        }

		//Player slots
		addPlayerSlotsDefault(userInv);
	}

	/**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //Item slots
            if (index < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            //Inventory slots
            else
            {
            	//Explosive
            	if (ExplosionFurnaceManager.isValidExplosive(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            	//Explosive reactant
            	else if (ExplosionFurnaceManager.isValidDampener(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            	//Input
            	else if (ExplosionFurnaceManager.isValidInput(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 2, 5, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            	//Reagent
            	else if (ExplosionFurnaceManager.isValidReagent(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 5, 6, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            	//Player inventory
                else if (index >= 9 && index < 36)
                {
                    if (!this.mergeItemStack(itemstack1, 36, 45, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 36 && index < 45 && !this.mergeItemStack(itemstack1, 9, 36, false))
                {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }
        
        return itemstack;
    }

}
