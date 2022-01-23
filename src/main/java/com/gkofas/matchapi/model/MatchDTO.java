package com.gkofas.matchapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Class that represents the equivalent data transfer object of {@code Match} object.
 */
public class MatchDTO implements Serializable {
    @JsonProperty("id")
    private long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("match_date")
    private String matchDate;
    @JsonProperty("match_time")
    private String matchTime;
    @JsonProperty("team_a")
    private String teamA;
    @JsonProperty("team_b")
    private String teamB;
    @JsonProperty("sport")
    private ESport sport;

    /**
     * Class instructor.
     */
    public MatchDTO() {
        // no-op
    }

    /*
     * private constructor that can be instantiated by MatchDTOBuilder
     */
    private MatchDTO(MatchDTOBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.matchDate = builder.matchDate;
        this.matchTime = builder.matchTime;
        this.teamA = builder.teamA;
        this.teamB = builder.teamB;
        this.sport = builder.sport;
    }

    /**
     * Getter of the id value.
     *
     * @return  the id value
     */
    public long getId() {
        return id;
    }

    /**
     * Getter of the match description.
     *
     * @return  the string value of the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of the match date.
     *
     * @return  the string value of the match date.
     */
    public String getMatchDate() {
        return matchDate;
    }

    /**
     * Getter of the match time.
     *
     * @return  the string value of the match time.
     */
    public String getMatchTime() {
        return matchTime;
    }

    /**
     * Getter of the matches first team.
     *
     * @return  the string value of the first team.
     */
    public String getTeamA() {
        return teamA;
    }

    /**
     * Getter of the matches second team.
     *
     * @return  the string value of the second team.
     */
    public String getTeamB() {
        return teamB;
    }

    /**
     * Getter of the sport type of the match.
     *
     * @return  reference to the {@link ESport} of the match.
     */
    public ESport getSport() {
        return sport;
    }

    /**
     * Setter of the ID value.
     *
     * @param id   the match's ID value.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter of the match description value.
     *
     * @param description   the string value of the match description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter of the match date value.
     *
     * @param matchDate   the string value of the match date.
     */
    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * Setter of the match time value.
     *
     * @param matchTime   the string value of the match time.
     */
    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    /**
     * Setter of the team A value.
     *
     * @param teamA   the string value of the team A.
     */
    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    /**
     * Setter of the team B value.
     *
     * @param teamB   the string value of the team B.
     */
    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    /**
     * Setter of the match sport type value.
     *
     * @param sport   reference to the {@link ESport} type.
     */
    public void setSport(ESport sport) {
        this.sport = sport;
    }

    /**
     * Returns a reference to the entity equivalent of this {@code MatchDTO} object.
     *
     * @return  reference to the equivalent {@link Match} instance.
     */
    public Match toEntity() {
        return new Match.MatchBuilder(getTeamA(), getTeamB())
                .setDescription(getDescription())
                .setMatchDate(getMatchDate())
                .setMatchTime(getMatchTime())
                .setSport(getSport())
                .build();
    }

    /**
     * A builder class that builds {@link Match} instances.
     */
    public static class MatchDTOBuilder {
        private final String teamA;
        private final String teamB;

        private long id;
        private String description;
        private String matchDate;
        private String matchTime;
        private ESport sport;

        /**
         * Class constructor.
         */
        public MatchDTOBuilder(String teamA, String teamB) {
            this.teamA = teamA;
            this.teamB = teamB;
        }

        /**
         * Sets the {@code match ID} value.
         *
         * @param id the match ID.
         * @return   reference to the {@link MatchDTOBuilder} instance.
         */
        public MatchDTOBuilder setId(long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the {@code match description} value.
         *
         * @param description the match description value.
         * @return reference to the {@link MatchDTOBuilder} instance.
         */
        public MatchDTOBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the {@code match date} value.
         *
         * @param matchDate the match date value.
         * @return reference to the {@link MatchDTOBuilder} instance.
         */
        public MatchDTOBuilder setMatchDate(String matchDate) {
            this.matchDate = matchDate;
            return this;
        }

        /**
         * Sets the {@code match time} value.
         *
         * @param matchTime the match time value.
         * @return reference to the {@link MatchDTOBuilder} instance.
         */
        public MatchDTOBuilder setMatchTime(String matchTime) {
            this.matchTime = matchTime;
            return this;
        }

        /**
         * Sets the {@code sport} value.
         *
         * @param sport the type of the sport.
         * @return reference to the {@link MatchDTOBuilder} instance.
         */
        public MatchDTOBuilder setSport(ESport sport) {
            this.sport = sport;
            return this;
        }

        /**
         * Creates a new {@link MatchDTO} instance that has the same values as this match builder.
         * It should be called last, after all the setters.
         *
         * @return reference to a newly created {@link MatchDTO} instance.
         */
        public MatchDTO build() {
            return new MatchDTO(this);
        }
    }
}
