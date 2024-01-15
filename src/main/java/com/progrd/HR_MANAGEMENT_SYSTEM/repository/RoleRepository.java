package com.progrd.HR_MANAGEMENT_SYSTEM.repository;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
