package kamkeel.kingdomregions;

import cpw.mods.fml.common.network.IGuiHandler;
import kamkeel.kingdomregions.client.player.InventoryKingdomPlayer;
import kamkeel.kingdomregions.client.gui.InventoryContainerEmpty;
import kamkeel.kingdomregions.client.gui.RuneStoneGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == 55)
      return new InventoryContainerEmpty(player, player.inventory, new InventoryKingdomPlayer());
    return null;
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == 55)
      return new RuneStoneGUI(player, player.inventory, new InventoryKingdomPlayer());
    return null;
  }
}
