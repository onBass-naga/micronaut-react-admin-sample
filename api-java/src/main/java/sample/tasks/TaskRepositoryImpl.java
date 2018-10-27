package sample.tasks;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import sample.domain.Task;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TaskRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<sample.tasks.Task> findAll() {
        String qlString = "SELECT t FROM Task as t";
        TypedQuery<Task> query = entityManager.createQuery(qlString, Task.class);
        query.setMaxResults(20);

        return query.getResultList().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<sample.tasks.Task> findById(Integer id) {
        return findRowById(id).map(this::toDomain);
    }

    private sample.tasks.Task toDomain(Task row) {
        return new TaskBuilder()
                .id(row.getId())
                .overview(row.getOverview())
                .deadline(row.getDeadline())
                .isDone(row.getDone())
                .create();
    }

    private Optional<Task> findRowById(Integer id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    @Override
    @Transactional
    public void create(sample.tasks.Task task) {
        Task dest = new Task();
        dest.setId(task.id);
        dest.setOverview(task.overview);
        dest.setDeadline(task.deadline);
        dest.setDone(task.isDone);
        entityManager.persist(dest);
    }

    @Override
    @Transactional
    public void update(sample.tasks.Task task) {
        findRowById(task.id).ifPresent(row -> {
            row.setOverview(task.overview);
            row.setDeadline(task.deadline);
            row.setDone(task.isDone);
            entityManager.merge(row);
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        findRowById(id).ifPresent(task -> entityManager.remove(task));
    }


}
