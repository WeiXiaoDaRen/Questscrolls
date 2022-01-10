package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Manualspeak implements Listener {

    @EventHandler
    public void Speak(AsyncPlayerChatEvent event){

        if(QuestscrollsCommandExecutor.isQuery){
            Zhu.SendManualObject(event.getPlayer(),"speak(说话)", event.getMessage());
        }
        new MainBusiness().Task("speak", event.getMessage(),event.getPlayer(),1);

    }

}
