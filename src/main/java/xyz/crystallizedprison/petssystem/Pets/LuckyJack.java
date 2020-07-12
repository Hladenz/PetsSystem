package xyz.crystallizedprison.petssystem.Pets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class LuckyJack implements PetStructor {

    @Override
    public String ReturnName() {
        return "LuckyJack";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.LEGENDARY;
    }

    @Override
    public String ReturnDescription() {
        return "Doubles All Tokens you Get!";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.SHULKER_SPAWN_EGG;
    }

    @Override
    public void Equip(Player p) {

    }

    @Override
    public void UnEquip(Player p) {

    }
}
