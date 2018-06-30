package org.sandalfon.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.sandalfon.restservice.entity.Question;

@RepositoryRestResource(path = "/questions")
public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findById(Long questionId);

	boolean existsById(Long questionId);
	
}