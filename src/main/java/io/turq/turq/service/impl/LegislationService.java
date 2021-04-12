package io.turq.turq.service.impl;

import io.turq.turq.config.JwtTokenUtil;
import io.turq.turq.contstants.APIErrors;
import io.turq.turq.entities.ContestEntity;
import io.turq.turq.entities.LegislationEntity;
import io.turq.turq.entities.UserEntity;
import io.turq.turq.exceptions.LegislationNotFoundException;
import io.turq.turq.exceptions.ForbiddenException;
import io.turq.turq.model.legislation.LegislationRequest;
import io.turq.turq.repository.LegislationRepository;
import io.turq.turq.service.interfaces.IContestService;
import io.turq.turq.service.interfaces.ILegislationService;
import io.turq.turq.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LegislationService implements ILegislationService {

    @Autowired
    private LegislationRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IContestService contestService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<LegislationEntity> findAll() {
        List<LegislationEntity> contest = (List<LegislationEntity>) repository.findAll();
        return contest;
    }

    @Override
    public List<LegislationEntity> findByContest(long contestId) {
        List<LegislationEntity> legislation = repository.findByContest(contestId);
        return legislation;
    }

    @Override
    public LegislationEntity findById(long id) {
       Optional<LegislationEntity> legislation = repository.findById(id);
        if (legislation.isEmpty()) {
            throw new LegislationNotFoundException(APIErrors.LEGISLATION_NOT_FOUND);
        }
       return legislation.get();
    }

    @Override
    public LegislationEntity createLegislation(LegislationRequest req, String token) {
        LegislationEntity retLegislation = null;
        String authorEmail = jwtTokenUtil.getSubject(token);
        try {
            UserEntity author = userService.findByEmail(authorEmail);
            ContestEntity contest = contestService.findById(req.getContestId());
            LegislationEntity legislation = new LegislationEntity(req.getTitle(), req.getChapter(), req.getSection(), req.getAccomplishes(), req.getTerms(), req.getPurpose(), req.getProvisions(), req.getExceptions(), req.getOther(), author, contest);
            retLegislation = repository.save(legislation);
        } catch (DataIntegrityViolationException e){
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            retLegislation = null;
        }
        return retLegislation;
    }

    @Override
    public LegislationEntity updateLegislation(LegislationRequest req, String token, long id) {
        LegislationEntity retLegislation = null;
        String authorEmail = jwtTokenUtil.getSubject(token);
        System.out.println(req.getExceptions());
        try {
            LegislationEntity legislation = this.findById(id);
            if (legislation == null) {
                throw new LegislationNotFoundException(APIErrors.CONTEST_NOT_FOUND);
            } else if (!legislation.getAuthor().getEmail().equalsIgnoreCase(authorEmail)) {
                throw new ForbiddenException(APIErrors.LEGISLATION_UPDATE_PERMISSION);
            } else {
                retLegislation = repository.save(new LegislationEntity(legislation.getId(), req.getTitle(), req.getChapter(), req.getSection(), req.getAccomplishes(), req.getTerms(), req.getPurpose(), req.getProvisions(), req.getExceptions(), req.getOther(), legislation.getAuthor(), legislation.getContest()));
            }
        } catch (DataIntegrityViolationException e){
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            retLegislation = null;
        }
        return retLegislation;
    }

    @Override
    public void deleteLegislation(String token, long id) {
        String authorEmail = jwtTokenUtil.getSubject(token);
        try {
            LegislationEntity legislation = this.findById(id);
            if (legislation == null) {
                throw new LegislationNotFoundException(APIErrors.CONTEST_NOT_FOUND);
            } else if (!legislation.getAuthor().getEmail().equalsIgnoreCase(authorEmail)) {
                throw new ForbiddenException(APIErrors.LEGISLATION_UPDATE_PERMISSION);
            } else {
                repository.delete(legislation);
            }
        } catch (DataIntegrityViolationException e){
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
        }
        return;
    }
}
