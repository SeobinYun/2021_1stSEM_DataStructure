import java.sql.SQLOutput;

public class IntNode {
    private int data;
    private IntNode link;


    public IntNode(int initialData, IntNode initialLink) { //constructor
        data = initialData;
        link = initialLink;
    }

    public void addNodeAfter(int item) {
        link = new IntNode(item, link);
    }

    public int getData() {
        return data;
    }

    public IntNode getLink() {
        return link;
    }

    public static IntNode listCopy(IntNode source) {
        IntNode copyHead;
        IntNode copyTail;

        if (source == null) {
            return null;
        }

        copyHead = new IntNode(source.data, null);
        copyTail = copyHead;

        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        return copyHead;
    }

    public static IntNode[] listCopyWithTail(IntNode source) {
        IntNode copyHead;
        IntNode copyTail;
        IntNode[] answer = new IntNode[2];

        if (source == null) {
            return answer;
        }

        copyHead = new IntNode(source.data, null);
        copyTail = copyHead;

        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    public static int listLength(IntNode head) { // list 길이 구하기
        IntNode cursor;
        int answer;

        answer = 0;
        for (cursor = head; cursor != null; cursor = cursor.link) {
            answer++;
        }
        return answer;
    }

    public static IntNode[] listPart(IntNode start, IntNode end) { // list 쪼개기
        IntNode copyHead;
        IntNode copyTail;
        IntNode cursor;
        IntNode[] answer = new IntNode[2];

        copyHead = new IntNode(start.data, null);
        copyTail = copyHead;
        cursor = start;


        while (cursor != end) {
            cursor = cursor.link;
            if (cursor == null) {
                throw new IllegalArgumentException("end node was not found on the list");
            }
            copyTail.addNodeAfter(cursor.data);
            copyTail = copyTail.link;
        }

        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    public static IntNode listPosition(IntNode head, int position) { // n번째 data 값 확인
        IntNode cursor;
        int i;

        if (position <= 0) {
            throw new IllegalArgumentException("position is not positive");
        }

        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++) {
            cursor = cursor.link;
        }
        return cursor;
    }

    public static IntNode listSearch(IntNode head, int target) { //list내에서 원하는 data 값의 위치 검색
        IntNode cursor;

        for (cursor = head; cursor != null; cursor = cursor.link) {
            if (target == cursor.data) {
                return cursor;
            }
        }
        return null;
    }

    public void removeAfter() {
        link = link.link;
    }

    public void setData(int newData) { // 새로운 값으로 바꿈
        data = newData;
    }

    public void setLink(IntNode newLink) {
        link = newLink;
    }

}