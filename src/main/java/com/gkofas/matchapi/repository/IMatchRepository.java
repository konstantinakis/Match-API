package com.gkofas.matchapi.repository;

import com.gkofas.matchapi.model.ESport;
import com.gkofas.matchapi.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface that extends the {@link JpaRepository} matched with the {@link Match} model.
 */
public interface IMatchRepository extends JpaRepository<Match, Long> {
    /**
     * Finds the matches of the given sport type.
     *
     * @param sport reference to the {@link ESport} type.
     * @return      a list with {@link Match} instances.
     */
    List<Match> findBySport(ESport sport);
}
