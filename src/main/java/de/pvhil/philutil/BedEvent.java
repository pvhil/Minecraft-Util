package de.pvhil.philutil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import static org.bukkit.Bukkit.getServer;

public class BedEvent implements Listener {

    private int bedcount = 0;

    @EventHandler
    public void onBedJoin(PlayerBedEnterEvent e) throws InterruptedException {
        if(e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_NOW){
            return;
        }

        Player player = e.getPlayer();
        World world = player.getWorld();
        getServer().getConsoleSender().sendMessage(String.valueOf(world.getTime()));
        bedcount++;
        int playercount = Bukkit.getOnlinePlayers().size();
        double rr = playercount / 2;
        int result = (int) Math.floor(rr);
        if(bedcount >= result){
            world.setTime(23450);
            getServer().broadcastMessage(ChatColor.AQUA+"Guten Morgen!");

            if (world.hasStorm())
                world.setStorm(false);
            if (world.isThundering())
                world.setThundering(false);
            if (world.getWeatherDuration() > 0)
                world.setWeatherDuration(0);

            for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                if (allPlayers.isSleeping())
                    allPlayers.wakeup(true);
            }


        }else getServer().broadcastMessage(ChatColor.AQUA+ player.getName()+ChatColor.GRAY+" ist im Bett! ("+bedcount+"/"+result+")");

    }
    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e){
        if(bedcount >0) {
            bedcount--;
        }
        Player player = e.getPlayer();
        int playercount = Bukkit.getOnlinePlayers().size();
        double rr = playercount / 2;
        int result = (int) Math.floor(rr);
        if(player.getWorld().getTime() < 23450 || player.getWorld().getTime() > 6000) {
            getServer().broadcastMessage(ChatColor.AQUA + player.getName() + ChatColor.GRAY + " hat das Bett verlassen! (" + bedcount + "/" + result + ")");
        }

    }

}
