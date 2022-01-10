package com.king.plugincore;

import com.king.resource.ReadManual;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;


public class useReward implements Listener {

    @EventHandler
    public void use(PlayerInteractEvent event){

        //判断玩家动作 为 持道具右击空气
        if((event.getAction().name().equalsIgnoreCase("RIGHT_CLICK_AIR"))||(event.getAction().name().equalsIgnoreCase("RIGHT_CLICK_BLOCK"))) {

            //遍历已经成功配置的任务卷轴
            for (String name : ReadManual.manualList.keySet()){

                if(ReadManual.manualList.get(name).checkTaskName(event.getItem().getItemMeta().getDisplayName(), event.getPlayer())){
                    event.getPlayer().sendMessage("奖励都给到你了哦");
                }else {
                }

            }

        }
    }

}
