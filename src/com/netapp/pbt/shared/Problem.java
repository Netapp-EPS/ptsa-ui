package com.netapp.pbt.shared;

import java.io.Serializable;

public class Problem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7727945896819501218L;
	public String id;
	public String title;
	public String symptoms;
	public String solution;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	

}
