package sample.tasks;

import java.time.ZonedDateTime;

public class TaskBuilder {
    private Integer id;
    private String overview;
    private ZonedDateTime deadline;
    private boolean isDone;

    public TaskBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public TaskBuilder overview(String overview) {
        this.overview = overview;
        return this;
    }

    public TaskBuilder deadline(ZonedDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public TaskBuilder isDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Task create() {
        return new Task(id, overview, deadline, isDone);
    }
}