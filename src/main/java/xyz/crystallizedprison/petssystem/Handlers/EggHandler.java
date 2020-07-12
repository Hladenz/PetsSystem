package xyz.crystallizedprison.petssystem.Handlers;

import me.clip.ezblocks.EZBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.Pet;
import xyz.crystallizedprison.petssystem.PetsSystem;

import java.math.BigDecimal;

public class EggHandler {

    PetsSystem main;

    public EggHandler(PetsSystem main) {
        this.main = main;
    }

    public void Loop(){
        BukkitScheduler scheduler = main.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(main, new Runnable() {

            @Override
            public void run() {
                for (Player p:Bukkit.getOnlinePlayers()){
                    PlayerInfo playerInfo = main.GetPlayerInfo(p);
                    if (playerInfo.getEgg() != null){

                        int increase = EZBlocks.getEZBlocks().getBlocksBroken(playerInfo.getPlayer()) -playerInfo.getLastClaimedBlockAmount();

                        playerInfo.setLastClaimedBlockAmount(increase + playerInfo.getLastClaimedBlockAmount());
                        playerInfo.getEgg().setCurrent(playerInfo.getEgg().getCurrent()+increase);
                        if (playerInfo.getEgg().getCurrent() >= playerInfo.getEgg().getFinal()){
                            Pet pet =main.GetPet(playerInfo.getEgg().getTier());
                            playerInfo.setEgg(null);
                            ItemStack petItem = main.ReturnPetObject(pet);
                            playerInfo.getPlayer().getInventory().addItem(petItem);
                            playerInfo.getPlayer().sendMessage(ChatColor.DARK_AQUA + "Your Egg Has just Hatched! You Got the " + pet.pet.ReturnName() + " Pet!");

                        }
                        main.SetPlayerInformation(playerInfo);
                    }else {
                        playerInfo.setLastClaimedBlockAmount(EZBlocks.getEZBlocks().getBlocksBroken(p));
                        main.SetPlayerInformation(playerInfo);
                    }
                }
                main.getData().save();
            }
        }, 0L, 20L);
    }
}
