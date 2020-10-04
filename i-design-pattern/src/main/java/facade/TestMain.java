package facade;

import facade.controller.TaskController;
import facade.domain.Task;
import facade.service.TaskService;
import facade.service.impl.TaskServiceImpl;

/**
 * @Author: Jeremy
 * @Date: 2020/8/26 23:06
 */
public class TestMain {
    public static void main(String[] args) {
        TaskService taskService = new TaskServiceImpl();
        TaskController taskController = new TaskController(taskService);
        // A
        Task task1 = taskController.create(1L, "select * from t;");
        taskController.config(task1, "config somthing");
        taskController.deploy(task1);
        System.out.println(task1);

        // facade for B
        Task task2 = taskController.createUntilDeploy(2L, "select * from t;", "config somthing");
        System.out.println(task2);
    }
}
