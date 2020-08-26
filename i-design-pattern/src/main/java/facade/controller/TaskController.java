package facade.controller;

import facade.domain.Task;
import facade.service.TaskService;

/**
 * @Author: Jeremy
 * @Date: 2020/8/26 23:01
 */
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public Task create(Long id, String command) {
        return this.taskService.create(id, command);
    }

    public void config(Task task, String config) {
        this.taskService.config(task, config);
    }

    public void deploy(Task task) {
        taskService.deploy(task);
    }

    // facade design pattern
    public Task createUntilDeploy(Long id, String command, String config) {
        Task task = taskService.create(id, command);
        taskService.config(task, config);
        taskService.deploy(task);
        return task;
    }
}
