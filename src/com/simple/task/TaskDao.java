package com.simple.task;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class TaskDao  {
    private String DATABASE_URL = "jdbc:sqlite:tasks.db";
    public Dao<Task, Integer> taskDao;
    private ConnectionSource connectionSource;

    public TaskDao() throws Exception{
    	DATABASE_URL = "jdbc:sqlite:simpletasks.db";
		System.setProperty(LocalLog.LOCAL_LOG_FILE_PROPERTY, "log.out");
		connectionSource = conDb();
		connectionSource.close();
    }

    public TaskDao(String databaseName) throws Exception{
    	DATABASE_URL = "jdbc:sqlite:" + databaseName + ".db";
		System.setProperty(LocalLog.LOCAL_LOG_FILE_PROPERTY, "log.out");
		connectionSource = conDb();
		connectionSource.close();
    }
    
    public void createTask(Task task) throws SQLException {
        taskDao.create(task);
    }

    public void deleteTaskById(int id) throws SQLException {
        taskDao.deleteById(id);
    }

    public List<Task> getAllTasks() throws SQLException {
        return taskDao.queryForAll();
    }

    public List<Task> getTaskByName(String title) throws SQLException {
        QueryBuilder<Task, Integer> statementBuilder = taskDao.queryBuilder();
        statementBuilder.where().eq(Task.TITLE_FIELD_NAME, title);
        return taskDao.query(statementBuilder.prepare());
    }

    public Task getTaskById(int id) throws SQLException {
        return taskDao.queryForId(id);
    }

	private ConnectionSource conDb() throws Exception {
		ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL);
		taskDao = DaoManager.createDao(connectionSource, Task.class);
		TableUtils.createTableIfNotExists(connectionSource, Task.class);
		return connectionSource;
	}
	
    public void closeConnection() throws SQLException {
        connectionSource.close();
    }

}