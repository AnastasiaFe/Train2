package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Task;
import ua.nure.fedorenko.kidstim.model.entity.TaskStatus;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.TaskService;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.TaskDTO;
import ua.nure.fedorenko.kidstim.service.mapper.ChildMapper;
import ua.nure.fedorenko.kidstim.service.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ChildService childService;

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ChildMapper childMapper;


    @Override
    public TaskDTO getTaskById(String id) {
        return taskMapper.getTaskDTO(taskDao.getTaskById(id));
    }

    @Override
    public void addTask(TaskDTO task) {
        taskDao.addTask(taskMapper.getTask(task));
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        return taskMapper.getTaskDTO(taskDao.updateTask(taskMapper.getTask(task)));
    }

    @Override
    public void deleteTask(String id) {
        TaskDTO task = getTaskById(id);
        taskDao.deleteTask(taskMapper.getTask(task));
    }

    @Override
    public List<TaskDTO> getTasksByParent(String parent) {
        List<TaskDTO> tasks = new ArrayList<>();
        for (Task task : taskDao.getTasksByParent(parent)) {
            tasks.add(taskMapper.getTaskDTO(task));
        }
        return tasks;
    }

    @Override
    public void markAsCompleted(String taskId, boolean complete) {
        TaskDTO task = getTaskById(taskId);
        List<ChildDTO> children = task.getChildren();
        if (complete) {
            task.setStatus(TaskStatus.COMPLETED);
            for (ChildDTO child : children) {
                child.setPoints(child.getPoints() + task.getPoints());
                childService.updateChild(child);
            }
        } else {
            task.setStatus(TaskStatus.CREATED);
            for (ChildDTO child : children) {
                child.setPoints(child.getPoints() - task.getPoints());
                childService.updateChild(child);
            }
        }
        updateTask(task);
    }

    @Override
    public List<TaskDTO> getTasksByChild(ChildDTO childDTO) {
        Child child = childMapper.getChild(childDTO);
        List<TaskDTO> tasks = new ArrayList<>();
        for (Task task : taskDao.getTasksByChild(child)) {
            tasks.add(taskMapper.getTaskDTO(task));
        }
        return tasks;
    }
}
