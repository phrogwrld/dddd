package net.silthus.template.listener;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.silthus.template.TemplatePlugin;

public class DeathEvent implements Listener {

  private final HashSet<UUID> kickHashSet = new HashSet<>();

  private final TemplatePlugin templatePlugin;

  public DeathEvent(final TemplatePlugin templatePlugin) {
    this.templatePlugin = templatePlugin;
    templatePlugin.getServer().getPluginManager().registerEvents(this, templatePlugin);
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onJoin(PlayerDeathEvent event) {
    Player player = event.getEntity();

    kickHashSet.add(player.getUniqueId());
    Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "You died!", new Date(new Date().getTime() + 2000000),
        player.getName());
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();

    if (kickHashSet.contains(player.getUniqueId())) {
      kickHashSet.remove(player.getUniqueId());
      player.kick();
    }
  }
}
