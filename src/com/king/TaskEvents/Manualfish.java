package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Manualfish implements Listener {

    @EventHandler
    public void fish(PlayerFishEvent event){

        if(event.getState().equals(PlayerFishEvent.State.valueOf("CAUGHT_FISH"))){

            if(event.getCaught() != null) {

                ItemStack e = ((Item)event.getCaught()).getItemStack();
                if(QuestscrollsCommandExecutor.isQuery){
                    Zhu.SendManualObject(event.getPlayer(),"fish(钓鱼)", ToolClass.nmsitem(e));
                }
                new MainBusiness().Task("fish", ToolClass.nmsitem(e), event.getPlayer(), 1);
            }
        }

    }

}
