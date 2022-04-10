package net.mcmerdith.guildedmenu.components;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BusinessManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use business commands");
            return false;
        }

        Player player = (Player) commandSender;

        if (strings.length == 0) return false;
        String subCommand = strings[0];

        switch (subCommand) {
            case "register":
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < strings.length; i++) {
                    name.append(strings[i]);
                    if (i != strings.length - 1) name.append(" ");
                }

                register(player.getUniqueId(), name.toString());
                break;
            case "delete":
                break;

        }

        return true;
    }

    // Data Storage

    public class Business {
        public String name;
        public UUID[] managers;

        public Business(String name) {
            this(name, null);
        }

        public Business(String name, UUID[] managers) {
            this.name = name;
            this.managers = managers;
        }
    }

    private Map<UUID, Business> businessMap = new HashMap<>();

    public void register(UUID entity, String name) {

    }
}
