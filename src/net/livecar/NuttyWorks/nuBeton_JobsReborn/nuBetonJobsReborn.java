package net.livecar.NuttyWorks.nuBeton_JobsReborn;

import java.util.logging.Level;

import net.livecar.NuttyWorks.nuBeton_JobsReborn.Conditions.*;
import net.livecar.NuttyWorks.nuBeton_JobsReborn.Events.*;
import net.livecar.NuttyWorks.nuBeton_JobsReborn.Objectives.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;

import com.gamingmesh.jobs.api.JobsJoinEvent;

import pl.betoncraft.betonquest.BetonQuest;

public class nuBetonJobsReborn extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener 
{
	public static nuBetonJobsReborn Instance;
	
	public void onEnable() {
		nuBetonJobsReborn.Instance = this;
	    
		//Register conditions with beton
		BetonQuest.getInstance().registerConditions("nujobs_canlevel", Condition_CanLevel.class);
		BetonQuest.getInstance().registerConditions("nujobs_hasjob", Condition_HasJob.class);
		BetonQuest.getInstance().registerConditions("nujobs_jobfull", Condition_JobFull.class);
		BetonQuest.getInstance().registerConditions("nujobs_joblevel", Condition_JobLevel.class);
	    
		//register events
		BetonQuest.getInstance().registerEvents("nujobs_addexp", Event_AddExp.class);
		BetonQuest.getInstance().registerEvents("nujobs_addlevel", Event_AddLevel.class);
		BetonQuest.getInstance().registerEvents("nujobs_dellevel", Event_DelLevel.class);
		BetonQuest.getInstance().registerEvents("nujobs_joinjob", Event_JoinJob.class);
		BetonQuest.getInstance().registerEvents("nujobs_leavejob", Event_LeaveJob.class);
		BetonQuest.getInstance().registerEvents("nujobs_setlevel", Event_SetLevel.class);
		
		//register objectives
		BetonQuest.getInstance().registerObjectives("nujobs_joinjob", Objective_JoinJob.class);
		BetonQuest.getInstance().registerObjectives("nujobs_leavejob", Objective_LeaveJob.class);
		BetonQuest.getInstance().registerObjectives("nujobs_levelup", Objective_LevelUpEvent.class);
		BetonQuest.getInstance().registerObjectives("nujobs_payment", Objective_PaymentEvent.class);
	    
		getLogger().log(java.util.logging.Level.ALL,ChatColor.GREEN + this.getDescription().getName() + " [V "+ this.getDescription().getVersion() + "] initialized");
		
		try {
			MCStatsMetrics metrics = new MCStatsMetrics(this);
			metrics.start();
		} catch (Exception e) {
			// Wheee no stats, oh well.
		}
	}
}
