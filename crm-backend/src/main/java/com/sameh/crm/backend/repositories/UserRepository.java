package com.sameh.crm.backend.repositories;

import com.sameh.crm.backend.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    public boolean existsByUsernameOrEmail(String username, String email);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

}
