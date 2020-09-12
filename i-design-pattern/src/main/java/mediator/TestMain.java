package mediator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/12 16:33
 */
public class TestMain {
    public static void main(String[] args) {
        AbstractChatRoom chatRoom = new NormalChatRoom();
        Member member1 = new NormalMember("Jeremy", chatRoom);
        Member member2 = new NormalMember("Tom", chatRoom);
        Member member3 = new NormalMember("Amy", chatRoom);

        member1.sendText("hello every one!");
        member2.sendText("hi, how are you?");
        member3.sendText("hi, guys!");
    }
}
