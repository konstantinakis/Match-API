package com.gkofas.matchapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Class that represents the entity {@code MatchOdds} on db.
 */
@Entity
@Table(name = "match_odds")
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Match match;

    @Column(name = "specifier")
    private ESpecifier specifier;

    @Column(name = "odd")
    private float odd;

    /**
     * Class constructor.
     */
    public MatchOdds() {
        //no-op
    }

    /**
     * Class constructor.
     */
    public MatchOdds(ESpecifier specifier, float odd, Match match) {
        this.specifier = specifier;
        this.odd = odd;
        this.match = match;
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
     * Getter of the match specifier value.
     *
     * @return  the match specifier value
     */
    public ESpecifier getSpecifier() {
        return specifier;
    }

    /**
     * Getter of the match odd value.
     *
     * @return  the match odd value
     */
    public float getOdd() {
        return odd;
    }

    /**
     * Gets the reference of the {@link Match} that this odd belongs to.
     *
     * @return  reference of the {@link Match} instance.
     */
    public Match getMatch() {
        return match;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter of the specifier value.
     *
     * @param specifier   the string value of the specifier.
     */
    public void setSpecifier(ESpecifier specifier) {
        this.specifier = specifier;
    }

    /**
     * Setter of the odd value.
     *
     * @param odd   the value of the odd.
     */
    public void setOdd(float odd) {
        this.odd = odd;
    }

    /**
     * Setter of the {@link Match} instance that this match odds correspond to.
     *
     * @param match   reference to a {@link Match} instance.
     */
    public void setMatch(Match match) {
        this.match = match;
    }

    /**
     * Returns a reference to the DTO equivalent of this {@code MatchOdds} object.
     *
     * @return  reference to the equivalent {@link MatchOddsDTO} instance.
     */
    public MatchOddsDTO toDTO() {
        return new MatchOddsDTO(getId(), getSpecifier().toString(), getOdd(), match.getId());
    }
}
