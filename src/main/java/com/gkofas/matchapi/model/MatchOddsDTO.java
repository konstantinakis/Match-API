package com.gkofas.matchapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Class that represents the equivalent data transfer object of {@code MatchOdds} object.
 */
public class MatchOddsDTO implements Serializable {

    @JsonProperty("id")
    private long id;
    @JsonProperty("specifier")
    private String specifier;
    @JsonProperty("odd")
    private float odd;
    @JsonProperty("match_id")
    private long matchId;

    /*
     * Class constructor.
     */
    public MatchOddsDTO() {
        //no-op
    }

    public MatchOddsDTO(String specifier, float odd, long matchId) {
        this.specifier = specifier;
        this.odd = odd;
        this.matchId = matchId;
    }

    public MatchOddsDTO(long id, String specifier, float odd, long matchId) {
        this.id = id;
        this.specifier = specifier;
        this.odd = odd;
        this.matchId = matchId;
    }

    public long getId() {
        return id;
    }

    public String getSpecifier() {
        return specifier;
    }

    public float getOdd() {
        return odd;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public void setOdd(float odd) {
        this.odd = odd;
    }

    public void setMatch(long match) {
        this.matchId = match;
    }
}
