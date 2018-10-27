package sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private Integer id;

    @NotNull
    @Column(name = "overview", nullable = false)
    private String overview;

    @NotNull
    @Column(name = "deadline", nullable = false)
    private ZonedDateTime deadline;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    public Task(){}

    public Task(Integer id, String overview, ZonedDateTime deadline, boolean isDone) {
        this.id = id;
        this.overview = overview;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", overview='" + overview + '\'' +
                ", deadline=" + deadline +
                ", isDone=" + isDone +
                '}';
    }
}