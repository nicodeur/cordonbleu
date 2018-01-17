package com.benromberg.cordonbleu.main.resource.activity;


import static org.assertj.core.api.Assertions.assertThat;

import com.benromberg.cordonbleu.main.CordonBleuTestRule;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.benromberg.cordonbleu.data.model.CommitFixture;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class ActivityResourceTest implements CommitFixture {
 
    @Rule
    @ClassRule
    public static final CordonBleuTestRule RULE = new CordonBleuTestRule().withTeam();
    
    @Test
    public void getActivity() throws Exception {
        Response response = RULE.request().param("name", TEAM_NAME).get("/api/activity/public");
        assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
    }

}
