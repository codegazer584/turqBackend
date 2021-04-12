package io.turq.turq.repository;

import io.turq.turq.entities.PaymentsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<PaymentsEntity, Long> {

}
