package bridge.notification;

import bridge.sender.MsgSender;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:10
 */
public class NormalNotification extends Notification {
    public NormalNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String msg) {
        msgSender.send(msg);
    }
}
