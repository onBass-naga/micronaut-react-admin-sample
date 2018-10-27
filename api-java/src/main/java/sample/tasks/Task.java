package sample.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Task {
    public Integer id;
    public String overview;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    public ZonedDateTime deadline;
    @JsonProperty(value="isDone")
    public boolean isDone;

    public Task(){}

    public Task(Integer id, String overview, ZonedDateTime deadline, boolean isDone) {
        this.id = id;
        this.overview = overview;
        this.deadline = deadline;
        this.isDone = isDone;
    }
}
