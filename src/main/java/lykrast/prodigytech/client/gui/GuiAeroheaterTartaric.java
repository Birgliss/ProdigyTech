package lykrast.prodigytech.client.gui;

import com.google.common.collect.ImmutableList;

import lykrast.prodigytech.common.gui.ContainerAeroheaterTartaric;
import lykrast.prodigytech.common.tileentity.TileAeroheaterTartaric;
import lykrast.prodigytech.common.util.Config;
import lykrast.prodigytech.common.util.TooltipUtil;
import lykrast.prodigytech.core.ProdigyTech;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiAeroheaterTartaric extends GuiInventory {
	public static final ResourceLocation GUI = ProdigyTech.resource("textures/gui/tartaric_aeroheater.png");
    private final IInventory playerInventory;
    private final TileAeroheaterTartaric tile;

	public GuiAeroheaterTartaric(InventoryPlayer playerInv, TileAeroheaterTartaric tile) {
		super(new ContainerAeroheaterTartaric(playerInv, tile));
		
		playerInventory = playerInv;
		this.tile = tile;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (TileAeroheaterTartaric.isBurningFuel(tile))
        {
            int k = getFuelBurnLeftScaled(13);
            drawTexturedModalRect(guiLeft + 72, guiTop + 37 + (13 - k), 176, (13 - k), 14, k + 1);
        }
        if (TileAeroheaterTartaric.isBurningStoker(tile))
        {
            int k = getStokerBurnLeftScaled(13);
            drawTexturedModalRect(guiLeft + 90, guiTop + 37 + (13 - k), 176, (13 - k), 14, k + 1);
        }

        int l = getTemperatureScaled(17, 30, 1000);
        this.drawTexturedModalRect(guiLeft + 79, guiTop + 16 + (17 - l), 176, 14 + (17 - l), 18, l + 1);
	}

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = tile.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    private int getTemperatureScaled(int pixels, int min, int max) {
        int temp = MathHelper.clamp(tile.getField(2), min, max) - min;
        int interval = max - min;
        return temp != 0 && interval != 0 ? temp * pixels / interval : 0;
    }

    private int getFuelBurnLeftScaled(int pixels) {
        int i = tile.getField(1);
        if (i == 0) i = 200;
        return tile.getField(0) * pixels / i;
    }

    private int getStokerBurnLeftScaled(int pixels) {
        return Math.min(tile.getField(3) * pixels / Config.tartaricStokerTime, pixels);
    }

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderTemperatureToolTip(mouseX, mouseY);
	}

    private void renderTemperatureToolTip(int x, int y) {
        if (x >= guiLeft + 79 && x < guiLeft + 97 && y >= guiTop + 16 && y < guiTop + 34)
        {
        	String tooltip = I18n.format(TooltipUtil.TEMPERATURE_OUT, tile.getField(2));
            this.drawHoveringText(ImmutableList.of(tooltip), x, y, fontRenderer);
        }
    }

}
