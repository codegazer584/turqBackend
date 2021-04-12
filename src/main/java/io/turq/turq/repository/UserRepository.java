package io.turq.turq.repository;

import io.turq.turq.entities.UserEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
  public UserEntity findByEmail(String email);
}
