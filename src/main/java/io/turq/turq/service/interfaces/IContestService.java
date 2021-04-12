package io.turq.turq.service.interfaces;

import io.turq.turq.entities.ContestEntity;
import io.turq.turq.entities.LegislationEntity;
import io.turq.turq.model.contest.ContestRequest;
import io.turq.turq.model.contest.ContestUpdateRequest;

import java.util.List;

public interface IContestService {
    List<ContestEntity> findAll();
    ContestEntity findById(long id);
    List<LegislationEntity> getLegislationByContest(long contestId);
    ContestEntity createContest(ContestRequest req, String token);
    ContestEntity updateContest(ContestRequest req, String token, long id);
    void deleteContest(String token, long id);
}
