package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_9.Conditions;

import java.util.List;

import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobProgression;

import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class Condition_CanLevel extends Condition
{
	private String sJobName;
	
	public Condition_CanLevel(Instruction instruction) throws InstructionParseException
	{
		super(instruction);
		if (instruction.size() < 2) {
			throw new InstructionParseException("Not enough arguments");
		}
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(instruction.getPart(1)))
			{
				sJobName = job.getName();
				return;
			}
		}
		throw new InstructionParseException("Jobs Reborn job " + instruction.getPart(1) + " does not exist" );
	}

	public boolean check(String playerID)
	{
		Player oPlayer =  PlayerConverter.getPlayer(playerID);
		
		List<JobProgression> oJobs = Jobs.getPlayerManager().getJobsPlayer(oPlayer).getJobProgression();
		for (JobProgression oJob : oJobs) 
		{
			if (oJob.getJob().getName().equalsIgnoreCase(sJobName))
			{
				//User has the job, return true
				if (oJob.canLevelUp())
					return true;
			}
		}
		return false;
	}
}
