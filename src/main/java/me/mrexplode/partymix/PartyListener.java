package me.mrexplode.partymix;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

/**
 * The listener instance for the PartyMixEvent.
 * 
 * @author <a href="https://mrexplode.github.io">MrExplode</a>
 *
 */
public abstract class PartyListener implements Listener {

    @EventHandler
    public abstract void onPartyUse(PartyMixEvent e);
    
    /**
     * 
     * @param a The Action
     * @return true when the action is right click. (block or air)
     */
    public boolean isRightClick(Action a) {
        return a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK;
    }
    
    /**
     * 
     * @param a The Action
     * @return true when the action is left click. (block or air)
     */
    public boolean isLeftClick(Action a) {
        return a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK;
    }
    
}
