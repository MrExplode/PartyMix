package me.mrexplode.partymix.listeners;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.mrexplode.partymix.PartyListener;
import me.mrexplode.partymix.PartyMix;
import me.mrexplode.partymix.PartyMixEvent;


public class MageListener extends PartyListener {

    @SuppressWarnings("deprecation")
    @Override
    public void onPartyUse(PartyMixEvent e) {
        final Player player = e.getPlayer();
        if (e.getIdentifier().equalsIgnoreCase("Eye of truth") && isRightClick(e.getAction())) {
            //defensive
            player.setHealth(Math.min(player.getHealth() + 2, player.getMaxHealth()));
        }
        if (e.getIdentifier().equalsIgnoreCase("Eye of truth") && isLeftClick(e.getAction())) {
            //offensive
            BukkitRunnable run = new BukkitRunnable() {
                int i = 0;
                @Override
                public void run() {
                    Fireball f = player.launchProjectile(Fireball.class);
                    f.setIsIncendiary(false);
                    f.setYield(2f);
                    i++;
                    if (i > 20) {
                        this.cancel();
                    }
                }
                
            };
            run.runTaskTimer(PartyMix.p, 0, 2);
        }
    }

}
