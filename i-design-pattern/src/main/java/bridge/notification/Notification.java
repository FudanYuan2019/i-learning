package bridge.notification;

import bridge.sender.MsgSender;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:11
 */
public abstract class Notification {
    protected MsgSender msgSender;

    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String msg);
}
