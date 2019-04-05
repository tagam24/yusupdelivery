package com.tagam24.tagam.models;

/**
 * Created by User on 25.02.2019.
 */

public class model_vote {
    String id, vote;

    public model_vote() {
    }

    public model_vote(String id, String vote) {
        this.id = id;
        this.vote = vote;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getId() {
        return id;
    }

    public String getVote() {
        return vote;
    }
}
