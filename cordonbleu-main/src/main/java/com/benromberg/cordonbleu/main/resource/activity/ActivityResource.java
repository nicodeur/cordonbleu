package com.benromberg.cordonbleu.main.resource.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.benromberg.cordonbleu.data.model.Commit;
import com.benromberg.cordonbleu.data.model.Team;
import com.benromberg.cordonbleu.service.commit.CommitService;
import com.benromberg.cordonbleu.service.team.TeamService;
import com.codahale.metrics.annotation.Timed;

import com.benromberg.cordonbleu.main.resource.activity.ActivityResponse;

@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityResource {
    private final TeamService teamService;
    private final CommitService commitService;
    
    @Inject
    public ActivityResource(TeamService teamService, CommitService commitService) {
        this.teamService = teamService;
        this.commitService = commitService;
    }

    @GET
    @Timed
    @Path("/public")
    public ActivityResponse getActivityByTeam(@QueryParam("name") String name, @QueryParam("begin") String beginDate, @QueryParam("end") String endDate) {
    	
    	Integer nbCommit = 0;
    	Integer nbCommitApproved = 0;
    	Integer nbCommitWithComments = 0;
    	Integer nbCommitWithoutReview = 0;
    	
    	if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(beginDate) && StringUtils.isNotBlank(endDate)) {
    		Optional<Team> myTeam = teamService.findTeamByName(name);
    		
    		if (myTeam.isPresent()) {
	            DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	    		DateTimeFormatter START_OF_DAY = new DateTimeFormatterBuilder().append(DATEFORMATTER)
	    				.parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
	    				.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter();
	    		
	    		DateTimeFormatter END_OF_DAY = new DateTimeFormatterBuilder().append(DATEFORMATTER)
	    				.parseDefaulting(ChronoField.HOUR_OF_DAY, 23).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
	    				.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59).toFormatter();
	
	            LocalDateTime localBegin = LocalDateTime.parse(beginDate, START_OF_DAY);
	            LocalDateTime localEnd = LocalDateTime.parse(endDate, END_OF_DAY);
	            
	    		List<Commit> listCommits = commitService.findByTeam(myTeam.get(), localBegin, localEnd);
		    	if (listCommits != null && listCommits.size() > 0) {
		    		nbCommit = listCommits.size();
		    		
		    		for (Commit commit : listCommits) {
		    			if (commit.getApproval().isPresent()) {
		    				nbCommitApproved ++;
		    			}
		    			if (commit.getComments() != null && commit.getComments().size() > 0) {
		    				nbCommitWithComments ++;
		    			}
		    			if (!commit.getApproval().isPresent()
		    					&& commit.getComments() != null && commit.getComments().size() == 0) {
		    				nbCommitWithoutReview ++;
		    			}
		    		}
		    	}
    		}
    	}
    	
		return new ActivityResponse(nbCommit, nbCommitApproved, nbCommitWithComments, nbCommitWithoutReview);
    }
 
}
