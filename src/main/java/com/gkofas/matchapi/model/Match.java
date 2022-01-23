package com.gkofas.matchapi.model;

import javax.persistence.*;

/**
 * Class that represents the entity {@code Match} on db.
 */
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "match_date")
    private String matchDate;

    @Column(name = "match_time")
    private String matchTime;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @Column(name = "sport")
    private ESport sport;

    /**
     * Class constructor
     */
    public Match() {
        //no-op
    }

    /*
     * private constructor that can be instantiated by MatchBuilder
     */
    private Match(MatchBuilder builder) {
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
     * Returns a reference to the DTO equivalent of this {@code Match} object.
     *
     * @return  reference to the equivalent {@link MatchDTO} instance.
     */
    public MatchDTO toDTO() {
        return new MatchDTO.MatchDTOBuilder(getTeamA(), getTeamB())
                .setId(getId())
                .setDescription(getDescription())
                .setMatchDate(getMatchDate())
                .setMatchTime(getMatchTime())
                .setSport(getSport())
                .build();
    }

    /**
     * A builder class that builds {@link Match} instances.
     */
    public static class MatchBuilder {
        private  final String teamA;
        private  final String teamB;

        private String description;
        private String matchDate;
        private String matchTime;
        private ESport sport;

        /**
         * Class constructor.
         */
        public MatchBuilder(String teamA, String teamB) {
            this.teamA = teamA;
            this.teamB = teamB;
        }

        /**
         * Sets the {@code match description} value.
         *
         * @param description the match description value.
         * @return            reference to the {@link MatchBuilder} instance.
         */
        public MatchBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the {@code match date} value.
         *
         * @param matchDate the match date value.
         * @return          reference to the {@link MatchBuilder} instance.
         */
        public MatchBuilder setMatchDate(String matchDate) {
            this.matchDate = matchDate;
            return this;
        }

        /**
         * Sets the {@code match time} value.
         *
         * @param matchTime the match time value.
         * @return          reference to the {@link MatchBuilder} instance.
         */
        public MatchBuilder setMatchTime(String matchTime) {
            this.matchTime = matchTime;
            return this;
        }

        /**
         * Sets the {@code sport} value.
         *
         * @param sport the type of the sport.
         * @return      reference to the {@link MatchBuilder} instance.
         */
        public MatchBuilder setSport(ESport sport) {
            this.sport = sport;
            return this;
        }

        /**
         * Creates a new {@link Match} instance that has the same values as this match builder.
         * It should be called last, after all the setters.
         *
         * @return  reference to a newly created {@link Match} instance.
         */
        public Match build() {
            return new Match(this);
        }
    }
}
