package me.mrexplode.partymix.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrexplode.partymix.AbstractPartyCommand;
import net.md_5.bungee.api.ChatColor;


public class LightningStickCommand extends AbstractPartyCommand {

    public LightningStickCommand(String name, String description, String usage, String identifier, String permission, List<String> aliases) {
        super(name, description, usage, identifier, permission, aliases);
    }

    @Override
    public void commandDispatched(Player player, String[] args) {
        givePartyItem(player, Material.STICK, "§aYou get a lightning stick!", (t) -> {
            ItemMeta meta = t.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "M" + ChatColor.LIGHT_PURPLE + "L" + ChatColor.GREEN + "G " + ChatColor.GOLD + "Sétapálca");
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            t.setItemMeta(meta);
        });
    }

}
