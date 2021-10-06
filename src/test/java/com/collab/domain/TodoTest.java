package com.collab.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private Todo readTodo;
    private Todo writeTodo;
    private LocalDateTime localDateTime;
    private Timestamp timestamp;

    @BeforeEach
    public void setUp() {
        localDateTime = LocalDateTime.now();
        timestamp = Timestamp.valueOf(localDateTime);
        readTodo = new Todo(1, "Otsikko", "sisältää juu", "false", timestamp);
        writeTodo = new Todo("Uusi Todo", "Kirjoitettava otsikko");
    }

    @Test
    void testHeading() {
        assertEquals("Otsikko", readTodo.getHeading());
        assertEquals("Uusi Todo", writeTodo.getHeading());
    }


    @Test
    void testGetId() {
        assertEquals(1, readTodo.getId());
        assertEquals(-1, writeTodo.getId());
    }


    @Test
    void testSetContent() {
        assertNotEquals(" ", readTodo.getContent());
        assertEquals("sisältää juu", readTodo.getContent());
        readTodo.setContent("muutettu sisältö");
        assertEquals("muutettu sisältö", readTodo.getContent());
        readTodo.setContent("muutettu toisen kerran");
        assertNotEquals("muutettu sisältö", readTodo.getContent());
        assertEquals("muutettu toisen kerran", readTodo.getContent());
    }

    @Test
    void testToggleDone() {
        assertFalse(readTodo.isDone());
        readTodo.toggleDone();
        assertTrue(readTodo.isDone());
        readTodo.toggleDone();
        assertFalse(readTodo.isDone());
    }

    @Test
    void doneConvertsToBoolean() {
        assertFalse(readTodo.convertStrToBool("false"));
        assertTrue(readTodo.convertStrToBool("true"));
    }
}
