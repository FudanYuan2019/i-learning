package command;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 20:46
 */
public class TestMain {
    public static void main(String[] args) {
        // create receiver object
        Receiver receiver = new Receiver("Arthur", "回旋打击");

        // create the command object, and set the receiver object
        Command command1 = new MoveCommand(receiver, 10.0, 20.0);
        Command command2 = new SkillCommand(receiver);
        Command command3 = new MoveCommand(receiver, 30.0, 30.0);
        Command command4 = new SkillCommand(receiver);
        Command command5 = new MoveCommand(receiver, 40.0, 40.0);
        Command command6 = new SkillCommand(receiver);

        // put the command to the queue
        Queue<Command> queue = new LinkedList<>();
        queue.offer(command1);
        queue.offer(command2);
        queue.offer(command3);
        queue.offer(command4);
        queue.offer(command5);
        queue.offer(command6);

        // get the command and create the invoker to action
        while (!queue.isEmpty()) {
            Command command = queue.poll();
            Invoker invoker = new Invoker(command);
            invoker.action();
            System.out.println();
        }
    }
}
