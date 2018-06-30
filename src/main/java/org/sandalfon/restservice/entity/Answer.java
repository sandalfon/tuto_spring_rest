package org.sandalfon.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Answer {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	Question question;
	
	public Long getId() {
	    return this.id;
	}
	
	public String getText() {
	    return this.text;
	}

	public void setText(String text) {
	    this.text = text;
	}
	
	public Question getQuestion() {
	      return question;
	   }

	   public void setQuestion(Question question) {
	      this.question = question;
	   }
	   
	   @Override
	   public String toString() {
	       return "Answer{ id="+this.id+":: "+this.text+" }";
	   }

	
}
