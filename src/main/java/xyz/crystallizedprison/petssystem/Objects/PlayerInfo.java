package xyz.crystallizedprison.petssystem.Objects;

import org.bukkit.entity.Player;
import xyz.crystallizedprison.petssystem.Pet;

public class PlayerInfo {

    private final Player player;
    private Pet pet;
    private int LastClaimedBlockAmount;
    private Egg egg;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getLastClaimedBlockAmount() {
        return LastClaimedBlockAmount;
    }

    public void setLastClaimedBlockAmount(int lastClaimedBlockAmount) {
        LastClaimedBlockAmount = lastClaimedBlockAmount;
    }

    public Egg getEgg() {
        return egg;
    }

    public void setEgg(Egg egg) {
        this.egg = egg;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerInfo(Player player, Pet pet, int lastClaimedBlockAmount, Egg egg) {
        this.player = player;
        this.pet = pet;
        LastClaimedBlockAmount = lastClaimedBlockAmount;
        this.egg = egg;
    }
}
