package de.pvhil.philutil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;


public class Commands implements CommandExecutor  {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){return true;}

        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("c")){
            String x = String.valueOf(player.getLocation().getBlockX());
            String y = String.valueOf(player.getLocation().getBlockY());
            String z = String.valueOf(player.getLocation().getBlockZ());
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY+"Spieler "+ ChatColor.AQUA+player.getName()+ChatColor.GRAY+" ist bei "+x+" "+y+" "+z);
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("store")){
            if(args.length == 1) {
                String x = String.valueOf(player.getLocation().getBlockX());
                String y = String.valueOf(player.getLocation().getBlockY());
                String z = String.valueOf(player.getLocation().getBlockZ());
                String alias = args[0];

                String data = player.getUniqueId()+"|"+x+"|"+y+"|"+z+"|"+alias+"\n";

                File file = Main.myObj;
                try {
                    FileWriter fr = new FileWriter(file, true);
                    BufferedWriter br = new BufferedWriter(fr);
                    br.write(data);
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage(ChatColor.GRAY + "Du hast die Koordinaten " + x + " " + y + " " + z + " mit dem Alias " + alias + " gespeichet (/clist)");
                return true;}
            else player.sendMessage(ChatColor.GRAY + "Bitte gibn Alias an!");
            }
        if(cmd.getName().equalsIgnoreCase("clist")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(Main.myObj));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        if(line.contains(player.getUniqueId().toString())) {
                            String[] parts = line.split("\\|");
                            sb.append(parts[4]+": "+parts[1]+" "+parts[2]+" "+parts[3]+"\n");
                            line = br.readLine();
                        }
                    }
                    String everything = sb.toString();
                    player.sendMessage("Deine Koordinaten:\n"+ChatColor.GRAY + everything);
                } finally {
                    br.close();
                }
            }catch (IOException e){
                ;
            }
            return true;
        }


        return true;
    }
}
