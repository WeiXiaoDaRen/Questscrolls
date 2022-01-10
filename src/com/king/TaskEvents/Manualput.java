package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Manualput implements Listener {

    @EventHandler
    public void put(BlockPlaceEvent event){

        if(QuestscrollsCommandExecutor.isQuery){
            Zhu.SendManualObject(event.getPlayer(),"put(放置)", ToolClass.nmsitem(event.getItemInHand()));
        }
        new MainBusiness().Task("put", ToolClass.nmsitem(event.getItemInHand()),event.getPlayer(),1);

    }

}
