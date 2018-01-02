package lykrast.prodigytech.client.gui;

import lykrast.prodigytech.common.gui.ContainerLinearExtractor;
import lykrast.prodigytech.common.tileentity.TileLinearExtractor;
import lykrast.prodigytech.core.ProdigyTech;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiLinearExtractor extends GuiInventory {
	public static final ResourceLocation GUI = new ResourceLocation(ProdigyTech.MODID, "textures/gui/linear_extractor.png");
    private final IInventory playerInventory;
    private final TileLinearExtractor tile;

	public GuiLinearExtractor(InventoryPlayer playerInv, TileLinearExtractor tile) {
		super(new ContainerLinearExtractor(playerInv, tile));
		
		playerInventory = playerInv;
		this.tile = tile;
		
		this.xSize = 176;
		this.ySize = 133;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(GUI);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = tile.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

}
