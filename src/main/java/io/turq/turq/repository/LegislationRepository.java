package io.turq.turq.repository;

import io.turq.turq.entities.LegislationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LegislationRepository extends CrudRepository<LegislationEntity, Long> {

    @Query("SELECT l FROM LegislationEntity l WHERE contest_id = ?1")
    List<LegislationEntity> findByContest(long contestId);

    @Query("SELECT l FROM LegislationEntity l WHERE author_id = ?1")
    List<LegislationEntity> findByAuthor(long authorId);
}
