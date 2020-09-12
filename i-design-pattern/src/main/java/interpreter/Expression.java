package interpreter;

import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/9/11 22:32
 */
public interface Expression {
    boolean interpret(Map<String, Long> stats);
}
