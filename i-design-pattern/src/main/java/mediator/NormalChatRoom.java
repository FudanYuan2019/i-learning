package mediator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/12 16:30
 */
public class NormalChatRoom extends AbstractChatRoom {
    public NormalChatRoom() {
        super();
    }

    @Override
    public void sendText(Member fromMember, String txt) {
        for (Member member : this.memberList) {
            if (member == fromMember) {
                continue;
            }
            member.receiveText(fromMember, txt);
        }
    }
}
