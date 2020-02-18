package com.example.demo.model;

import java.io.Serializable;

public class Todo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;

	private String name;

	private boolean completed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	
	

	


}