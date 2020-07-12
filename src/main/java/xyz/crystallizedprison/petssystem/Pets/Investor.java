package xyz.crystallizedprison.petssystem.Pets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class Investor implements PetStructor {

    @Override
    public String ReturnName() {
        return "Investor";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.LEGENDARY;
    }

    @Override
    public String ReturnDescription() {
        return "Doubles Generator Output while your Online!";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.CHICKEN_SPAWN_EGG;
    }

    @Override
    public void Equip(Player p) {

    }

    @Override
    public void UnEquip(Player p) {

    }
}
