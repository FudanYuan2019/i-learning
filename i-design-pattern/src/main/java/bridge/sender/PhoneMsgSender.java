package bridge.sender;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/23 23:16
 */
public class PhoneMsgSender implements MsgSender {

    private List<String> phoneList;

    public PhoneMsgSender(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public void send(String msg) {
        // Omitting concrete logic
        for (String phone : phoneList) {
            System.out.println(String.format("Send [%s] to %s by phone", msg, phone));
        }
    }
}
