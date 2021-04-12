package io.turq.turq.service.interfaces;

import io.turq.turq.entities.ContestEntity;
import io.turq.turq.entities.LegislationEntity;
import io.turq.turq.model.legislation.LegislationRequest;

import java.util.List;

public interface ILegislationService {
    List<LegislationEntity> findAll();
    LegislationEntity findById(long id);
    List<LegislationEntity> findByContest(long contestId);
    LegislationEntity createLegislation(LegislationRequest req, String token);
    LegislationEntity updateLegislation(LegislationRequest req, String token, long id);
    void deleteLegislation(String token, long id);
}
