package me.mrexplode.partymix.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrexplode.partymix.PartyListener;
import me.mrexplode.partymix.PartyMixEvent;

/**
 * Listener for fireball shooting stick
 * 
 * @author MrExplode
 *
 */
public class FireStickListener extends PartyListener {

    @Override
    public void onPartyUse(PartyMixEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        if (item.getType().equals(Material.STICK) && e.getIdentifier().equalsIgnoreCase("fireburn") && isRightClick(e.getAction())) {
            player.launchProjectile(Fireball.class);
        }
    }

}
