package mediator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/12 16:34
 */
public class NormalMember extends Member {

    public NormalMember(String nickName, AbstractChatRoom chatRoom) {
        super(nickName, chatRoom);
    }

    @Override
    public void sendText(String txt) {
        this.chatRoom.sendText(this, txt);
    }

    @Override
    public void receiveText(Member fromMember, String txt) {
        System.out.println(String.format("%s receive a message from %s: %s", getNickName(), fromMember.getNickName(), txt));
    }
}
