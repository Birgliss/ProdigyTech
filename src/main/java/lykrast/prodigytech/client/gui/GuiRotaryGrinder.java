package lykrast.prodigytech.client.gui;

import lykrast.prodigytech.common.gui.ContainerRotaryGrinder;
import lykrast.prodigytech.common.tileentity.TileRotaryGrinder;
import lykrast.prodigytech.common.util.Config;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.MathHelper;

public class GuiRotaryGrinder extends GuiHotAirMachineSimple {
	public GuiRotaryGrinder(InventoryPlayer playerInv, TileRotaryGrinder tile) {
		super(playerInv, tile, new ContainerRotaryGrinder(playerInv, tile), 80);
	}

	@Override
	protected int getProcessLeftScaled(int pixels)
    {
        int i = tile.getField(1);

        if (i == 0)
        {
            i = Config.rotaryGrinderProcessTime * 10;
        }
        
        int j = MathHelper.clamp(i - tile.getField(0), 0, i);

        return j * pixels / i;
    }

}
