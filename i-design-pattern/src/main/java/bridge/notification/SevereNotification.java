package bridge.notification;

import bridge.sender.MsgSender;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:10
 */
public class SevereNotification extends Notification {

    public SevereNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String msg) {
        msgSender.send(msg);
    }
}
