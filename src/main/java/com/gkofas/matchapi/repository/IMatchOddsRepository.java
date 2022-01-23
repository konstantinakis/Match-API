package com.gkofas.matchapi.repository;

import com.gkofas.matchapi.model.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Interface that extends the {@link JpaRepository} matched with the {@link MatchOdds} model.
 */
public interface IMatchOddsRepository extends JpaRepository<MatchOdds, Long> {

    /**
     * Returns a list of all the {@link MatchOdds} that have the give {@code match id} as
     * a foreign key.
     *
     * @param matchID   the match id value.
     * @return          a list with {@link MatchOdds} instances.
     */
    List<MatchOdds> findByMatchId(long matchID);

    /**
     * Deletes all the {@link MatchOdds} that have as a foreign key the given {@code match id}.
     *
     * @param matchID   the match id value.
     */
    @Transactional
    void deleteByMatchId(long matchID);
}
