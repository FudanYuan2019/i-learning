package facade.service.impl;

import facade.domain.Task;
import facade.service.TaskService;

/**
 * @Author: Jeremy
 * @Date: 2020/8/26 22:57
 */
public class TaskServiceImpl implements TaskService {
    @Override
    public Task create(Long id, String command) {
        return new Task(id, command);
    }

    @Override
    public void config(Task task, String config) {
        task.setConfig(config);
    }

    @Override
    public void deploy(Task task) {
        task.setDeployed(true);
    }
}
