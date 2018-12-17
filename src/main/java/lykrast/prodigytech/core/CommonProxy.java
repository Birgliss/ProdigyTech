package lykrast.prodigytech.core;

import java.io.File;

import lykrast.prodigytech.common.capability.CapabilityHotAir;
import lykrast.prodigytech.common.gui.ProdigyTechGuiHandler;
import lykrast.prodigytech.common.recipe.HeatSawmillManager;
import lykrast.prodigytech.common.recipe.RotaryGrinderManager;
import lykrast.prodigytech.common.recipe.ZorraAltarManager;
import lykrast.prodigytech.common.util.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy {
	public static Configuration config;
	
	public SimpleNetworkWrapper createNetworkChannel() {
		SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(ProdigyTech.MODID);
		return channel;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "prodigy_tech.cfg"));
        Config.readConfig();
        
        NetworkRegistry.INSTANCE.registerGuiHandler(ProdigyTech.instance, new ProdigyTechGuiHandler());
        CapabilityHotAir.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		//ItemMysteryTreat.initEffects();
		ZorraAltarManager.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		//So it turns out there are several mods that do their oredict in Init like barbarians
		//So that got moved from the Manager's init in order to work
		HeatSawmillManager.INSTANCE.registerPlanks();
		RotaryGrinderManager.INSTANCE.registerOres();;
	}

}
