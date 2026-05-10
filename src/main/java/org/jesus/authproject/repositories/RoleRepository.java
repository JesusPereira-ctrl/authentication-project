package org.jesus.authproject.repositories;

import org.jesus.authproject.entities.RoleEntity;
import org.jesus.authproject.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum role);
}
