package com.divergentsl.authentication.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.divergentsl.authentication.model.User;

public interface ClientRepo extends CrudRepository<User, Integer> {

	public Optional<User> findByUsername(Integer username);
}
