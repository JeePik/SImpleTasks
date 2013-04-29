package com.simple.task;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tasks")
public class Task {

	// for QueryBuilder to be able to find the fields
	public static final String TITLE_FIELD_NAME = "title";
	public static final String PRIORITY_FIELD_NAME = "priority";



    @DatabaseField(generatedId = true)
	private int id;

    @DatabaseField(columnName = TITLE_FIELD_NAME, canBeNull = false)
    private String title;
    
    @DatabaseField(columnName = PRIORITY_FIELD_NAME)
	private int priority;

    public Task() {
		// all persisted classes must define a no-arg constructor with at least package visibility
	}


	public Task(String title) {
		this.title = title;
	}


	public Task(String title, int priority) {
		this.title = title;
		this.priority = priority;
	}


	public int getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}



	public int hashCode() {
		return title.hashCode();
	}



	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}


		if (other == this) {
			return true;
		}
		return title.equals(((Task) other).title) && 
		       priority == ((Task) other).priority;
	}
}
