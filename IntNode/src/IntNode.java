import java.sql.SQLOutput;

public class IntNode {
    private int data;
    private IntNode link;

    public static void main(String[] args){
        IntNode test = new IntNode(1, null); // IntNode 클래스의 객체 생성
        test.addNodeAfter(6); // addNodeAfter() 메소드를 사용하여 원하는 data 값 넣기
        test.addNodeAfter(5);
        test.addNodeAfter(4);
        test.addNodeAfter(3);
        test.addNodeAfter(2);

        System.out.println("\n>> addNodeAfter(), getData(), getLink() 작동 확인 : List가 연결되었는지 확인 ");

        System.out.print("Test Data : ");
        // addNodeAfter() 메소드를 통해 리스트 연결 됐는지 확인하고, getData() method가 작동하는지 확인
        for(IntNode cursor=test; cursor!=null; cursor=cursor.link){
            // test를 탐색할 cursor를 test로 초기화해준 뒤, cursor가 null이 될때까지 cursor를 넘겨주며 탐색
            System.out.print(cursor.getData() + " ");
        }


        System.out.print("\nTest Link : "); // getLink() 메소드 작동 확인
        for(IntNode cursor=test; cursor!=null; cursor=cursor.link){ // 위의 data 탐색 방법과 동일
            System.out.print(cursor.getLink() + " ");
        }


        int testLength = test.listLength(test); // listLength() 메소드의 리턴값을 받아줄 int형 변수 선언
        System.out.println("\n\n>> listLength() 작동 확인 : test list의 length는 "+ testLength);

        IntNode wantedListPosition = test.listSearch(test, 2); // listSearch() 메소드의 리턴값을 받아줄 IntNode형 변수 선언
        System.out.println("\n>> listSearch() 작동 확인 : data 2가 있는 위치는 " + wantedListPosition);

        test.removeAfter(); // removeAfter() 메소드를 사용하여 data 2가 들어있는 node 삭제
        System.out.println("\n>> removeAfter() 작동 확인 : data 2가 들어있는 node 삭제");


        System.out.println("\n>> data 2 삭제 후, listCopy() 작동 확인");
        IntNode copyListHead = test.listCopy(test); // listCopy()의 리턴값을 받아줄 IntNode형 변수를 선언해주고 메소드를 실행

        System.out.print("copyList Data : ");
        // 리스트 카피 됐는지 확인, getData() 메소드를 통해 removeAfter()가 잘 수행되었는지 확인
                for(IntNode cursor=copyListHead; cursor!=null; cursor=cursor.link){
            System.out.print(cursor.getData() + " ");
        }

        System.out.println("\n\n>> listPosition() 작동 확인");
        IntNode positionLink = listPosition(copyListHead, 2);
        // listPosition() 메소드의 리턴값을 받아줄 IntNode형 변수를 선언하고 복사된 리스트의 2번째 값의 위치를 확인
        System.out.println("2번째 data의 위치는 " + positionLink);


        IntNode test1 = new IntNode(10, null); //IntNode Class의 두번째 객체 생성
        test1.addNodeAfter(50);
        test1.addNodeAfter(40);
        test1.addNodeAfter(30);
        test1.addNodeAfter(20);

        test.setLink(test1); // setLink() 메소드를 사용하여 test의 Link값을 test1로 설정해줌
        test.setData(70); // setData() 메소드를 사용하여 test에 70 할당
        System.out.println("\n>> setLink()를 사용하여 test 노드를 test1(data 10, 20, 30, 40, 50이 연결된 리스트)로 설정해주고, setData()를 사용하여 test1에 data 70을 set");


        System.out.println("\n>> setLink(), setData(), listCopyWithTail() 작동 확인");
        IntNode[] copyList = new IntNode[2];
        // listCopyWithTail()의 리턴값이 IntNode[]형이기 때문에 리턴값을 받고자 IntNode[]형 copyList라는 변수 선언
        copyList = listCopyWithTail(test); // listCopyWithTail() 메소드를 사용하여 tail까지 리턴하도록 함

        System.out.print("copyList Data : ");
        for(IntNode cursor=copyList[0]; cursor!=copyList[1].link; cursor=cursor.link){
            // cursor에 copyList[0](head)를 할당하여 cursor가 copyList[1]을 만날 때까지 copyList의 data 출력
            System.out.print(cursor.getData() + " ");
        }

        System.out.print("\ncopyList Link : ");
        for(IntNode cursor=copyList[0]; cursor!=copyList[1].link; cursor=cursor.link){ // 위의 방법과 동일
            System.out.print(cursor.getLink() + " ");
        }

        System.out.println("\n\n>> listPart() 작동 확인 : test1을 data 30~50까지만 copy");
        IntNode[] testPart = new IntNode[2]; // listPart()의 리턴값을 받고자 IntNode[]형 변수 선언
        testPart = listPart(test.listSearch(test,30), test.listSearch(test, 50));
        // listPart() 메소드를 사용할 때, IntNode형 파라미터를 넘겨주어야하므로 넘기고자하는 data값의 위치를 listSearch() 메소드를 통해 찾고 넘겨줌


        System.out.print("testPart Data : ");
        for(IntNode cursor=testPart[0]; cursor!=testPart[1].link; cursor=cursor.link){ // 위의 방법과 마찬가지로 확인
            System.out.print(cursor.getData() + " ");
        }

        System.out.print("\ntestPart Link : ");
        for(IntNode cursor=testPart[0]; cursor!=testPart[1].link; cursor=cursor.link){
            System.out.print(cursor.getLink() + " ");
        }


        System.out.print("\n\n>> listPart() 작동 확인 : 40~80으로 cut하려 했을 경우 : "); // 없는 data까지 자르려 할때
        IntNode[] testPart1 = new IntNode[2];
        testPart1 = listPart(test.listSearch(test,40), test.listSearch(test, 80));




    }

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