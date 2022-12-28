package com.divergentsl.springsecuritydemo2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergentsl.springsecuritydemo2.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * The method returns an Optional instance containing the User entity with the
	 * name provided as a parameter. If no such user exists in the database, the
	 * method returns an empty Optional instance.
	 * @return Optional<User> 
	 */
	Optional<User> findUserByUsername(String u);
}
