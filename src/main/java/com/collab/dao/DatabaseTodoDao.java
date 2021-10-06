package com.collab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.collab.domain.Todo;

/**
 * Dao for todo
 */
public class DatabaseTodoDao implements Dao<Todo, Integer> {
    private Connection connection;
    private List<Todo> todos;

    public DatabaseTodoDao(Connection connection) {
        this.connection = connection;
        this.todos = new ArrayList<>();
    }

    /* Create new todo */
    @Override
    public void create(Todo todo) throws SQLException {
        PreparedStatement pStatement = connection
                .prepareStatement("INSERT INTO Todos (heading, content, done, time_stamp) VALUES (?, ?, ?, ?);");
        pStatement.setString(1, todo.getHeading());
        pStatement.setString(2, todo.getContent());
        pStatement.setString(3, todo.convertDone());
        /* pStatement.setString(4, todo.getDate().toString()); */
        pStatement.setTimestamp(4, todo.localDateTimeToTimeStamp());

        pStatement.executeUpdate();
        pStatement.close();

    }

    /* Read existing todo by id */
    @Override
    public Todo read(Integer id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM Todos WHERE id = ?;");
        pStatement.setInt(1, id);
        ResultSet resultSet = pStatement.executeQuery();

        return resultSet.next()
                ? new Todo(resultSet.getInt("id"), resultSet.getString("heading"), resultSet.getString("content"),
                        resultSet.getString("done"), resultSet.getTimestamp("time_stamp"))
                : null;
    }

    /* Update existing todo */
    @Override
    public void update(Todo todo) throws SQLException {
        PreparedStatement pStatement = connection
                .prepareStatement("UPDATE Todos SET heading = ?, content = ?, done = ? WHERE id = ?;");
        pStatement.setString(1, todo.getHeading());
        pStatement.setString(2, todo.getContent());
        pStatement.setString(3, todo.convertDone());
        pStatement.setInt(4, todo.getId());

        pStatement.executeUpdate();

    }

    /* Delete existing todo by id */
    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM Todos WHERE id = ?");
        pStatement.setInt(1, id);
        pStatement.executeUpdate();
    }

    /* List all todos. Updates list and returns it */
    @Override
    public List<Todo> list() throws SQLException {
        todos.clear();
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM Todos;");
        ResultSet resultSet = pStatement.executeQuery();
        while (resultSet.next()) {
            todos.add(new Todo(resultSet.getInt("id"), resultSet.getString("heading"), resultSet.getString("content"),
                    resultSet.getString("done"), resultSet.getTimestamp("time_stamp")));
        }
        return todos;
    }

}
