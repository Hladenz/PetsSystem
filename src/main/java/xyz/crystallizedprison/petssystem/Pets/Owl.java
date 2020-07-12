package xyz.crystallizedprison.petssystem.Pets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class Owl implements PetStructor {

    @Override
    public String ReturnName() {
        return "Owl";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.COMMON;
    }

    @Override
    public String ReturnDescription() {
        return "When Equipped it give You Night Vision";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.VILLAGER_SPAWN_EGG;
    }

    @Override
    public void Equip(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, true, false));
    }

    @Override
    public void UnEquip(Player p) {
        p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
}
