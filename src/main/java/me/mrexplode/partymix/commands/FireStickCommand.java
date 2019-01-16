package me.mrexplode.partymix.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrexplode.partymix.AbstractPartyCommand;
import net.md_5.bungee.api.ChatColor;

/**
 * Command for getting fireball shooting stick
 * 
 * @author MrExplode
 *
 */
public class FireStickCommand extends AbstractPartyCommand {

    public FireStickCommand(String name, String description, String usage, String identifier, String permission, List<String> aliases) {
        super(name, description, usage, identifier, permission, aliases);
    }

    @Override
    public void commandDispatched(Player player, String[] args) {
        givePartyItem(player, Material.STICK, ChatColor.AQUA + "§aYou get a fireshooter!", (t) -> {
            ItemMeta meta = t.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_RED + "Fire" + ChatColor.GOLD + "Burn");
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            t.setItemMeta(meta);
        });
    }
}
