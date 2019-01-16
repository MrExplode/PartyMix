package me.mrexplode.partymix.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrexplode.partymix.PartyListener;
import me.mrexplode.partymix.PartyMixEvent;


public class LightningStickListener extends PartyListener {

    @Override
    public void onPartyUse(PartyMixEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        if (item.getType().equals(Material.STICK) && e.getIdentifier().equalsIgnoreCase("god's fury") && isRightClick(e.getAction())) {
            Location loc = player.getTargetBlock(null, 120).getLocation();
            player.getWorld().strikeLightning(loc);
            
            for (Entity entity :  player.getWorld().getNearbyEntities(loc, 1.5, 4, 1.5)) {
                Damageable d = (Damageable) entity;
                d.damage(1, player);
            }
        }
    }
}
