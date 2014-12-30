package ru.chand.simpletodo;

import java.util.Date;

/**
 * Created by chandrav on 12/29/14.
 */
public class TodoItem {
    private long id;
    private String body;
    private int priority;
    private Date completionDate;

    public TodoItem(String body, int priority, Date dueDate) {
        super();
        this.body = body;
        this.priority = priority;
        this.completionDate = dueDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.body + " [" + this.priority + "]";
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}