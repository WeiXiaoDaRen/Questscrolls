package com.king.TaskEvents;

import com.king.Zhu;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.MainBusiness;
import com.king.plugincore.ToolClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class Manualenchant implements Listener {

    @EventHandler
    public void enchant(EnchantItemEvent event){

        if(QuestscrollsCommandExecutor.isQuery) {
            Zhu.SendManualObject(event.getEnchanter(),"enchant(附魔)", ToolClass.nmsitem(event.getItem()));
        }

     new MainBusiness().Task("enchant", ToolClass.nmsitem(event.getItem()),event.getEnchanter(),1);

    }

}
