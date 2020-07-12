package xyz.crystallizedprison.petssystem;

import me.clip.ezblocks.EZBlocks;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.crystallizedprison.petssystem.Commands.Incubator;
import xyz.crystallizedprison.petssystem.Commands.Pets_Command;
import xyz.crystallizedprison.petssystem.Commands.Petsystem;
import xyz.crystallizedprison.petssystem.Configs.UserData;
import xyz.crystallizedprison.petssystem.Events.PlayerJoinEvent;
import xyz.crystallizedprison.petssystem.Events.PlayerLeaveEvent;
import xyz.crystallizedprison.petssystem.Events.PlayerRightClick;
import xyz.crystallizedprison.petssystem.Events.GamblerController;
import xyz.crystallizedprison.petssystem.Handlers.EggHandler;
import xyz.crystallizedprison.petssystem.Objects.Egg;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class PetsSystem extends JavaPlugin {

    UserData data = new UserData();
    HashMap<Player,PlayerInfo> PlayerList = new HashMap<>();
    EggHandler eggHandler = new EggHandler(this);

    public HashMap<Player,PlayerInfo> getPlayerList() {
        return PlayerList;
    }

    public UserData getData() {
        return data;
    }

    //TODO Add formating to Egg Required Since Legends Formate to E notation

    @Override
    public void onEnable() {
        // Plugin startup logic
        data.setup();

        getCommand("incubator").setExecutor(new Incubator(this));
        getCommand("pets").setExecutor(new Pets_Command(this));
        getCommand("petsystem").setExecutor(new Petsystem(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this),this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(this),this);
        getServer().getPluginManager().registerEvents(new PlayerRightClick(this),this);
        getServer().getPluginManager().registerEvents(new GamblerController(this),this);

        eggHandler.Loop();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public ItemStack ReturnPetObject(Pet pet){
        ItemStack petitem = new ItemStack(pet.pet.ReturnMaterial());
        ItemMeta itemMeta = petitem.getItemMeta();

        itemMeta.setDisplayName(ChatColor.DARK_AQUA+pet.pet.ReturnName() + " Pet");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA+""+ChatColor.BOLD+"NAME: "+ChatColor.DARK_AQUA+pet.pet.ReturnName());
        lore.add(ChatColor.AQUA+""+ChatColor.BOLD+"TIER: "+ChatColor.DARK_AQUA+pet.pet.ReturnTier().Name);
        lore.add(ChatColor.AQUA+""+ChatColor.BOLD+"DESCRIPTION: ");
        lore.add(ChatColor.DARK_AQUA+pet.pet.ReturnDescription());
        lore.add(ChatColor.DARK_AQUA+"Right Click To Equip");


        itemMeta.setLore(lore);
        petitem.setItemMeta(itemMeta);

        return petitem;

    }

    public ItemStack ReturnEggObject(Egg egg){
        ItemStack eggitem = new ItemStack(egg.getTier().EggType);
        ItemMeta itemMeta = eggitem.getItemMeta();

        itemMeta.setDisplayName(ChatColor.DARK_AQUA+egg.getTier().Name + " Egg");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA+""+ChatColor.BOLD+"TIER: "+ChatColor.DARK_AQUA+egg.getTier().Name);
        lore.add(ChatColor.DARK_AQUA+""+egg.getFinal()+"/"+egg.getCurrent());
        lore.add(ChatColor.DARK_AQUA+"Right Click To Start Incubating");

        itemMeta.setLore(lore);
        eggitem.setItemMeta(itemMeta);

        return eggitem;

    }



    public PlayerInfo GetPlayerInfo(Player player){
        if (data.get().contains("data."+player.getUniqueId().toString())){
            ConfigurationSection secton = data.get().getConfigurationSection("data."+player.getUniqueId().toString());
            int LastClaimedBlockAmount = secton.getInt("LastClaimedBlockAmount");

            Pet pet = null;
            if (secton.contains("Pet")){
                pet = Pet.valueOf(secton.getString("Pet"));
            }

            Egg egg = null;
            if (secton.contains("Egg")){
                egg = new Egg(Tiers.valueOf(secton.getString("Egg.Tier")),secton.getDouble("Egg.current"),secton.getDouble("Egg.Final"));
            }

            return new PlayerInfo(player,pet,LastClaimedBlockAmount,egg);

        }else{
            int LastClaimedBlockAmount = EZBlocks.getEZBlocks().getBlocksBroken(player);

            PlayerInfo playerInfo = new PlayerInfo(player,null,LastClaimedBlockAmount,null);
            SavePlayerInformation(playerInfo);
            return new PlayerInfo(player,null,LastClaimedBlockAmount,null);
        }
    }

    public void SavePlayerInformation(PlayerInfo p){

        data.get().set("data."+p.getPlayer().getUniqueId().toString(),null);
        ConfigurationSection secton= null;
        if (!data.get().contains("data."+p.getPlayer().getUniqueId().toString())){
            secton =data.get().createSection("data."+p.getPlayer().getUniqueId().toString());
        }else {
            secton = data.get().getConfigurationSection("data." + p.getPlayer().getUniqueId().toString());
        }

        secton.set("LastClaimedBlockAmount",p.getLastClaimedBlockAmount());

        if (p.getPet() != null) {
            secton.set("Pet", p.getPet().pet.ReturnName().toUpperCase());
        }

        if (p.getEgg() != null) {
            secton.set("Egg.Tier", p.getEgg().getTier().Name);
            secton.set("Egg.current", p.getEgg().getCurrent());
            secton.set("Egg.Final", p.getEgg().getFinal());
        }
        data.save();
    }

    public void SetPlayerInformation(PlayerInfo p){

        data.get().set("data."+p.getPlayer().getUniqueId().toString(),null);
        ConfigurationSection secton= null;
        if (!data.get().contains("data."+p.getPlayer().getUniqueId().toString())){
            secton =data.get().createSection("data."+p.getPlayer().getUniqueId().toString());
        }else {
            secton = data.get().getConfigurationSection("data." + p.getPlayer().getUniqueId().toString());
        }

        secton.set("LastClaimedBlockAmount",p.getLastClaimedBlockAmount());

        if (p.getPet() != null) {
            secton.set("Pet", p.getPet().pet.ReturnName().toUpperCase());
        }

        if (p.getEgg() != null) {
            secton.set("Egg.Tier", p.getEgg().getTier().Name);
            secton.set("Egg.current", p.getEgg().getCurrent());
            secton.set("Egg.Final", p.getEgg().getFinal());
        }
    }

    public Pet GetPet(Tiers tier){
        List<Pet> pets = new ArrayList<>();
        for (Pet pet:Pet.values()){
            if(pet.pet.ReturnTier().equals(tier)){
                pets.add(pet);
            }
        }
        Random rnd = new Random();
        return pets.get(rnd.nextInt(pets.size()));
    }


}
