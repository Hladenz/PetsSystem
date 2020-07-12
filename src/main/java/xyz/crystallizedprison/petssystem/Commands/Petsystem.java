package xyz.crystallizedprison.petssystem.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.crystallizedprison.petssystem.Objects.Egg;
import xyz.crystallizedprison.petssystem.Objects.Tiers;
import xyz.crystallizedprison.petssystem.Pet;
import xyz.crystallizedprison.petssystem.PetsSystem;

public class Petsystem implements CommandExecutor {
    PetsSystem main;

    public Petsystem(PetsSystem main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = ((Player) sender).getPlayer();

            if (args.length !=0){
                if (args[0].equals("givePet")){

                    if (args.length > 3){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Format");
                        return true;
                    }

                    if (!p.hasPermission("PetsSystem.givePet")){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Permission");
                        return true;
                    }

                    if (Bukkit.getPlayer(args[2]) == null){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Player");
                        return true;
                    }

                    if (Pet.valueOf(args[1]) == null){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Pet");
                        return true;
                    }

                    Player rec = Bukkit.getPlayer(args[2]);

                    rec.getInventory().addItem(main.ReturnPetObject(Pet.valueOf(args[1])));
                    rec.sendMessage(ChatColor.AQUA + "You Have Just received a " + args[1] + " Pet!");
                    p.sendMessage(ChatColor.DARK_AQUA+"Pet Has Been Sent");
                    return true;

                }
                else if (args[0].equals("giveEgg")){
                    if (args.length > 3){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Format");
                        return true;
                    }

                    if (!p.hasPermission("PetsSystem.giveEgg")){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Permission");
                        return true;
                    }

                    if (Bukkit.getPlayer(args[2]) == null){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Player");
                        return true;
                    }

                    if (Tiers.valueOf(args[1]) == null){
                        p.sendMessage(ChatColor.DARK_AQUA+"Invalid Tier");
                        return true;
                    }

                    Player rec = Bukkit.getPlayer(args[2]);

                    rec.getInventory().addItem(main.ReturnEggObject( new Egg(Tiers.valueOf(args[1]),0,Tiers.valueOf(args[1]).BlocksMinedRequired)));
                    rec.sendMessage(ChatColor.AQUA + "You Have Just received a " + args[1] + " Tier Egg!");
                    p.sendMessage(ChatColor.DARK_AQUA+"Pet Has Been Sent");
                    return true;
                }
            }
        }else{
            if (args.length !=0){
                if (args[0].equals("givePet")){

                    if (args.length > 3){
                        System.out.println(ChatColor.DARK_AQUA+"Invalid Format");
                        return true;
                    }

                    if (Bukkit.getPlayer(args[2]) == null){
                        System.out.println(ChatColor.DARK_AQUA+"Invalid Player");
                        return true;
                    }

                    if (Pet.valueOf(args[1]) == null){
                        System.out.println(ChatColor.DARK_AQUA+"Invalid Pet");
                        return true;
                    }

                    Player rec = Bukkit.getPlayer(args[2]);

                    rec.getInventory().addItem(main.ReturnPetObject(Pet.valueOf(args[1])));
                    rec.sendMessage(ChatColor.AQUA + "You Have Just received a " + args[1] + " Pet!");
                    return true;
                }
                else if (args[0].equals("giveEgg")){
                    if (args.length > 3){
                        System.out.println(ChatColor.DARK_AQUA+"Invalid Format");
                        return true;
                    }

                    if (Bukkit.getPlayer(args[2]) == null){
                        System.out.println(ChatColor.DARK_AQUA+"Invalid Player");
                        return true;
                    }

                    if (Tiers.valueOf(args[1]) == null){
                        System.out.println(ChatColor.DARK_AQUA+"Invalid Tier");
                        return true;
                    }

                    Player rec = Bukkit.getPlayer(args[2]);

                    rec.getInventory().addItem(main.ReturnEggObject( new Egg(Tiers.valueOf(args[1]),0,Tiers.valueOf(args[1]).BlocksMinedRequired)));
                    rec.sendMessage(ChatColor.AQUA + "You Have Just received a " + args[1] + " Tier Egg!");
                    return true;
                }
            }

        }

        return false;
    }
}
