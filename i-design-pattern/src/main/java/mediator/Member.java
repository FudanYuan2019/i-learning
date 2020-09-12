package mediator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/12 16:31
 */
public abstract class Member {
    protected String nickName;
    protected AbstractChatRoom chatRoom;

    public Member(String nickName, AbstractChatRoom chatRoom) {
        this.nickName = nickName;
        this.chatRoom = chatRoom;
        chatRoom.register(this);
    }

    public String getNickName() {
        return nickName;
    }

    public abstract void sendText(String txt);
    public abstract void receiveText(Member from, String txt);
}
