package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class Manualshear implements Listener {

    @EventHandler
    public void shear(PlayerShearEntityEvent event){

        if(QuestscrollsCommandExecutor.isQuery){
            Zhu.SendManualObject(event.getPlayer(),"shear(剪子)",event.getEntity().getName().replace("§","&"));
        }

        new MainBusiness().Task("shear", event.getEntity().getName().replace("§","&"),event.getPlayer(),1);

    }

}
