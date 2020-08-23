package bridge.sender;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:16
 */
public class EmailMsgSender implements MsgSender {

    List<String> emailList;

    public EmailMsgSender(List<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public void send(String msg) {
        // Omitting concrete logic
        for (String email : emailList) {
            System.out.println(String.format("Send [%s] to %s by email", msg, email));
        }
    }
}
