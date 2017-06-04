package com.Core.vaadin.pushServer.ejemploPushMarkus;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class Card {
    public enum State {
        TODO, IN_PROGRESS, DONE
    }

    public State state;
    public String taskName;

    public Card(State state, String taskName) {
        this.state = state;
        this.taskName = taskName;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
