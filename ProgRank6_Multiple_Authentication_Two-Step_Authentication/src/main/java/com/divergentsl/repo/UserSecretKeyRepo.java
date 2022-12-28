package com.divergentsl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divergentsl.model.UserSecretKey;

public interface UserSecretKeyRepo extends JpaRepository<UserSecretKey, Integer> {
 Optional<UserSecretKey> findByUsername(String uname);
}
