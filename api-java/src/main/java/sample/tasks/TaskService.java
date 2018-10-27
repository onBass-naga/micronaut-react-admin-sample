package sample.tasks;

import javax.inject.Singleton;
import java.time.ZonedDateTime;
import java.util.*;

@Singleton
public class TaskService {

    Map<Integer, Task> inMemoryDatastore = new LinkedHashMap<>();

    {
        Task task1 = new TaskBuilder()
                .id(1)
                .overview("Learn Micronaut")
                .deadline(ZonedDateTime.now())
                .create();
        inMemoryDatastore.put(task1.id, task1);

        Task task2 = new TaskBuilder()
                .id(2)
                .overview("Learn react-admin")
                .deadline(ZonedDateTime.now())
                .create();
        inMemoryDatastore.put(task2.id, task2);
    }

    public Collection<Task> findAll() {
        return inMemoryDatastore.values();
    }

    public Task findById(Integer id) {
        return inMemoryDatastore.get(id);
    }

    public Task create(Task task) {
        Integer id = inMemoryDatastore.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        task.id = id;
        inMemoryDatastore.put(id, task);
        return task;
    }

    public void update(Task task) {
        inMemoryDatastore.put(task.id, task);
    }

    public void delete(Integer id) {
        inMemoryDatastore.remove(id);
    }
}
