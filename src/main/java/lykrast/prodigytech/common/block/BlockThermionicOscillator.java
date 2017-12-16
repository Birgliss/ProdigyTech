package lykrast.prodigytech.common.block;

import lykrast.prodigytech.common.item.ItemBlockMachineHotAir;
import lykrast.prodigytech.common.tileentity.TileThermionicOscillator;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockThermionicOscillator extends BlockHotAirMachine<TileThermionicOscillator> implements ICustomItemBlock {

    public BlockThermionicOscillator(float hardness, float resistance, int harvestLevel) {
		super(hardness, resistance, harvestLevel, TileThermionicOscillator.class);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		TileThermionicOscillator tile = new TileThermionicOscillator();
		tile.setFacing(EnumFacing.getHorizontal(meta & 7));
		return tile;
	}

	@Override
	public ItemBlock getItemBlock() {
		return new ItemBlockMachineHotAir(this, 125, 75);
	}

}