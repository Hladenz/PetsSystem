package xyz.crystallizedprison.petssystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.Pet;
import xyz.crystallizedprison.petssystem.PetsSystem;

import java.util.ArrayList;
import java.util.List;

public class Pets_Command implements CommandExecutor {

    PetsSystem main;

    public Pets_Command(PetsSystem main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = ((Player) sender).getPlayer();
            if (args.length != 0){
                if (args[0].toLowerCase().equals("unequip")){
                    PlayerInfo playerInfo = main.GetPlayerInfo(p);

                    if (playerInfo.getPet() == null){
                        p.sendMessage(ChatColor.DARK_AQUA +"You Don't Have Any Pet Equipped At the Moment");
                        return true;
                    }

                    Pet pet = playerInfo.getPet();
                    ItemStack petitem = main.ReturnPetObject(pet);

                    p.getInventory().addItem(petitem);
                    pet.pet.UnEquip(p);
                    playerInfo.setPet(null);
                    main.SavePlayerInformation(playerInfo);
                    p.sendMessage(ChatColor.DARK_AQUA+"Pet Unequipped!");

                }
            }else{
                PlayerInfo playerInfo = main.GetPlayerInfo(p);
                Pet pet = playerInfo.getPet();

                if (pet==null){
                    p.sendMessage(ChatColor.DARK_AQUA+"You don't Have a pet Equipped!");
                }else {
                    List<String> message = new ArrayList<>();
                    message.add(ChatColor.AQUA + "" + ChatColor.BOLD + "Current Pet: ");
                    message.add(ChatColor.AQUA + "" + ChatColor.BOLD + "NAME: " + ChatColor.DARK_AQUA + pet.pet.ReturnName());
                    message.add(ChatColor.AQUA + "" + ChatColor.BOLD + "TIER: " + ChatColor.DARK_AQUA + pet.pet.ReturnTier().Name);
                    message.add(ChatColor.AQUA + "" + ChatColor.BOLD + "DESCRIPTION: ");
                    message.add(ChatColor.DARK_AQUA + pet.pet.ReturnDescription());
                    p.sendMessage(message.toArray(new String[message.size()]));
                }
            }
        }
        return false;
    }
}
