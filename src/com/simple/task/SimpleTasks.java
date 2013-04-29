package com.simple.task;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SimpleTasks {
	private static PrintStream out;
	static boolean exit = true;
	
	 public TaskDao taskDao;

	    public SimpleTasks() throws Exception{
	        taskDao = new TaskDao();
	        out = System.out;
	    }

	    public void exec() throws SQLException{
		        while (exit) {
		            int operation = inpCommand();
		            if (operation==1) {
		            	createTask();
		            	out.println("Saved in database.");
		            } else if (operation==2) {
		            	showTasksList();
		            } else if (operation==3) {
		            	deleteTask();
		            	showTasksList();
		            } else if (operation==4) {
		            	stop();
		            }
		        }    
	    }
	    
	    public void stop() {
			exit = false;
		}

	    public void createTask() throws SQLException {
	        Task newTask = inpTask();
	        taskDao.createTask(newTask);
	    }

	    public int deleteTask() throws SQLException {
	    	int id;
	    	while (true) {
	    		try {
	    			out.println("Enter some number of task to delete:");
	    			String taskId = getInput();
	             	id = Integer.parseInt(taskId);
	            	taskDao.deleteTaskById(id);
	            	return id;
	    		} catch (NumberFormatException ex) {
	    			out.println("Error! Input must be a number.");
	    		}
	    	}
	    }


	    public void showTasksList() throws SQLException {
	        List<Task> taskList = taskDao.getAllTasks();
	        showList(taskList);
	    }

	    private void  closeConnection() throws  SQLException{
	        taskDao.closeConnection();
	    }
	    
	    public static String getInput() {
	        Scanner scan = new Scanner(System.in);
	        return scan.next();
	    }

	    public static int inpCommand() {    
        	while(true){
	    	try{
	    		Scanner in = new Scanner(System.in);
        		out.println("Choose operation:");
        		out.print(" [1] - New task |");  
        		out.print(" [2] - List of tasks |");
        		out.print(" [3] - Delete task |");
        		out.print(" [4] - Exit |");
        		out.println();
       	 		int input = in.nextInt();
	        return input;
        	}catch(Exception e){
        		out.println("Input must be in interval from 1 till 4.");
        	}
          }
	    }

	    public static Task inpTask() {
	        out.println("Enter some new task title:");
	        String title = getInput();
	        out.println("Enter task priority:");
	        String prio = getInput();
	        int priority = Integer.parseInt(prio);
	        return new Task(title, priority);
	    }

	    public static void showList(List<Task> taskList) {
	       String space = "       ";
	        out.println("    # Task     |"+"    Priority    |" +"   Title   ");
	        out.println("----------------------------------------------- ");
	        for (Task task  : taskList) {
	        	out.print(space);
	            out.print(task.getId());
	            out.print(space+"|"+space);
	            out.print(task.getPriority());
	            out.print(space+"|  ");
	            out.println(task.getTitle());
	            out.println("----------------------------------------------- ");
	        }
	    }
	    
	    public static void main(String[] args) throws Exception {
	    	SimpleTasks simpt = new SimpleTasks();
	        simpt.exec();
	        simpt.closeConnection();
	    }
	}