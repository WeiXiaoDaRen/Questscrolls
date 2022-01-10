package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Manualcommand implements Listener {

    public void command(PlayerCommandPreprocessEvent event){

        if(QuestscrollsCommandExecutor.isQuery){
            Zhu.SendManualObject(event.getPlayer(),"command(指令)", event.getMessage().replace("§","&"));
        }
        new MainBusiness().Task("command", event.getMessage().replace("§","&"), event.getPlayer(),1);


    }

}
