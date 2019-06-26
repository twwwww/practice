package com.tww.test.arithmetic.timing_wheel;

import java.util.LinkedList;
import java.util.List;

/**
 *时刻
 */
public class TimeScale {
    private List<Task> tasks;
    private Integer timeIndex;

    public TimeScale(Integer timeIndex) {
        this.timeIndex = timeIndex;
        tasks = new LinkedList<>();
    }

    public void scheduleTask() {
        tasks.forEach(Task::execute);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Integer getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(Integer timeIndex) {
        this.timeIndex = timeIndex;
    }
}
