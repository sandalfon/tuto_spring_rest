package org.sandalfon.restservice.controller;

import org.sandalfon.restservice.entity.Question;
import org.sandalfon.restservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;

	@GetMapping("/q/{questionId}")
	public Question getQuestion(@PathVariable Long questionId) {
		return  questionRepository.findById(questionId);
	}

	@RequestMapping(method = RequestMethod.GET,
			value = "/questions/{questionId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> greeting(@PathVariable Long questionId) {

	System.out.println(questionRepository.findById(questionId).toString());
		return new ResponseEntity<>(questionRepository.findById(questionId), HttpStatus.OK);
	}


	@GetMapping("/questions/")
	public Page<Question> getAllQuestions(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}

	@PostMapping("/questions")
	public Question createQuestion(@Valid @RequestBody Question question) {
		return questionRepository.save(question);
	}

	@PutMapping("/questions/{questionId}")
	public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question questionRequest) {

		Question question = questionRepository.findById(questionId);

		if(!questionRepository.existsById(questionId)) 
			throw  new  EmptyResultDataAccessException(1);
		question.setText(questionRequest.getText());
		return questionRepository.save(question);

	}


	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		Question question = questionRepository.findById(questionId);

		if(!questionRepository.existsById(questionId)) 
			throw  new  EmptyResultDataAccessException(1);
		questionRepository.delete(question);
		return ResponseEntity.ok().build();

	}

}