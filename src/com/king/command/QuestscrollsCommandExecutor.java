package com.king.command;

/*
 *  /Questscrolls give <TaskName> <player>   --  给予玩家任务卷轴
 *  /Questscrolls finish <player>            --  完成玩家背包中的任务卷轴 (1个)
 *  /Questscrolls reward <player>            --  给予玩家完成状态下的卷轴 (1个)
 *  /Questscrolls listTask                   --  查看已经加载成功的任务数量
 *  /Questscrolls query                      --  进入调试模式
 *  /Questscrolls reload                     --  重载配置文件
 * */

import com.king.Zhu;
import com.king.plugincore.giveTask;
import com.king.resource.ReadConfig;
import com.king.resource.ReadLanguage;
import com.king.resource.ReadManual;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuestscrollsCommandExecutor implements CommandExecutor, TabExecutor {

    public static boolean isQuery = false;

    public String[] commandss = {"give","reward","listTask","query","reload","help"};  //代表着指令的数组 用于指令补全

    //指令的主要方法
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!commandSender.hasPermission("taskmanual.set")){
            sendmessage(ReadLanguage.Insufficient_permissions,commandSender);
            return true;
        }

        if(strings.length < 4 && strings.length > 0){ //首先 指令的自变量小于4 如果大于4 则一定是错误的所以不继续判定

            switch (strings[0]){    //开始对指令进行处理

                case "help": {  //帮助文档

                    Zhu.sendMessage(commandSender,"[Questscrolls - 1.0] 帮助文档");
                    Zhu.sendMessage(commandSender,"§a/questscrolls give <TaskName> <player>   §7--  给予玩家任务卷轴");
                    Zhu.sendMessage(commandSender,"§a/questscrolls reward <TaskName> <player> §7--  给予玩家奖励卷轴");
                    Zhu.sendMessage(commandSender,"§a/questscrolls listTask                   §7--  可用的任务卷轴");
                    Zhu.sendMessage(commandSender,"§a/questscrolls query                      §7--  进入调试模式");
                    Zhu.sendMessage(commandSender,"§a/questscrolls reload                     §7--  重载配置文件");

                    break;

                }

                case "give":{  //给予卷轴
                    if(strings.length < 3){
                        Zhu.sendMessage(commandSender, ReadLanguage.Format_Incorrect);
                        break;
                    }
                    giveTask.give(strings[2],strings[1],commandSender);
                    break;

                }

                case "reward":{ //给予奖励卷轴
                    if(strings.length < 3){
                        Zhu.sendMessage(commandSender, ReadLanguage.Format_Incorrect);
                        break;
                    }
                    giveTask.giveKit(strings[2],strings[1],commandSender);
                    break;

                }

                case "listTask":{ //显示加载成功的任务

                    sendmessage("§a 可用的任务如下",commandSender);

                    for (String s1 : ReadManual.manualList.keySet()) {

                        sendmessage(s1.replaceAll("§","&"),commandSender);

                    }
                    break;
                }

                case "query": { //开关调试模式

                    if(isQuery){
                        sendmessage("§c 调试模式已关闭",commandSender);
                    }else{
                        sendmessage("§a 调试模式已开启",commandSender);
                    }
                    isQuery = !isQuery;

                    break;

                }

                case "reload": { //重载配置文件

                    sendmessage("§a重载中...",commandSender);
                    ReadLanguage.reloadLanguage();
                    ReadManual.manualList.clear();
                    ReadManual.reloadManual();
                    new ReadConfig(Zhu.getconfig());
                    sendmessage("§a重载完毕!",commandSender);
                    sendmessage("§c出于安全性 不会重载( playerdata.yml )",commandSender);
                    break;
                }

                default:{ //都不是则

                    Zhu.sendMessage(commandSender, ReadLanguage.Format_Incorrect);
                    Zhu.sendMessage(commandSender,"§c/Questscrolls help");
                    break;

                }

            }
            return true;

        }
        Zhu.sendMessage(commandSender, ReadLanguage.Format_Incorrect);
        Zhu.sendMessage(commandSender,"/Questscrolls help");
        return true;
    }


    //指令补全区
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {


        if (strings.length > 1) return Collections.singletonList(commandSender.getName()); //如果你已经输入了则返回空列表

        if (strings.length == 0) return Arrays.asList(commandss); //如果只输入了 指令头 则返回所有的子命令

        return Arrays.stream(commandss).filter(S -> S.startsWith(strings[0])).collect(Collectors.toList());

    }

    public static void sendmessage(String s,CommandSender commandSender){

        if(commandSender instanceof Player){
            commandSender.sendMessage(s);
        }else{
            commandSender.getServer().getLogger().info(s);
        }

    }
}
