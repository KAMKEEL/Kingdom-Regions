package kamkeel.kingdomregions.client;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import kamkeel.kingdomregions.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {
	
    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
    	
     return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }

}