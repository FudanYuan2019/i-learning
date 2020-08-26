package facade.service;

import facade.domain.Task;

/**
 * @Author: Jeremy
 * @Date: 2020/8/26 22:52
 */
public interface TaskService {
    Task create(Long id, String command);
    void config(Task task, String config);
    void deploy(Task task);
}
