package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Task;

import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Task getTaskById(String id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public void addTask(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    @Override
    public Task updateTask(Task task) {
        sessionFactory.getCurrentSession().update(task);
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        Object findAAgain = session.merge(task);
        session.delete(findAAgain);
    }

    @Override
    public List<Task> getTasksByParent(String parentId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Task where parent.id=:id");
        query.setParameter("id", parentId);
        return query.getResultList();
    }

    @Override
    public List<Task> getTasksByChild(Child child) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Task t where :child in elements (t.children)");
        query.setParameter("child", child);
        return query.getResultList();
    }
}
