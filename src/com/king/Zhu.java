package com.king;

import com.king.TaskEvents.*;
import com.king.command.QuestscrollsCommandExecutor;
import com.king.plugincore.PlayerManualQuantity;
import com.king.plugincore.QuestscrollsPapi;
import com.king.resource.ReadConfig;
import com.king.resource.ReadLanguage;
import com.king.resource.ReadManual;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

/*
* 转区只需要修改
*
*  1. Zhu
*  2. QuestscrollsCommandExecutor
*
* 里的固定字符 其余都在 language 里
* */

public class Zhu extends JavaPlugin {

    public static String Banben = "";

    public static HashMap<String,String> ExecuteTask = new HashMap<>();

    @Override
    public void onEnable() {

        //获取当前版本的 nms 字段
        String packet = Bukkit.getServer().getClass().getPackage().getName();
        Banben = packet.substring(packet.lastIndexOf('.') + 1);

        getLogger().info("插件加载中..." + Banben);

        getLogger().info("版本: 1.0 作者: BIDE");

        getLogger().info("正在创建各种文件...");

            PlayerManualQuantity.reloadPlayerManualQuantity(); //读取玩家数据文件

            saveDefaultConfig();                                      //创建配置文件

            File mulu = new File(getDataFolder(), "\\Tasks");   //创建 Tasks 目录

            if (!mulu.exists()) { //是否已存在?

                if(mulu.mkdirs()){

                    getLogger().info("创建文件夹成功");

                    InputStream input = this.getResource("task1.yml");

                    getLogger().info("系统自动生成一份可供参考的任务文件");

                    try {

                        OutputStream oput = new FileOutputStream(mulu.getPath()+"\\task1.yml");
                        byte[] ls = new byte[1024];
                        int a;

                        while(true){
                            assert input != null;
                            if (!((a = input.read(ls)) > 0)) break;

                            oput.write(ls,0,a);

                        }

                        input.close();
                        oput.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{

                    getLogger().info("创建文件夹失败！");

                }

            }




        getLogger().info("读取语言文件中");

            saveResource("language.yml", false);   //创建语言文件 不覆盖

            ReadLanguage.reloadLanguage();

        getLogger().info("读取完毕");

        getLogger().info("注册指令中");

            Objects.requireNonNull(this.getCommand("questscrolls")).setExecutor(new QuestscrollsCommandExecutor()); //注册指令

        getLogger().info("注册完毕");

        getLogger().info("读取配置文件中");

            new ReadConfig(getConfig());

        getLogger().info("读取完毕");

        getLogger().info("读取任务中...");

            new ReadManual(mulu.getPath());

        getLogger().info("读取完毕");

        getLogger().info("注册事件中...");

        Bukkit.getPluginManager().registerEvents(new Manualdestroy(), this); //玩家破坏物品事件
        Bukkit.getPluginManager().registerEvents(new Manualkill(), this); //玩家击杀事件
        Bukkit.getPluginManager().registerEvents(new Manualput(),this); //玩家放置事件
        Bukkit.getPluginManager().registerEvents(new Manualcraft(),this); //玩家合成事件
        Bukkit.getPluginManager().registerEvents(new Manualfish(),this); //玩家钓鱼事件
        Bukkit.getPluginManager().registerEvents(new Manualenchant(),this); //玩家附魔事件
        Bukkit.getPluginManager().registerEvents(new Manualeat(),this); //玩家食用
        Bukkit.getPluginManager().registerEvents(new Manualdamage(),this); // 损坏物品
        Bukkit.getPluginManager().registerEvents(new Manualcommand(),this); // 执行指令
        Bukkit.getPluginManager().registerEvents(new Manualshear(),this); // 剪刀
        Bukkit.getPluginManager().registerEvents(new Manualupgrade(),this);// 升级
        Bukkit.getPluginManager().registerEvents(new Manualspeak(),this); // 发言


        getLogger().info("注册完毕");

        if( Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){ //注册中

            getLogger().info("检测到前置插件 PlaceholderAPI ;");
            if(new QuestscrollsPapi().register()){
                getLogger().info("变量注册成功");
            }else{
                getLogger().info("变量注册失败！ 请联系作者！");
            }

        }else{
            getLogger().info("[非必要] 未安装前置 PlaceholderAPI");
            getLogger().info("无法使用变量功能");
        }

        getLogger().info("完毕 插件已加载完成");

    }

    @Override
    public void onDisable() {
        getLogger().info("插件卸载中...");

        getLogger().info("插件卸载完毕!");
        getLogger().info("欢迎你的下次使用~");
    }

    public static void sendMessage(CommandSender c, String s){

        if(c instanceof Player){
            c.sendMessage(s);
        }else{
            getPlugin(Zhu.class).getLogger().info(s);
        }

    }

    public static void mistake(int a,String b){

        switch (a){
            case 1:{
                getPlugin(Zhu.class).getLogger().info("错误！  =》 " +b+ " 《=");
                getPlugin(Zhu.class).getLogger().info("lore中必须包含 <d> 和 <m> !!");
                getPlugin(Zhu.class).getLogger().info("错误的任务lore不会加载！");
                getPlugin(Zhu.class).getLogger().info("");
                break;
            }
            case 2:{
                getPlugin(Zhu.class).getLogger().info("错误！  =》 " +b+ " 《=");
                getPlugin(Zhu.class).getLogger().info("<d> 必须位于 <m> 的后面！");
                getPlugin(Zhu.class).getLogger().info("错误的任务lore不会加载！");
                getPlugin(Zhu.class).getLogger().info("");
                break;
            }
            case 3:{
                getPlugin(Zhu.class).getLogger().info("此任务: "+b+" 剩余的任务数量");
                getPlugin(Zhu.class).getLogger().info("远远小于 此任务卷轴随机需要的数量");
                getPlugin(Zhu.class).getLogger().info("因此 此任务卷轴将不会被加载");
                getPlugin(Zhu.class).getLogger().info("");
                break;
            }
            case 4:{
                getPlugin(Zhu.class).getLogger().info("文件 "+b+" 的后缀名不为.yml");
                getPlugin(Zhu.class).getLogger().info("请不要混入奇怪的文件！");
                getPlugin(Zhu.class).getLogger().info("");
                break;
            }
            case 5:{
                getPlugin(Zhu.class).getLogger().info("任务卷轴 "+b+"的 Name 值");
                getPlugin(Zhu.class).getLogger().info("已在其他的 任务卷轴中 存在!");
                getPlugin(Zhu.class).getLogger().info("请不要在不同的卷轴中 用同样的Name值");
                getPlugin(Zhu.class).getLogger().info("");
                break;
            }
            case 6:{
                getPlugin(Zhu.class).getLogger().info("对应的玩家不在线！");
                getPlugin(Zhu.class).getLogger().info("");
                break;
            }
            case 7:{
                getPlugin(Zhu.class).getLogger().info("你输入的任务卷轴不存在");
                getPlugin(Zhu.class).getLogger().info("你可以使用 questscrolls listTask 显示可用卷轴");
                getPlugin(Zhu.class).getLogger().info("");
            }
            default:{
                break;
            }
        }


    }

    public static Player giveplayer(String name){

        return getPlugin(Zhu.class).getServer().getPlayer(name);

    }

    public static boolean IsOnline(Player player){

        return getPlugin(Zhu.class).getServer().getOnlinePlayers().contains(player);

    }

    public static void senmessage(Player player,int m){

        switch (m){
            case 1:{
                player.sendMessage("§c 玩家不在线或不存在!");
                break;
            }
            case 2:{
                player.sendMessage("§c不存在此任务！！");
                player.sendMessage("§c你可以使用 /questscrolls listTask 显示可用卷轴");
                break;
            }
            case 3:{
                player.sendMessage("§a 奖励已经发放给你了！");
            }
            default:{
                break;
            }
        }

    }

    public static void SendManualObject(Player player,String a,String b){

        player.sendMessage("§7你发生的行为" + a);
        player.sendMessage("§7所获取的对象是" + b);

    }

    public static FileConfiguration getconfig(){
        Zhu.getPlugin(Zhu.class).reloadConfig();
        return Zhu.getPlugin(Zhu.class).getConfig();
    }

}
