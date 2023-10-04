package com.example.VotingSystemWar.models;

import javax.persistence.*;

@Entity
@Table(name = "vote_records")
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voter;

    @Column(name = "vote_item_id")
    private Long voteItemId;

    public VoteRecord() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public Long getVoteItemId() {
        return voteItemId;
    }

    public void setVoteItemId(Long voteItemId) {
        this.voteItemId = voteItemId;
    }
}
