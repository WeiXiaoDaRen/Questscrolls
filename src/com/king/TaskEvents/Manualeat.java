package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Manualeat implements Listener {

    @EventHandler
    public void eat(PlayerItemConsumeEvent event){

        if(QuestscrollsCommandExecutor.isQuery){
            Zhu.SendManualObject(event.getPlayer(),"eat(食用)", ToolClass.nmsitem(event.getItem()));
        }

        new MainBusiness().Task("eat", ToolClass.nmsitem(event.getItem()),event.getPlayer(),1);

    }

}
