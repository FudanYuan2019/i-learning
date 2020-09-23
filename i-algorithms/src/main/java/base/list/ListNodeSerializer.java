package base.list;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 11:19
 */
public class ListNodeSerializer {
    public static String serialize(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            stringBuilder.append(node.val + ",");
            node = node.next;
        }

        return stringBuilder.toString();
    }

    public static ListNode deserialize(String listStr) {
        String[] strings = listStr.split(",");
        ListNode dummyHead = new ListNode();
        ListNode last = dummyHead;
        for (String s : strings) {
            ListNode node = new ListNode(Integer.parseInt(s));
            last.next = node;
            last = node;
        }
        return dummyHead.next;
    }
}
