package com.brettchild.springmvc.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;



@Entity
@Table(appliesTo = "THING")
public class Thing {

	@Column
	@GeneratedValue
	private Integer id = 0;
	@Column
	private String name = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
