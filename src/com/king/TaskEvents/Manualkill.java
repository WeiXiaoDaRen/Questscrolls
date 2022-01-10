package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Manualkill implements Listener {
    //击杀任务
    @EventHandler
    public void kill(EntityDeathEvent event){
        if(event.getEntity().getKiller() != null){ //有击杀者 (不是被火烧死 或者 怎么样的)
                if (QuestscrollsCommandExecutor.isQuery) {
                    Zhu.SendManualObject(event.getEntity().getKiller(), "kill(击杀)", event.getEntity().getName().replace("§", "&"));
                }
                new MainBusiness().Task("kill", event.getEntity().getName().replace("§", "&"), event.getEntity().getKiller(), 1);

        }
    }

}
