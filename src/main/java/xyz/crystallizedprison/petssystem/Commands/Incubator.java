package xyz.crystallizedprison.petssystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.crystallizedprison.petssystem.Objects.Egg;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.PetsSystem;

import java.util.ArrayList;
import java.util.List;

public class Incubator implements CommandExecutor {

    PetsSystem main;

    public Incubator(PetsSystem main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            PlayerInfo playerInfo = main.GetPlayerInfo(player);
            if (args.length != 0){
                if (args[0].toLowerCase().equals("withdraw")){
                    Egg egg = playerInfo.getEgg();
                    ItemStack eggItem = main.ReturnEggObject(egg);

                    player.getInventory().addItem(eggItem);
                    playerInfo.setEgg(null);
                    main.SavePlayerInformation(playerInfo);
                    player.sendMessage(ChatColor.DARK_AQUA+"You Withdrawn Your Egg!");
                    return true;

                }else if (args[0].toLowerCase().equals("help")){
                    List<String> message = new ArrayList<>();
                    message.add(ChatColor.AQUA+"Help:");
                    message.add(ChatColor.AQUA+"withdraw " + ChatColor.DARK_AQUA +" - Withdraw Your Egg!");
                    player.sendMessage(message.toArray(new String[message.size()]));
                }else if (args[0].toLowerCase().equals("force")){
                    if (!player.hasPermission("petsystem.force")){
                        player.sendMessage(ChatColor.DARK_AQUA+"HAHAHAHHAH ofcourse you dont have perms to this");
                        return true;
                    }

                    playerInfo.getEgg().setCurrent(playerInfo.getEgg().getFinal() + 1);
                    main.SavePlayerInformation(playerInfo);

                }
            }else{
                if (playerInfo.getEgg() !=null){
                    List<String> message = new ArrayList<>();
                    Egg egg = playerInfo.getEgg();
                    message.add(ChatColor.AQUA+"Current Egg:");
                    message.add(ChatColor.AQUA+""+ChatColor.BOLD+"TIER: "+ChatColor.DARK_AQUA+egg.getTier().Name);
                    message.add(ChatColor.DARK_AQUA+""+egg.getFinal()+"/"+egg.getCurrent());
                    message.add(ChatColor.AQUA+"Do /incubator withdraw - To stop Incubating this Egg!");
                    player.sendMessage(message.toArray(new String[message.size()]));
                    return true;
                }else{
                    player.sendMessage(ChatColor.DARK_AQUA+"You Don't Have Any Eggs Incubating");
                    return true;
                }
            }
        }
        return false;
    }
}
