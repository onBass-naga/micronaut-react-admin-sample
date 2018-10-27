package sample.tasks;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task create(Task task) {
        Integer id = VulnerableIdGenerator.next();
        task.id = id;
        taskRepository.create(task);
        return task;
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

    public void delete(Integer id) {
        taskRepository.delete(id);
    }
}
