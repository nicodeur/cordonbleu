package com.benromberg.cordonbleu.main.resource.commit;

import com.benromberg.cordonbleu.main.resource.team.ReadCommitAuthorResponse;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadCommitListItemResponse {
    @JsonProperty
    private final ReadCommitAuthorResponse author;

    @JsonProperty
    private final String hash;

    @JsonProperty
    private final LocalDateTime created;

    @JsonProperty
    private final List<String> repositories;

    @JsonProperty
    private final int numComments;

    @JsonProperty
    private final boolean approved;

    @JsonProperty
    private final boolean removed;

    @JsonCreator
    public ReadCommitListItemResponse(ReadCommitAuthorResponse author, String hash, LocalDateTime created,
            List<String> repositories, int numComments, boolean approved, boolean removed) {
        this.author = author;
        this.hash = hash;
        this.created = created;
        this.repositories = repositories;
        this.numComments = numComments;
        this.approved = approved;
        this.removed = removed;
    }

    public ReadCommitAuthorResponse getAuthor() {
        return author;
    }

    public String getHash() {
        return hash;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<String> getRepositories() {
        return repositories;
    }

    public int getNumComments() {
        return numComments;
    }

    public boolean isApproved() {
        return approved;
    }
}
