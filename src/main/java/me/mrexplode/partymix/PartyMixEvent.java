package me.mrexplode.partymix;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This is a kind of PlayerInteractEvent, when the PartyMix item is involved in it.
 * This event is Cancellable, but you have to check for it.
 * 
 * @author <a href="https://mrexplode.github.io">MrExplode</a>
 *
 */
public class PartyMixEvent extends PlayerEvent implements Cancellable {
    
    private HandlerList handlers = new HandlerList();
    private boolean cancelled;
    
    private Action action;
    private ItemStack item;
    private String partyItemIdentifier;
    
    public PartyMixEvent(final Player who, final Action action, final ItemStack item, final String identifier) {
        super(who);
        this.action = action;
        this.item = item;
        this.partyItemIdentifier = identifier;
    }
    
    /**
     * 
     * @return The action of this event
     */
    public Action getAction() {
        return this.action;
    }
    
    /**
     * 
     * @return The item involved in this event
     */
    public ItemStack getItem() {
        return this.item;
    }
    
    /**
     * Returns the unique name of the item. Not to be confused with {@link ItemMeta#getDisplayName()}
     * This name stands for the PartyMix item, and the actual displayname may be different from this.
     * 
     * @return the unique name
     */
    public String getIdentifier() {
        return this.partyItemIdentifier;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
