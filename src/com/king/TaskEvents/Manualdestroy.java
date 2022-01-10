package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Manualdestroy implements Listener {

    // 破坏方块事件
    @EventHandler
    public void destroy(BlockBreakEvent event){

        if(QuestscrollsCommandExecutor.isQuery){
            Zhu.SendManualObject(event.getPlayer(),"destroy(破坏)",ToolClass.nmsblock(event.getBlock()));
        }
        new MainBusiness().Task("destroy", ToolClass.nmsblock(event.getBlock()),event.getPlayer(),1);

    }
}
