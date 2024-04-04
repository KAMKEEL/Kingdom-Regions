package kamkeel.kingdomregions;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import kamkeel.kingdomregions.client.ClientProxy;
import kamkeel.kingdomregions.client.gui.InterfaceGUI;
import kamkeel.kingdomregions.client.RegionEventHandler;
import kamkeel.kingdomregions.Network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import static kamkeel.kingdomregions.KingdomRegions.*;

@Mod(modid = MODID, name= NAME, version = VERSION)
public class KingdomRegions {
  public static final String MODID = "kingdomregions";
  public static final String NAME = "Kingdom | Regions";
  public static final String VERSION = "5.0";
  
  public static ModInteropProxy modInterop;
  
  public static KingdomRegions instance;
  
  public static Configuration config;
  
  @SidedProxy(clientSide = "kamkeel.kingdomregions.client.ClientProxy", serverSide = "kamkeel.kingdomregions.CommonProxy")
  public static CommonProxy proxy;
  
  public KingdomRegions() {
    instance = this;
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent preEvent) {
    config = new Configuration(preEvent.getSuggestedConfigurationFile());
    ConfigurationMoD.loadConfig();
    if (Loader.isModLoaded("VillageNames")) {
      try {
        modInterop = Class.forName("kamkeel.kingdomregions.ActiveModInteropProxy").<ModInteropProxy>asSubclass(ModInteropProxy.class).newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } 
    } else {
      modInterop = new DummyModInteropProxy();
    } 
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    new ItemRegistry();
    PacketDispatcher.registerPackets();
    RegionEventHandler events = new RegionEventHandler();
    FMLCommonHandler.instance().bus().register(events);
    MinecraftForge.EVENT_BUS.register(events);
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent postEvent) {
    if (FMLCommonHandler.instance().getEffectiveSide().isClient())
      MinecraftForge.EVENT_BUS.register(new InterfaceGUI(Minecraft.getMinecraft())); 
  }
  
  @EventHandler
  public void serverLoad(FMLServerStartingEvent event) {
    event.registerServerCommand(new CommandDisplayText());
    event.registerServerCommand(new CommandSetDisplayPoint());
  }
}
