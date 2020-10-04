package mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/9/12 16:28
 */
public abstract class AbstractChatRoom {
    protected List<Member> memberList;

    public AbstractChatRoom() {
        this.memberList = new ArrayList<>();
    }

    public void register(Member member) {
        this.memberList.add(member);
    }

    public abstract void sendText(Member fromMember, String txt);
}
