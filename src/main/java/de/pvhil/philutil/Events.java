package de.pvhil.philutil;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e){
        //replace standard join message
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.GREEN+player.getName()+ChatColor.AQUA+" ist dem Server beigetreten!");
        player.sendTitle("Hallo "+player.getName(), "Willkommen zurück", 20, 70, 20);



    }
    @EventHandler
    public static void onPlayerLeave(PlayerQuitEvent e){
        //replace standard leave message
        e.setQuitMessage(ChatColor.GREEN+e.getPlayer().getName()+ChatColor.AQUA+" hat den Server verlassen!");

    }
    @EventHandler
    public static void onChat(AsyncPlayerChatEvent e){
        //replace standard chat
        String message = e.getMessage();
        e.setFormat(ChatColor.AQUA+e.getPlayer().getName()+": "+ChatColor.WHITE+message);

    }
    @EventHandler
    public static void onDeath(PlayerDeathEvent e){
        //send death coords
        Player player = e.getEntity();
        String x = String.valueOf(player.getLocation().getBlockX());
        String y = String.valueOf(player.getLocation().getBlockY());
        String z = String.valueOf(player.getLocation().getBlockZ());
        player.sendMessage("Du bist bei "+x+" "+y+" "+z+" gestorben.");

    }

}
