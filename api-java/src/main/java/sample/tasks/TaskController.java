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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.micronaut.http.HttpResponse.*;

@Controller("/tasks")
public class TaskController {

    private TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Get("{?searchCommand*}")
    public HttpResponse<Collection<Task>> list(@Nullable SearchCommand searchCommand) {
        System.out.println(searchCommand);

        Collection<Task> tasks = taskService.findAll();

        return ok(tasks)
                .header(ContentRange.NAME,
                        ContentRange.value("tasks", 0, tasks.size(), tasks.size()));
    }

    @Get("/{id}")
    public Maybe<Task> find(Integer id) {
        Task task = taskService.findById(id);
        return task == null ? Maybe.empty() : Observable.just(task).firstElement();
    }

    @Post
    public HttpResponse<Task> create(@Body Task task) {
        Task created = taskService.create(task);
        return created(created);
    }

    @Put("/{id}")
    public HttpResponse<Task> update(Integer id, @Body Task task) {
        taskService.update(task);
        return ok(task);
    }

    @Delete("/{id}")
    public HttpResponse<Map<String, Integer>> delete(Integer id) {
        taskService.delete(id);
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

        parsed.id.forEach(id -> taskService.delete(id));
        return ok(parsed.id);
    }

}
