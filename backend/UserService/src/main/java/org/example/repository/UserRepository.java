package org.example.repository;


import org.example.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByIdAndToken(Long id, String token);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByToken(String token);
}
