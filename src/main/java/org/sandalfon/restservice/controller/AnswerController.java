package org.sandalfon.restservice.controller;



import javax.validation.Valid;


import org.sandalfon.restservice.entity.Answer;
import org.sandalfon.restservice.entity.Question;
import org.sandalfon.restservice.repository.AnswerRepository;
import org.sandalfon.restservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AnswerController {

	
	  @Autowired
	    private AnswerRepository answerRepository;

	    @Autowired
	    private QuestionRepository questionRepository;

	   @GetMapping("/questions/{questionId}/answers")
	    public Page<Answer> getAllAnswersByQuestionId(@PathVariable (value = "questionId") Long questionId,
	                                                Pageable pageable) {
	        return  answerRepository.findByQuestionId(questionId, pageable);
	    }
	    

	   

	    
	    
	    @PostMapping("/questions/{questionId}/answers")
	    public Answer createAnswer(@PathVariable (value = "questionId") Long questionId,
	                                 @Valid @RequestBody Answer answer) {
	    	Question question = questionRepository.findById(questionId);
	    	if(question == null)
	    		throw  new  EmptyResultDataAccessException(1);
	    	answer.setQuestion(question);
	    	return answerRepository.save(answer);
	       
	    }

	    @PutMapping("/questions/{questionId}/answers/{answerId}")
	    public Answer updateAnswer(@PathVariable (value = "questionId") Long questionId,
	                                 @PathVariable (value = "answerId") Long answerId,
	                                 @Valid @RequestBody Answer answertRequest) {
	    	if(!questionRepository.existsById(questionId)) 
	        	throw  new  EmptyResultDataAccessException(1);
	    	
	    	Answer answer = answerRepository.findById(answerId);
	    	answer.setText(answertRequest.getText());
	            return answerRepository.save(answer);
	         }

	    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
	    public ResponseEntity<?> deleteComment(@PathVariable (value = "questionId") Long questionId,
	                              @PathVariable (value = "answerId") Long answerId) {
	        if(!questionRepository.existsById(questionId))
	        	throw  new  EmptyResultDataAccessException(1);
	        Answer answer = answerRepository.findById(answerId);
	        answerRepository.delete(answer);
            return ResponseEntity.ok().build();}
	
}
