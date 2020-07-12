package xyz.crystallizedprison.petssystem.Objects;

import org.bukkit.Material;

public enum Tiers {
    COMMON (Material.ZOMBIE_VILLAGER_SPAWN_EGG,"COMMON",5000000),
    RARE (Material.ZOMBIE_SPAWN_EGG,"RARE",12000000),
    LEGENDARY (Material.BLAZE_SPAWN_EGG,"LEGENDARY",20000000);

    public Material EggType;
    public String Name;
    public int BlocksMinedRequired;

    Tiers(Material eggType, String name, int blocksMinedRequired) {
        EggType = eggType;
        Name = name;
        BlocksMinedRequired = blocksMinedRequired;
    }
}
