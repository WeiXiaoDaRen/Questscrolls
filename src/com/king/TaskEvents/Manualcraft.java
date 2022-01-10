package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class Manualcraft implements Listener {

    @EventHandler
    public void craft(CraftItemEvent event){

        for (HumanEntity humanEntity : event.getInventory().getViewers()) { //获取能看到这个物品栏的玩家

            if(QuestscrollsCommandExecutor.isQuery){
                Zhu.SendManualObject(humanEntity.getKiller(),"craft(合成)", ToolClass.nmsitem(event.getCurrentItem()));
            }
            new MainBusiness().Task("put", ToolClass.nmsitem(event.getCurrentItem()),humanEntity.getKiller(),event.getRecipe().getResult().getAmount());
        }

    }

    }

