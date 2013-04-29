package com.simple.task;


import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleTasks {
	private TaskDao taskDao;
	Task newTask;
	List<Task> tasks;
	int id;
	
	@Before
	public void setUp() throws Exception {
		taskDao = new TaskDao("test");
		newTask = new Task("Task1", 500);
		taskDao.createTask(newTask);
	}

	@After
	public void tearDown() throws Exception {
		id = newTask.getId();
		taskDao.deleteTaskById(id);
		taskDao.closeConnection();
	}
	
	@Test
	public void testCreateTask()throws SQLException {
		tasks = taskDao.getTaskByName(newTask.getTitle());
		Assert.assertEquals("Task1",tasks.get(0).getTitle());
		Assert.assertEquals(500, tasks.get(0).getPriority());
	}
	
	@Test
	public void testDeleteTask()throws SQLException {
		id = newTask.getId();
		taskDao.deleteTaskById(id);
		tasks = taskDao.getAllTasks();
		Assert.assertEquals(0, tasks.size());
	}
}
