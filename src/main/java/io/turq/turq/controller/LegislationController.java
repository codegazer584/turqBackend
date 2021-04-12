package io.turq.turq.controller;

import io.turq.turq.contstants.UrlConstants;
import io.turq.turq.entities.LegislationEntity;
import io.turq.turq.model.legislation.LegislationRequest;
import io.turq.turq.service.interfaces.ILegislationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LegislationController {

    @Autowired
    private ILegislationService legislationService;

    @GetMapping(path = UrlConstants.LEGISLATION_URL, produces = "application/json")
    public ResponseEntity getLegislation() {
        List<LegislationEntity> legislation = legislationService.findAll();
        return ResponseEntity.ok(legislation);
    }
    @GetMapping(path = UrlConstants.LEGISLATION_URL + "/{id}", produces = "application/json")
    public ResponseEntity getContests(@PathVariable(value="id") Long id) {
        LegislationEntity contest = legislationService.findById(id);
        return ResponseEntity.ok(contest);
    }

    @PostMapping(path = UrlConstants.LEGISLATION_URL, consumes = "application/json", produces = "application/json")
    public ResponseEntity createLegislation(@RequestBody LegislationRequest req, @RequestHeader (name="Authorization") String token) {
        LegislationEntity legislation = legislationService.createLegislation(req, token);
        return ResponseEntity.ok(legislation);
    }

    @PutMapping(path = UrlConstants.LEGISLATION_URL + "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateLegislation(@RequestBody LegislationRequest req, @RequestHeader (name="Authorization") String token, @PathVariable long id) {
        LegislationEntity legislation = legislationService.updateLegislation(req, token, id);
        return ResponseEntity.ok(legislation);
    }

    @DeleteMapping(path = UrlConstants.LEGISLATION_URL + "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateLegislation(@RequestHeader (name="Authorization") String token, @PathVariable long id) {
        legislationService.deleteLegislation(token, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
