package facade.domain;

/**
 * @Author: Jeremy
 * @Date: 2020/8/26 22:53
 */
public class Task {
    // task id
    Long id;

    // task's command
    String command;

    // task.config
    String config;

    public void setDeployed(Boolean deployed) {
        this.deployed = deployed;
    }

    Boolean deployed;

    public Task(Long id, String command) {
        this.id = id;
        this.command = command;
    }

    public Long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, command='%s', config='%s', deploy=%s}",
                id, command, config, deployed);
    }
}
