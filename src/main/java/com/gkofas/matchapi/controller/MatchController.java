package com.gkofas.matchapi.controller;

import com.gkofas.matchapi.model.*;
import com.gkofas.matchapi.repository.IMatchOddsRepository;
import com.gkofas.matchapi.repository.IMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    IMatchRepository matchRepository;
    @Autowired
    IMatchOddsRepository matchOddsRepository;

    /**
     * Returns a list with all the {@link Match} instances that exist in the db.
     *
     * @return  reference to the {@link ResponseEntity} containing the {@link MatchDTO}s and the {@link HttpStatus}.
     */
    @GetMapping("/matches")
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        ResponseEntity<List<MatchDTO>> responseEntity;
        try {
            List<MatchDTO> matches = new ArrayList<MatchDTO>();
            for (Match match : matchRepository.findAll()) {
                matches.add(match.toDTO());
            }

            if (matches.isEmpty()) {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                responseEntity = new ResponseEntity<>(matches, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo log
        }
        return responseEntity;
    }

    /**
     * Returns the {@link MatchDTO} instance that contains the given {@code id}.
     *
     * @return  reference to the {@link ResponseEntity} containing the match and the {@link HttpStatus}.
     */
    @GetMapping("/matches/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable("id") long id) {
        ResponseEntity<MatchDTO> responseEntity;
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            MatchDTO matchDTOData = matchData.get().toDTO();
            responseEntity = new ResponseEntity<>(matchDTOData, HttpStatus.OK);
        } else {
            responseEntity =  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * Returns a list with the Matches that their sport type is the given {@code sport}.
     *
     * @return  reference to the {@link ResponseEntity} containing the {@link MatchDTO} and the {@link HttpStatus}.
     */
    @GetMapping("/matches/sport/{sport}")
    public ResponseEntity<List<MatchDTO>> getMatchBySport(@PathVariable("sport") ESport sport) {
        ResponseEntity<List<MatchDTO>> responseEntity;
        List<MatchDTO> matches = new ArrayList<MatchDTO>();

        for (Match match : matchRepository.findBySport(sport)) {
            matches.add(match.toDTO());
        }
        if (matches.isEmpty()) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(matches, HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * Adds a new {@link Match} instance to the db.
     *
     * @param match reference to the {@link MatchDTO} instance.
     * @return      reference to the {@link ResponseEntity} containing the updated {@link MatchDTO}
     *              and the {@link HttpStatus}.
     */
    @PostMapping("/matches")
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO match) {
        ResponseEntity<MatchDTO> responseEntity;
        try {
            Match newMatch = matchRepository.save(new Match.MatchBuilder(match.getTeamA(), match.getTeamB())
                                                            .setDescription(match.getDescription())
                                                            .setMatchDate(match.getMatchDate())
                                                            .setMatchTime(match.getMatchTime())
                                                            .setSport(match.getSport())
                                                            .build());
            responseEntity = new ResponseEntity<>(newMatch.toDTO(), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Updates the Match entry in the database with the given {@code id}.
     *
     * @param id    the id of the match that should be updated.
     * @param match reference to the {@link MatchDTO} instance.
     *
     * @return      reference to the {@link ResponseEntity} containing the updated {@link MatchDTO}
     *              and the {@link HttpStatus}.
     */
    @PutMapping("/matches/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable("id") long id, @RequestBody MatchDTO match) {
        ResponseEntity<MatchDTO> responseEntity;
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            Match matchObj = matchData.get();
            matchObj.setDescription(match.getDescription());
            matchObj.setMatchDate(match.getMatchDate());
            matchObj.setMatchTime(match.getMatchTime());
            matchObj.setSport(match.getSport());
            matchObj.setTeamA(match.getTeamA());
            matchObj.setTeamB(match.getTeamB());
            responseEntity = new ResponseEntity<>(matchRepository.save(matchObj).toDTO(), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * Deletes the Match entry in the database with the given {@code id}.
     *
     * @param id    the id of the match that should be deleted.
     * @return      reference to the {@link ResponseEntity} containing the {@link HttpStatus}.
     */
    @DeleteMapping("/matches/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id") long id) {
        ResponseEntity<HttpStatus> responseEntity;
        try {
            matchRepository.deleteById(id);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //todo logger
        }
        return responseEntity;
    }

    /**
     * Deletes all the Match entries in the database.
     *
     * @return      reference to the {@link ResponseEntity} containing the {@link HttpStatus}.
     */
    @DeleteMapping("/matches")
    public ResponseEntity<HttpStatus> deleteAllMatches() {
        ResponseEntity<HttpStatus> responseEntity;
        try {
            matchRepository.deleteAll();
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //todo logger
        }
        return responseEntity;
    }

    /**
     * Returns a list with all the {@link MatchOdds} instances that exist in the db.
     *
     * @return  reference to the {@link ResponseEntity} containing the {@link MatchOdds} and the {@link HttpStatus}.
     */
    @GetMapping("/match_odds")
    public ResponseEntity<List<MatchOddsDTO>> getAllMatchOdds() {
        ResponseEntity<List<MatchOddsDTO>> responseEntity;
        try {
            List<MatchOddsDTO> matches = new ArrayList<MatchOddsDTO>();
            for (MatchOdds matchOdds : matchOddsRepository.findAll()) {
                matches.add(matchOdds.toDTO());
            }

            if (matches.isEmpty()) {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                responseEntity = new ResponseEntity<>(matches, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo log
        }
        return responseEntity;
    }

    /**
     * Returns a list with all the {@link MatchOddsDTO} instances that exist for the given {@code match id}.
     *
     * @param matchID    the id of the match.
     * @return           reference to the {@link ResponseEntity} containing the
     *                   {@link MatchOddsDTO} and the {@link HttpStatus}.
     */
    @GetMapping("/match_odds/{match_id}")
    public ResponseEntity<List<MatchOddsDTO>> getMatchOddsByMatchId(@PathVariable("match_id") long matchID) {
        ResponseEntity<List<MatchOddsDTO>> responseEntity;
        List<MatchOddsDTO> matchOddsDTOs = new ArrayList<MatchOddsDTO>();

        if (matchRepository.existsById(matchID)) {
            List<MatchOdds> matchOdds = matchOddsRepository.findByMatchId(matchID);
            for (MatchOdds matchOdd : matchOdds) {
                matchOddsDTOs.add(matchOdd.toDTO());
            }
            responseEntity = new ResponseEntity<>(matchOddsDTOs, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * Adds a new {@link MatchOdds} instance to the db.
     *
     * @param matchOdd  reference to the {@link MatchOddsDTO} instance.
     * @return          reference to the {@link ResponseEntity} containing the new {@link MatchOddsDTO}
     *                  and the {@link HttpStatus}.
     */
    @PostMapping("/matches/{match_id}/match_odds")
    public ResponseEntity<MatchOddsDTO> createMatchOdds(@PathVariable(value = "match_id") long matchID, @RequestBody MatchOddsDTO matchOdd) {
        ResponseEntity<MatchOddsDTO> responseEntity;
        try {
            Optional<Match> match = matchRepository.findById(matchID);
            if (match.isPresent()) {
                MatchOdds newMatchOdds = new MatchOdds(
                        ESpecifier.getESpecifierFrom(matchOdd.getSpecifier()), matchOdd.getOdd(), match.get());
                MatchOddsDTO matchOddsDTO = matchOddsRepository.save(newMatchOdds).toDTO();
                responseEntity = new ResponseEntity<>(matchOddsDTO, HttpStatus.CREATED);
            } else {
                responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo catch exception
        }
        return responseEntity;
    }

    /**
     * Updates the MatchOdds entry in the database with the given {@code id}.
     *
     * @param id            the id of the match odds that should be updated.
     * @param matchOddsDTO  reference to the {@link MatchDTO} instance.
     *
     * @return              reference to the {@link ResponseEntity} containing the updated {@link MatchDTO}
     *                      and the {@link HttpStatus}.
     */
    @PutMapping("/match_odds/{id}")
    public ResponseEntity<MatchOddsDTO> updateMatchOdds(@PathVariable("id") long id,
                                                        @RequestBody MatchOddsDTO matchOddsDTO) {
        ResponseEntity<MatchOddsDTO> responseEntity;
        Optional<MatchOdds> matchOdds = matchOddsRepository.findById(id);

        if (matchOdds.isPresent()) {
            MatchOdds matchOddsObj = matchOdds.get();
            matchOddsObj.setOdd(matchOddsDTO.getOdd());
            matchOddsObj.setSpecifier(ESpecifier.getESpecifierFrom(matchOddsDTO.getSpecifier()));
            responseEntity = new ResponseEntity<>(matchOddsRepository.save(matchOddsObj).toDTO(),  HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * Deletes the MatchOdds entry in the database with the given {@code id}.
     *
     * @param id    the id of the match odds that should be deleted.
     * @return      reference to the {@link ResponseEntity} containing the {@link HttpStatus}.
     */
    @DeleteMapping("/match_odds/{id}")
    public ResponseEntity<HttpStatus> deleteMatchOdd(@PathVariable("id") long id) {
        ResponseEntity<HttpStatus> responseEntity;
        try {
            matchOddsRepository.deleteById(id);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //todo logger
        }
        return responseEntity;
    }

    /**
     * Deletes all the MatchOdds entries in the database.
     *
     * @return      reference to the {@link ResponseEntity} containing the {@link HttpStatus}.
     */
    @DeleteMapping("/match_odds")
    public ResponseEntity<HttpStatus> deleteAllMatchOdds() {
        ResponseEntity<HttpStatus> responseEntity;
        try {
            matchOddsRepository.deleteAll();
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //todo logger
        }
        return responseEntity;
    }

    /**
     * Deletes all the MatchOdds entries in the database for the match with the given {@code match ID}.
     *
     * @param matchID   the id of the match
     * @return          reference to the {@link ResponseEntity} containing the {@link HttpStatus}.
     */
    @DeleteMapping("/matches/{match_id}/match_odds")
    public ResponseEntity<HttpStatus> deleteAllMatchOddsOfMatch(@PathVariable(value = "match_id") Long matchID) {
        ResponseEntity<HttpStatus> responseEntity;

        if (matchRepository.existsById(matchID)) {
            matchOddsRepository.deleteByMatchId(matchID);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
