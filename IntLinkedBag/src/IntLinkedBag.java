
public class IntLinkedBag implements Cloneable{
    private IntNode head;
    private int manyNodes;

    public static void main(String[] args){

        IntLinkedBag test1 = new IntLinkedBag(); // IntLinkedBag의 첫번째 객체 생성 (test1)
        test1.add(3); //add() 메소드를 사용하여 원하는 값들을 넣어줌
        test1.add(2);
        test1.add(1);

        IntLinkedBag test2= new IntLinkedBag(); // IntLinkedBag의 두번째 객체 생성 (test2)
        test2.add(6); // add() 메소드를 사용하여 원하는 값들을 넣어줌
        test2.add(5);
        test2.add(4);
        test2.add(3);
        test2.add(3);

        System.out.println(">> add(), size() 작동 확인");
        System.out.print("test1 Bag : "); // test1 내용물 확인
        for(IntNode cursor=test1.head; cursor!=null; cursor=cursor.getLink()) { //cursor를 test1.head로 초기화해주어 cursor가 null일 때까지, data값들을 읽어냄.
            System.out.print(cursor.getData() + " ");
        }
        System.out.println("\ntest1 manyNodes : " + test1.size()); /// size() 메소드 사용하여 manyNodes 값을 구해줌


        System.out.print("\ntest2 Bag : "); // test1 방법과 마찬가지로 test2 내용물 확인
        for(IntNode cursor = test2.head; cursor!=null; cursor=cursor.getLink()){
            System.out.print(cursor.getData() + " ");
        }
        System.out.println("\ntest2 manyNodes : " + test2.size()); // size() 메소드 사용하여 manyNodes 값을 구해줌



        System.out.println("\n>> clone() 작동 확인 : test2의 Clone인 test4 출력");
        IntLinkedBag test4 = new IntLinkedBag(); // test2의 Clone을 저장할 IntLinkedBag의 네번째 객체인 test4 생성
        test4 = (IntLinkedBag)test2.clone(); // clone() 메소드의 리턴값을 test4에 할당해줌

        System.out.print("(test2의 clone인) test4 Bag : "); // test1, test2 방법과 같이 test4의 내용물 확인해줌
        for(IntNode cursor = test4.head; cursor!=null; cursor=cursor.getLink()){
            System.out.print(cursor.getData() + " ");
        }
        System.out.println("\n(test2의 clone인) test4 manyNodes : " + test4.size()); // size() 메소드 사용하여 manyNodes 값을 구해줌


        System.out.println("\n>> union() 작동 확인 : test1과 test2를 합친 test3 만듦");
        IntLinkedBag test3 = new IntLinkedBag(); //test1과 test2를 합칠 test3 생성
        test3 = union(test1, test2); // union() 메소드의 리턴값을 test3에 할당해줌

        System.out.print("test3 Bag : "); // test1, 2, 4, 방법과 같이 test3의 내용물을 확인해줌
        for(IntNode cursor=test3.head; cursor!=null; cursor=cursor.getLink()){
            System.out.print(cursor.getData() + " ");
        }
        System.out.println("\ntest3 manyNodes : " + test3.size()); // size() 메소드 사용하여 manyNodes 값을 구해줌


        System.out.println("\n>> countOccurrences() 작동 확인"); // countOccurrences() 메소드를 사용하여 test3 안에 있는 data 3의 개수를 확인
        System.out.println("test3에서 3의 개수는 " + test3.countOccurrences(3));



        System.out.println("\n>> addAll() 작동 확인 : test2의 모든 걸 test1에 저장");
        test1.addAll(test2); // addAll() 메소드를 사용하여 test2의 모든걸 test1에 저장

        System.out.print("(test2를 저장한) test1 Bag : "); // test1의 내용물을 확인해줌
        for(IntNode cursor = test1.head; cursor!=null; cursor=cursor.getLink()){
            System.out.print(cursor.getData() + " ");
        }
        System.out.println("\n(test2를 저장한) test1 manyNodes : " + test1.size()); // size() 메소드 사용하여 manyNodes 값을 구해줌


        System.out.println("\n>> grab() 작동 확인 : test2에서 grab"); // grab() 메소드를 사용하여 test2 내에 무작위로 data값 하나를 구해줌
        int tmp = test2.grab();
        System.out.println("test2 Grab : " + tmp);


        System.out.println("\n>> remove() 작동 확인 : test2에서 data 3 삭제"); // remove() 메소드를 사용하여 test1내에 data 3을 삭제해줌
        test1.remove(3);

        System.out.print("(data 3을 삭제한) test1 data : "); // test1 내용물 확인
        for(IntNode cursor=test1.head; cursor!=null; cursor=cursor.getLink()){
            System.out.print(cursor.getData() + " ");
        }
        System.out.println("\n(data 3을 삭제한) test1 manyNodes : " + test1.size()); // size() 메소드 사용하여 manyNodes 값을 구해줌

    }

    public IntLinkedBag() { // constructor
        System.out.println("IntLinkedBag.IntLinkedBag");
        head = null;
        manyNodes = 0;
    }

    public void add(int element){
        System.out.println("IntLinkedBag.add");
        head = new IntNode(element, head);
        manyNodes++;
    }

    public void addAll(IntLinkedBag addend){ //addend가 null이면 안됨
        System.out.println("IntLinkedBag.addAll");

        IntNode[] copyInfo;

        if (addend.manyNodes > 0){
            copyInfo=IntNode.listCopyWithTail(addend.head);
            copyInfo[1].setLink(head);
            head=copyInfo[0];
            manyNodes+=addend.manyNodes;
        }
    }

    public void addMany(int... elements){ // 이 부분은 skip
        for(int i:elements){
            add(i);
        }
    }

    public Object clone(){
        System.out.println("IntLinkedBag.clone");
        IntLinkedBag answer;

        try{
            answer = (IntLinkedBag) super.clone();
        }
        catch(CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable");
        }
        answer.head=IntNode.listCopy(head);

        return answer;
    }

    public int countOccurrences(int target){ //target이 가방에 몇 개 있는지
        System.out.println("IntLinkedBag.countOccurrences");
        int answer;
        IntNode cursor;

        answer=0;
        cursor= IntNode.listSearch(head, target);
        while(cursor!=null) {
            answer++;
            cursor = cursor.getLink();
            cursor = IntNode.listSearch(cursor, target);
        }
        return answer;
    }

    public int grab() { // 리스트 내에 노드 데이터를 랜덤으로 출력
        System.out.println("IntLinkedBag.grab");
        int i;
        IntNode cursor;

        if (manyNodes == 0) {
            throw new IllegalStateException("Bag size is zero");
        }

        i = (int)(Math.random() * manyNodes) + 1;
        cursor = IntNode.listPosition(head, i);
        return cursor.getData();
    }

    public boolean remove(int target){
        System.out.println("IntLinkedBag.remove");
        IntNode targetNode;

        targetNode=IntNode.listSearch(head, target);
        if(targetNode==null){
            return false;
        }
        else{
            targetNode.setData(head.getData());
            head = head.getLink();
            manyNodes--;
            return true;
        }
    }

    public int size(){

        System.out.println("IntLinkedBag.size");
        return manyNodes;
    }

    public static IntLinkedBag union(IntLinkedBag b1, IntLinkedBag b2){
        System.out.println("IntLinkedBag.union");

        IntLinkedBag answer = new IntLinkedBag();

        answer.addAll(b1);
        answer.addAll(b2);
        return answer;
    }

}
