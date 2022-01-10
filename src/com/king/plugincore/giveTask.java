package com.king.plugincore;

import com.king.Zhu;
import com.king.resource.Manuals;
import com.king.resource.ReadManual;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveTask {

    public static void give(String name, String s, CommandSender commandSender){

        //获取 Manual对象

        s = s.replace("&","§");

        if (ReadManual.manualList.keySet().contains(s)) {   //确定你输入的东西没问题

            Manuals manuals = ReadManual.manualList.get(s);

            if (manuals.giveItem(Zhu.giveplayer(name))) {

            }else{
                //玩家不在线则
                if(commandSender instanceof Player){
                    Zhu.senmessage((Player) commandSender,1);
                }else{
                    Zhu.mistake(6,"");
                }

            }

        }else{ //输入的任务不存在则

            if(commandSender instanceof Player){
                Zhu.senmessage((Player) commandSender,2);
            }else{
                Zhu.mistake(7,"");
            }

        }

    }

    public static void giveKit(String name, String s, CommandSender commandSender){

        s = s.replace("&","§");

        if (ReadManual.manualList.keySet().contains(s)) {   //确定你输入的东西没问题

            Manuals manuals = ReadManual.manualList.get(s);

            if(Zhu.IsOnline(Zhu.giveplayer(name))){

                Zhu.giveplayer(name).getInventory().addItem(manuals.giveKit()); //给予奖励卷轴

            }else{ //玩家不在线则

                if(commandSender instanceof Player){
                    Zhu.senmessage((Player) commandSender,1);
                }else{
                    Zhu.mistake(6,"");
                }

            }

        }else{ //输入的任务不存在则

            if(commandSender instanceof Player){
                Zhu.senmessage((Player) commandSender,2);
            }else{
                Zhu.mistake(7,"");
            }

        }

    }

}
