package xyz.crystallizedprison.petssystem.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.crystallizedprison.petssystem.Objects.Egg;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.Objects.Tiers;
import xyz.crystallizedprison.petssystem.Pet;
import xyz.crystallizedprison.petssystem.PetsSystem;

import java.util.EventListener;

public class PlayerRightClick implements EventListener, Listener {

    PetsSystem main;

    public PlayerRightClick(PetsSystem main) {
        this.main = main;
    }

    @EventHandler(ignoreCancelled = true, priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack HeldItem = event.getPlayer().getInventory().getItemInMainHand();
        ItemMeta itemMeta = HeldItem.getItemMeta();
        Player player =event.getPlayer();
        if (itemMeta != null) {
            if (itemMeta.hasLore()) {
                if (itemMeta.getLore().get(itemMeta.getLore().size() - 1).contains("Right Click")) {
                    event.setCancelled(true);


                    if (itemMeta.getLore().get(itemMeta.getLore().size() - 1).equals(ChatColor.DARK_AQUA + "Right Click To Start Incubating")) {
                        Tiers tier = Tiers.valueOf(itemMeta.getLore().get(0).replace(ChatColor.AQUA + "" + ChatColor.BOLD + "TIER: " + ChatColor.DARK_AQUA, ""));
                        double current = Double.valueOf(itemMeta.getLore().get(1).split("/")[1]);

                        Egg egg = new Egg(tier, current, tier.BlocksMinedRequired);
                        PlayerInfo playerInfo = main.GetPlayerInfo(player);

                        HeldItem.setAmount(0);

                        if (playerInfo.getEgg() != null) {
                            Egg oldegg = playerInfo.getEgg();
                            ItemStack eggItem = main.ReturnEggObject(oldegg);

                            player.getInventory().addItem(eggItem);
                            player.sendMessage(ChatColor.DARK_AQUA + "You Withdrawn Your Egg!");
                        }

                        playerInfo.setEgg(egg);
                        main.SavePlayerInformation(playerInfo);
                        player.sendMessage(ChatColor.DARK_AQUA + "Put Egg into incubation");


                    } else if (itemMeta.getLore().get(itemMeta.getLore().size() - 1).equals(ChatColor.DARK_AQUA + "Right Click To Equip")) {
                        PlayerInfo playerInfo = main.GetPlayerInfo(player);

                        if (playerInfo.getPet() != null) {
                            player.sendMessage(ChatColor.DARK_AQUA + "You already have Egg Equipped. /pets UnEquip to UnEquip your current pet");
                            return;
                        }

                        for (Pet pet : Pet.values()) {
                            if (pet.pet.ReturnMaterial().equals(HeldItem.getType())) {
                                HeldItem.setAmount(0);
                                playerInfo.setPet(pet);
                                main.SavePlayerInformation(playerInfo);
                                playerInfo.getPet().pet.Equip(player);
                                player.sendMessage(ChatColor.AQUA + "Pet Equipped!");
                                break;
                            }
                        }
                    }


                }
            }
        }



    }
}
