package io.turq.turq.service.interfaces;
import io.turq.turq.entities.ContestStatusEntity;

public interface IContestStatusService {
    ContestStatusEntity findById(long id);
}
