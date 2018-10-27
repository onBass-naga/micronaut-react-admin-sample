package sample.tasks;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();
    Optional<Task> findById(Integer id);
    void create(Task task);
    void update(Task task);
    void delete(Integer id);
}
