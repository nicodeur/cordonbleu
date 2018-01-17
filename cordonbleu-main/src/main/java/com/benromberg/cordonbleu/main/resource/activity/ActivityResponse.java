package com.benromberg.cordonbleu.main.resource.activity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityResponse {
	
	private final Integer nbCommit;
	private final Integer nbCommitApproved;
	private final Integer nbCommitWithComments;
	private final Integer nbCommitWithoutReview;
	
	@JsonCreator
	public ActivityResponse(@JsonProperty("nbCommit") Integer nbCommit, 
			@JsonProperty("nbCommitApproved") Integer nbCommitApproved,
			@JsonProperty("nbCommitWithComments") Integer nbCommitWithComments, 
			@JsonProperty("nbCommitWithoutReview") Integer nbCommitWithoutReview) {
		this.nbCommit = nbCommit;
		this.nbCommitApproved = nbCommitApproved;
		this.nbCommitWithComments = nbCommitWithComments;
		this.nbCommitWithoutReview = nbCommitWithoutReview;
	}

	@JsonProperty
	public Integer getNbCommit() {
		return nbCommit;
	}

	@JsonProperty
	public Integer getNbCommitApproved() {
		return nbCommitApproved;
	}

	@JsonProperty
	public Integer getNbCommitWithComments() {
		return nbCommitWithComments;
	}

	@JsonProperty
	public Integer getNbCommitWithoutReview() {
		return nbCommitWithoutReview;
	}

}
