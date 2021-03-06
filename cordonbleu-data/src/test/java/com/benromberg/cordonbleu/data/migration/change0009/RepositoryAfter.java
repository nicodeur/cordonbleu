package com.benromberg.cordonbleu.data.migration.change0009;

import com.benromberg.cordonbleu.data.util.MongoCommand;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryAfter {
    @JsonProperty(MongoCommand.ID_PROPERTY)
    private final String id;

    @JsonProperty
    private final String otherField;

    @JsonProperty
    private final String team;

    @JsonCreator
    public RepositoryAfter(String id, String otherField, String team) {
        this.id = id;
        this.otherField = otherField;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public String getOtherField() {
        return otherField;
    }

    public String getTeam() {
        return team;
    }

}
