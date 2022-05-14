package com.multiplayerwordle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.multiplayerwordle.domain.SignUpEntity;

public interface SignUpRepository extends CrudRepository<SignUpEntity, Integer>{

	List<SignUpEntity> findByEmail(String email);
	
	
}
