package me.mrexplode.partymix;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.mrexplode.partymix.commands.FireStickCommand;
import me.mrexplode.partymix.commands.LightningStickCommand;
import me.mrexplode.partymix.commands.MageCommand;
import me.mrexplode.partymix.listeners.FireStickListener;
import me.mrexplode.partymix.listeners.LightningStickListener;
import me.mrexplode.partymix.listeners.MageListener;

public class PartyMix extends JavaPlugin {
    
    public Logger logger;
    public static Plugin p;
    
    /**
     * The set of the Commands
     */
    public HashSet<AbstractPartyCommand> commands = new HashSet<AbstractPartyCommand>();
    
    /**
     * The set of the Listeners
     */
    public HashSet<PartyListener> listeners = new HashSet<PartyListener>();
    
    @Override
    public void onEnable() {
        p = this;
        logger = getLogger();
        
        getServer().getPluginManager().registerEvents(new PartyMixEventListener(), p);
        addListeners();
        addCommands();
        register();
    }
    
    @Override
    public void onDisable() {
    }
    
    /**
     * Adds all commands to the {@link PartyMix#commands} set
     */
    private void addCommands() {
        commands.add(new FireStickCommand("firestick", "Gives you Harry Potter's stick",
                "/firestick", "Fireburn", "partymix.firestick", new ArrayList<String>()));
        commands.add(new LightningStickCommand("lightningstick", "Gives you a stick with the power of gods", 
                "/lightningstick", "God's Fury", "partymix.lightningstick", new ArrayList<String>()));
        commands.add(new MageCommand("mage", "Gives you a Magical totem", "/mage", "Eye of Truth", "partymix.mage", new ArrayList<String>()));
    }
    
    /**
     * Adds all listeners to the {@link PartyMix#listeners} set
     */
    private void addListeners() {
        listeners.add(new FireStickListener());
        listeners.add(new LightningStickListener());
        listeners.add(new MageListener());
    }
    
    /*
     * Registers the commands and listners
     */
    private void register() {
        //command registering
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            for (Iterator<AbstractPartyCommand> i = commands.iterator(); i.hasNext();) {
                AbstractPartyCommand cmd = i.next();
                commandMap.register(cmd.getName(), cmd);
            }
            
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        //Listerer registering
        for (Iterator<PartyListener> i = listeners.iterator(); i.hasNext();) {
            PartyListener listener = i.next();
            getServer().getPluginManager().registerEvents(listener, p);
        }
    }

}
