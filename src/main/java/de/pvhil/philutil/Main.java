package de.pvhil.philutil;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin implements CommandExecutor {

    public String pd = getDataFolder().getAbsolutePath();
    public static File myObj;
    public FileConfiguration config = getConfig();


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.getConfig();
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new BedEvent(), this);

        try {
            myObj = new File(pd, "cstore.txt");
            if (myObj.createNewFile()) {
                getServer().getConsoleSender().sendMessage("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            getServer().getConsoleSender().sendMessage("An error occurred.");
            e.printStackTrace();
        }

        Commands cmds = new Commands();
        getCommand("c").setExecutor(cmds);
        getCommand("store").setExecutor(cmds);
        getCommand("clist").setExecutor(cmds);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "PhilUtil geladen. Viel spass beim Projekt!");
        getServer().getConsoleSender().sendMessage(config.getString("test"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED+"PhilUtil aus.");

    }



}
