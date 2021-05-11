package io.turq.turq.service.impl;

import io.turq.turq.contstants.APIErrors;
import io.turq.turq.entities.ContestStatusEntity;
import io.turq.turq.exceptions.ContestStatusInvalidException;
import io.turq.turq.repository.ContestStatusRepository;
import io.turq.turq.service.interfaces.IContestStatusService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestStatusService implements IContestStatusService {

    @Autowired
    private ContestStatusRepository repository;

    @Override
    public ContestStatusEntity findById(long id) {
        Optional<ContestStatusEntity> status = repository.findById(id);
        if (status.isEmpty()) {
            throw new ContestStatusInvalidException(
                APIErrors.CONTEST_STATUS_INVALID
            );
        }
        return status.get();
    }
}
