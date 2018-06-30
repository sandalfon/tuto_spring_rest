package org.sandalfon.restservice.repository;

import org.sandalfon.restservice.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/answers")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	 Page<Answer> findByQuestionId(Long questionId, Pageable pageable);

	Answer findById(Long answerId);
}