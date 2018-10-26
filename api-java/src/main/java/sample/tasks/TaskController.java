package sample.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import sample.web.ContentRange;
import sample.web.DeleteFilter;
import sample.web.SearchCommand;

import javax.annotation.Nullable;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

import static io.micronaut.http.HttpResponse.created;
import static io.micronaut.http.HttpResponse.ok;
import static io.micronaut.http.HttpResponse.serverError;

@Controller("/tasks")
public class TaskController {

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

    @Get("{?searchCommand*}")
    public HttpResponse<Collection<Task>> list(@Nullable SearchCommand searchCommand) {
        System.out.println(searchCommand);

        Collection<Task> tasks = inMemoryDatastore.values();

        return ok(tasks)
                .header(ContentRange.NAME,
                        ContentRange.value("tasks", 0, tasks.size(), tasks.size()));
    }

    @Get("/{id}")
    public Maybe<Task> find(Integer id) {
        Task task = inMemoryDatastore.get(id);
        return task == null ? Maybe.empty() : Observable.just(task).firstElement();
    }

    @Post
    public HttpResponse<Task> create(@Body Task task) {

        Integer id = inMemoryDatastore.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        task.id = id;
        inMemoryDatastore.put(id, task);
        return created(task);
    }

    @Put("/{id}")
    public HttpResponse<Task> update(Integer id, @Body Task task) {
        inMemoryDatastore.put(task.id, task);
        return ok(task);
    }

    @Delete("/{id}")
    public HttpResponse<Map<String, Integer>> delete(Integer id) {
        inMemoryDatastore.remove(id);
        Map<String, Integer> response = new HashMap<>();
        response.put("id", id);
        return ok(response);
    }

    @Delete
    public HttpResponse<List<Integer>> deleteAll(@QueryValue String filter) {

        ObjectMapper mapper = new ObjectMapper();
        DeleteFilter parsed = null;
        try {
            parsed = mapper.readValue(filter, DeleteFilter.class);
        } catch (IOException e) {
            e.printStackTrace();
            return serverError();
        }

        parsed.id.forEach(id -> inMemoryDatastore.remove(id));
        return ok(parsed.id);
    }

}
