package me.mrexplode.partymix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

/**
 * This is a command superclass, for handling commands by players.
 * By extending this class you can easily create a command, where you don't 
 * have check for permissions, CommandSender instances.<br>
 * When a command is dispatched with correct states (by a player, who have permission),
 * the {@link #commandDispatched(Player, String[])} method will be invooked.
 * 
 * @author <a href="https://mrexplode.github.io">MrExplode</a>
 *
 */
public abstract class AbstractPartyCommand extends BukkitCommand {
    
    public String identifier;
    
    public AbstractPartyCommand(String name, String description, String identifier) {
        this(name, description, "/" + name, identifier, null, new ArrayList<String>());
    }
    /**
     * 
     * @param name The name of the command.
     * @param description The description of the command.
     * @param usage The The usage of command. example: /command
     * @param identifier The item identifier.
     * @param permission Permission for command.
     * @param aliases The list of aliases.
     */
    public AbstractPartyCommand(String name, String description, String usage, String identifier, String permission, List<String> aliases) {
        super(name, description, usage, aliases);
        this.identifier = identifier;
        if (permission != null) {setPermission(permission);}
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission(this.getPermission())) {
                commandDispatched((Player) sender, args);
            } else {
                sender.sendMessage(ChatColor.RED + "[PartyMix] You don't have permission to perform this command!");
            }
        } else {
            sender.sendMessage("[PartyMix] This plugin is made for players!");
        }
        return true;
    }
    
    public abstract void commandDispatched(Player player, String[] args);
    
    /**
     * Gives an item to the specified player, with a message.
     * NOTE: "PartyMix" and the actual identifier will be added to the lore
     * 
     * @param player The player
     * @param item The item to give
     * @param message A message displayed after the player got the item
     * @param beforeGive A consumer to apply settings for the item
     */
    public void givePartyItem(Player player, Material item, String message, Consumer<ItemStack> beforeGive) {
        ItemStack itemStack = new ItemStack(item, 1);
        beforeGive.accept(itemStack);
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();
        lore.add(ChatColor.GOLD + identifier);
        lore.add(ChatColor.DARK_RED + "PartyMix");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        
        Map<Integer, ItemStack> unset = player.getInventory().addItem(itemStack);
        if (unset.isEmpty()) {
            player.sendMessage(message);
        } else {
            player.sendMessage(ChatColor.RED + "There are no empity slot in your inventory!");
        }
    }
}
