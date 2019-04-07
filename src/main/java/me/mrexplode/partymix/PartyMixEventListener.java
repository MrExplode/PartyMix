package me.mrexplode.partymix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PartyMixEventListener implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        System.out.println("[PartyMix] interact event");
        if (!e.hasItem()) return;
        ItemStack i = e.getItem();
        if (!i.getItemMeta().hasLore()) return;
        System.out.println("[PartyMix] the event has item with lore");
        if (i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() - 1).equalsIgnoreCase("PartyMix")) {
            PartyMixEvent pmEvent = new PartyMixEvent(e.getPlayer(), e.getAction(), i, i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() - 2));
            Bukkit.getPluginManager().callEvent(pmEvent);
            System.out.println("[PartyMix] te PMX event has been fired!");
        }
    }

}
