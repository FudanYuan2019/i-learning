package bridge;

import bridge.notification.*;
import bridge.sender.EmailMsgSender;
import bridge.sender.MessageMsgSender;
import bridge.sender.MsgSender;
import bridge.sender.PhoneMsgSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:20
 */
public class TestMain {
    public static void main(String[] args) {
        List<String> emailList = new ArrayList<>(2);
        emailList.add("test1@i-learning.com");
        emailList.add("test2@i-learning.com");
        MsgSender emailMsgSender = new EmailMsgSender(emailList);
        Notification normalNotification = new NormalNotification(emailMsgSender);
        normalNotification.notify("There is a normal event");

        Notification trivialNotification = new TrivialNotification(emailMsgSender);
        trivialNotification.notify("There is a trivial event");

        List<String> phoneList = new ArrayList<>(2);
        phoneList.add("189****1199");
        phoneList.add("190****2202");
        MsgSender messageMsgSender = new MessageMsgSender(phoneList);
        Notification severeNotification = new SevereNotification(messageMsgSender);
        severeNotification.notify("There is a severe event");

        MsgSender phoneMsgSender = new PhoneMsgSender(phoneList);
        Notification urgentNotification = new UrgentNotification(phoneMsgSender);
        urgentNotification.notify("There is an urgent event");
    }
}
