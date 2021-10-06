package com.collab.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo {
    private int id;
    private String heading;
    private String content;
    private boolean done;
    private LocalDateTime localTime;
    // date when created
    // start date
    // expiration date

    /**
     * Constructors for getting tododao.
     * 
     * @param id      primary key
     * @param heading heading gor todo
     * @param content actual content for todo
     * @param done    is todo done @return true/false
     */

    /**
     * Constructor for reading tododao
     */
    public Todo(int id, String heading, String content, String done, Timestamp timestamp) {
        this.id = id;
        this.heading = heading;
        this.content = content;
        this.done = convertStrToBool(done);
        this.localTime = timeStampToLocaltime(timestamp);
    }

    /**
     * Construtor when creating new tododao
     */
    public Todo(String heading, String content) {
        this.id = -1;
        this.heading = heading;
        this.content = content;
        this.done = false;
        this.localTime = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return localTime;
    }

    public Timestamp localDateTimeToTimeStamp() {
        return Timestamp.valueOf(localTime);
    }

    public LocalDateTime timeStampToLocaltime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public int getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * Toggles the value of done, true to false, false to true. No parameters
     */
    public void toggleDone() {
        this.done = done ? false : true;
    }

    /**
     * Converts current boolean done value to String value, for sqlite database
     */
    public String convertDone() {
        return String.valueOf(done);
    }

    /**
     * Converts given string value to bool if applicable
     */
    public boolean convertStrToBool(String n) {
        return Boolean.valueOf(n);
    }

    /* Do we really need this? */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "id: " + id + "\nHeading: " + heading + "\nDone? " + done + "\nTime created: "
                + localTime.format(formatter);
    }

}
