package com.jerome.practice.stream;

import java.util.Arrays;
import java.util.Collection;

public class Task {
    private enum Status {
        OPEN, CLOSE
    }
    private final Status status;
    private final Integer point;

    public Task(Status status, Integer point) {
        this.status = status;
        this.point = point;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", status, point);
    }

    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.CLOSE, 4),
                new Task(Status.OPEN, 9)
        );
        final long openTasksPoints = tasks.stream().filter(t -> t.getStatus() == Status.OPEN).mapToInt(Task::getPoint).sum();
        System.out.println("" + openTasksPoints);

        // Calculate total points of all tasks
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map( task -> task.getPoint() ) // or map( Task::getPoint )
                .reduce( 0, Integer::sum );

        System.out.println( "Total points (all tasks): " + totalPoints );
    }
}

