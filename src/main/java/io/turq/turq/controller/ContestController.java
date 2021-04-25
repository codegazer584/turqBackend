package io.turq.turq.controller;

import io.turq.turq.contstants.UrlConstants;
import io.turq.turq.entities.ContestEntity;
import io.turq.turq.entities.LegislationEntity;
import io.turq.turq.model.contest.ContestRequest;
import io.turq.turq.model.contest.ContestUpdateRequest;
import io.turq.turq.service.interfaces.IContestService;
import io.turq.turq.service.interfaces.ILegislationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ContestController {

    @Autowired
    private IContestService contestService;

    @Autowired
    private ILegislationService legislationService;

    @GetMapping(path = UrlConstants.CONTEST_URL, produces = "application/json")
    public ResponseEntity getContests() {
        List<ContestEntity> contests = contestService.findAll();
        return ResponseEntity.ok(contests);
    }

    @GetMapping(path = UrlConstants.CONTEST_URL + "/{id}", produces = "application/json")
    public ResponseEntity getContests(@PathVariable(value="id") Long id) {
        ContestEntity contest = contestService.findById(id);
        return ResponseEntity.ok(contest);
    }

    @GetMapping(path = UrlConstants.CONTEST_URL + "/{id}/legislation", produces = "application/json")
    public ResponseEntity getContestLegislation(@PathVariable(value="id") Long id) {
        List<LegislationEntity> legislation = contestService.getLegislationByContest(id);
        return ResponseEntity.ok(legislation);
    }

    @PostMapping(path = UrlConstants.CONTEST_URL, consumes = "application/json", produces = "application/json")
    public ResponseEntity createContest(@RequestBody ContestRequest req, @RequestHeader (name="Authorization") String token) {
        ContestEntity contest = contestService.createContest(req, token);
        return ResponseEntity.ok(contest);
    }

    @PutMapping(path = UrlConstants.CONTEST_URL + "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateContest(@RequestBody ContestRequest req, @RequestHeader (name="Authorization") String token, @PathVariable long id) {
        ContestEntity contest = contestService.updateContest(req, token, id);
        return ResponseEntity.ok(contest);
    }

    @PostMapping(path = UrlConstants.CONTEST_URL + "/{id}/updateStatus", consumes="application/json", produces = "application/json")
    public ResponseEntity updateContestStatus(@RequestBody ContestUpdateRequest req, @RequestHeader (name="Authorization") String token, @PathVariable long id) {

        ContestEntity contest = contestService.updateStatus(req, token, id);
        return ResponseEntity.ok(contest);
    }

    @DeleteMapping(path = UrlConstants.CONTEST_URL + "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateContest(@RequestHeader (name="Authorization") String token, @PathVariable long id) {
        contestService.deleteContest(token, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
