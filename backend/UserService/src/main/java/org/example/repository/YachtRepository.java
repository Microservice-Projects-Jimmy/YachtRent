package org.example.repository;

import org.example.entity.YachtEntity;
import org.springframework.data.repository.CrudRepository;

public interface YachtRepository extends CrudRepository<YachtEntity, Long> {
}
