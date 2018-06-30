package org.sandalfon.restservice.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
private String text;

public String getText() {
    return this.text;
}

public void setText(String text) {
    this.text = text;
}

public Long getId() {
    return this.id;
}


@Override
public String toString() {
    return "Question{" + "id='" + this.id + ":: " +this.text+ " }";
}

}
