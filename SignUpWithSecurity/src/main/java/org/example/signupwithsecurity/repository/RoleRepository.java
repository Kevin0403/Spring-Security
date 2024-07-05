package org.example.signupwithsecurity.repository;

import org.example.signupwithsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r from Role r where r.name = :name")
    Optional<Role> findByName(@Param("name") String name);

}
