package bridge.enums;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:05
 */
public enum AlarmLevelEnum {
    TRIVIAL(0, "trivial"),

    NORMAL(1, "normal"),

    SEVERE(2, "severe"),

    URGENT(3, "urgent")
    ;

    Integer code;
    String alias;

    AlarmLevelEnum(Integer code, String alias) {
        this.code = code;
        this.alias = alias;
    }
}
