package kamkeel.kingdomregions.client.gui;

import kamkeel.kingdomregions.client.player.InventoryKingdomPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class InventoryContainerEmpty extends Container

{
private Minecraft mc;

public InventoryContainerEmpty(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryKingdomPlayer inventoryCustom)

{
int i;
}


/**

 * This should always return true, since custom inventory can be accessed from anywhere

 */

@Override
public boolean canInteractWith(EntityPlayer player)

{

return true;

}
}