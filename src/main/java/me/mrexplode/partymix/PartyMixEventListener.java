package me.mrexplode.partymix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PartyMixEventListener implements Listener {
    
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        System.out.println("recipe: " + e.getRecipe().getResult().getType());
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        System.out.println("[PartyMix] interact event");
        System.out.println("[PartyMix] is item: " + e.hasItem());
        if (!e.hasItem()) return;
        ItemStack i = e.getItem();
        System.out.println("[PartyMix] item lore: " + i.getItemMeta().hasLore());
        if (!i.getItemMeta().hasLore()) return;
        System.out.println("[PartyMix] the event has item with lore");
        if (i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() - 1).equalsIgnoreCase("PartyMix")) {
            PartyMixEvent pmEvent = new PartyMixEvent(e.getPlayer(), e.getAction(), i, i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() - 2));
            Bukkit.getPluginManager().callEvent(pmEvent);
            System.out.println("[PartyMix] te PMX event has been fired!");
        }
    }

}
