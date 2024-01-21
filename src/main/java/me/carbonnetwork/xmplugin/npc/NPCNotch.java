package me.carbonnetwork.xmplugin.npc;

import me.carbonnetwork.xmplugin.XmPlugin;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class NPCNotch implements Listener {

    private final XmPlugin plugin;
    public NPCNotch(XmPlugin plugin) {
        this.plugin = plugin;
    }
    int NPCID = 13;
    int followDistance = 10;
    private boolean isAtDestination = false;
    private int taskID = -1;

    @EventHandler
    private void onRightClick(NPCRightClickEvent e) {

        Player p = e.getClicker();
        NPC npc = e.getNPC();
        Location originalLocation = new Location(npc.getEntity().getWorld(), 72.0D, 68.0D, 155.0D, 270.0F, 0.0F);
        Location targetLocation = new Location(npc.getEntity().getWorld(), 51.0D, 68.0D, 162.0D, 270.0F, 0.0F);

        if (npc.getId() != NPCID) return;

        if (!isAtDestination) {
            if (p.getLocation().distance(npc.getEntity().getLocation()) > followDistance) {
                npc.getNavigator().cancelNavigation();
            } else {
                npc.getNavigator().setTarget(targetLocation);
                startScheduler(npc, new Location(npc.getEntity().getWorld(), 53.0D, 68.0D, 162.0D, 270.0F, 0.0F));
                isAtDestination = true;
            }
        } else {
            if (p.getLocation().distance(npc.getEntity().getLocation()) > followDistance) {
                npc.getNavigator().cancelNavigation();
            } else {
                npc.getNavigator().setTarget(originalLocation);
                startScheduler(npc, new Location(npc.getEntity().getWorld(), 71.0D, 68.0D, 155.0D, 270.0F, 0.0F));
                isAtDestination = false;
            }
        }

    }

    public void setupNPC() {

        NPC npc = CitizensAPI.getNPCRegistry().getById(NPCID);
        if (npc == null) return;

        Location originalLocation = new Location(npc.getEntity().getWorld(), 71.5D, 68.0D, 155.5D, 90.0F, 0.0F);
        npc.teleport(originalLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);

    }

    private void startScheduler(NPC npc, Location targetLocation) {
        if (taskID != -1) {
            Bukkit.getScheduler().cancelTask(taskID);
        }

        taskID = new BukkitRunnable() {
            @Override
            public void run() {
                boolean hasReached = hasReachedTarget(npc, targetLocation);
                if (Objects.equals(targetLocation, new Location(npc.getEntity().getWorld(), 53.0D, 68.0D, 162.0D, 270.0F, 0.0F))) {
//                    plugin.getLogger().severe("Has reached: " + hasReached);
                    if (hasReached) {
                        Location teleportLocation = new Location(npc.getEntity().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY(), npc.getEntity().getLocation().getZ(), 270.0F, 0.0F);
                        npc.teleport(teleportLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
                        cancel();
                    }
                } else if (Objects.equals(targetLocation, new Location(npc.getEntity().getWorld(), 71.0D, 68.0D, 155.0D, 270.0F, 0.0F))) {
                    if (hasReached) {
                        Location teleportLocation = new Location(npc.getEntity().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY(), npc.getEntity().getLocation().getZ(), 90.0F, 0.0F);
                        npc.teleport(teleportLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(this.plugin, 0L, 20L).getTaskId();
    }

    private boolean hasReachedTarget(NPC npc, Location targetLocation) {
        Location currentLocation = npc.getEntity().getLocation();
        double distance = currentLocation.distance(targetLocation);

        double threshold = 1.0;

        return distance < threshold;
    }

}
