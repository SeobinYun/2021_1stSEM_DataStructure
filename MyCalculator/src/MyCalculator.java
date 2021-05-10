// 스택 이용
// 입력: Infix로 표현된 수식 (((6+9))/3)*(6-4))
// 출력 :
//        1) 입력된 수식을 Postfix로 변환된 수식을 출력
//        2) 계산 결과값 출력 입력된 Infix 수식을 Postfix 수식으로 변환하여 화면에 출력하고,
//        변환된 Postfix 수식을 계산하여 결과 값을 화면에 출력하면 됩니다.
//
//        요구사항 :
//        1. 수식은 괄호가 있을 수 있고, 띄어쓰기가 ' '되어 있다고 가정해도 됩니다.
//        operator는 +, -, *, / 네 가지로 하고 operand는 양수로만 하고 실수형 (정수형이 아님)으로 할 것
//        2. 잘못 입력 된 수식이 경우 에러 메시지를 출력하고 가능한 모든 에러 상황들을 확인 할 수 있어야 함
//        3. 사용자 인터페이스는 자율적으로 정하면 됨
//
//        과제 제출일과 방법 :
//        1. 2020년 5월 10일 자정까지
//        2. 소스코드들과 출력 화면 예(최소한 5번은 실행하고 에러 케이스 등을 확인할 수 있도록 할 것)를 캡처하여 모든 파일을 MyCalculator 폴더에 저장하여 제출
//
//        제출물 : pdf 형태로 파일명은 이름_학번.pdf
// operand는 양수만
// 잘못 입력된 수식일 경우 에러메시지 출력

// 느낀점: equals()랑 ==랑 구분해서 사용하자
// data.equals("String")이고, 이건 값만 비교한다.
//반면 ==는 주소값까지 비교한다.
//postfix 계산 메소드를 실행할 때 계속 array[i].equals("+") -> 이런식으로 if문을 돌려서 값이 제대로 계산이 안됐던 것.
//array[i].charAt(0) == '+' -> 이렇게 고쳐주니까 해결됐다. array[i]는 String형이니까 array[i]=="+", 이렇게나 array[i]=='+' 이따위로 하면 절대 안됐음.
//charAt(index)로 정확한 char값을 지목해줘야했다!!!!!.

// 아!!!!!!!!!물론 코드들이 얼레벌레 복잡도 계산 안하고 달달달 돌아가긴 ㅇ한다...
//내가 생각하기에 애초에 실수형이면 링크드리스트 사용하는게 훨씬 편할텐데 그걸 생각안하고 머릿속에 떠오르는걸 마구마구 실행해버림..
//그래서 배열로 하느라 조금 힘들었다.
//+ 이 코드는 소수점 한자리밖에 못받는다.... 왜냐면 코드를 무작위로 짰기 때문...
//시간날 때 뒤죽박죽하게 된거 간결하게 고쳐야겠다.

import java.util.EmptyStackException;
import java.util.Scanner;
public class MyCalculator {
    public char[] stack; // InfixToPostfix시 사용할 char[]형 변수
    public double[] doubleStack; // Postfix를 연산할 때 사용할 double[]형 변수
    public int manyItems;
    public int doubleManyItems;

    public MyCalculator(){
        manyItems = 0;
        doubleManyItems = 0;
        doubleStack = new double[30];
        stack = new char[30];
    }
    public MyCalculator(int size){
        manyItems = size;
        doubleManyItems = 0;
        doubleStack = new double[size];
        stack = new char[size];
    }

    public static void main(String[] args) {
        while (true) {
            //String postfix = ""; // postfix
            double result = 0; // postfix 결과값
            System.out.println("\n======= MyCalculator =======");
            System.out.println("MyCalculator 사용을 환영합니다.\n");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Infix로 수식을 입력하시오. ");
            System.out.print(">>");
            String infix = scanner.next(); // infix 입력받음
            boolean check = checkInfix(infix); // infix 체크

            if (check == true) {
                //MyCalculator postfix = new MyCalculator(infix.length());
                String postfix = InfixToPostfix(infix); // postfix 구하기
                System.out.println("\nPostfix로 변환 : " + postfix);
                while(true){
                    System.out.println("\n계산을 시작할까요? (Y/N)");
                    System.out.print(">>");
                    String yesOrNo = scanner.next();
                    if (yesOrNo.equals("Y")) {
                        result = calculatePostfix(postfix); // 계산값 구하기
                        System.out.println("계산 값 : " + result);
                        break;
                    }
                    else if (yesOrNo.equals("N")) {
                        System.out.println("계산을 실행하지 않았습니다.");
                        break;
                    }
                    else{
                        System.out.println("Y/N 중에 입력해주세요.");
                    }
                }
            }
            else {
                return;// checkInfix 통과 못하면 아무것도 안하고 끝냄
            }
            String repeatAnswer;
            while(true) {
                System.out.println("\n계속하시겠습니까? (Y/N)");
                System.out.print(">>");
                repeatAnswer = scanner.next();
                if (repeatAnswer.equals("Y")) {
                    break;
                }
                else if (repeatAnswer.equals("N")) {
                    System.out.println("\n사용해주셔서 감사합니다.");
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println("===============================");
                    return;
                }
                else{
                    System.out.println("Y/N 중에 입력해주세요.");
                }
            }
            if (repeatAnswer.equals("Y")) {
                continue;
            }
        }
    }

    public static boolean checkInfix(String infix) {
        char[] infixCheckList = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '+', '-', '*', '/', '(', ')'};

        // 1. 괄호의 밸런스가 맞는지
        // 2. 위의 infixCheckList 이외의 값이 들어갔는지
        int left = 0, right = 0; // 괄호의 개수를 확인하는 변수
        boolean checking = false; // infix의 입력이 맞는지 확인하는 변수
        int dot = 0;

        for (int i = 0; i < infix.length(); i++) {// 2번, infixCheckList 이외의 값이 들어갔는지 check
            for (int j = 0; j < infixCheckList.length; j++) {
                if (infix.charAt(i) == infixCheckList[j]) { // 만약 infix.charAt(i)의 문자가 infixCheckList에 있으면
                    checking = true; // checking을 true로 바꾸고 (무조건 이쪽에서 true가 되야 올바르게 입력된 것임.
                    break; // j for문을 탈출
                }
                else{;}
            }
            if (checking == false) { // 그리고 checking이 false이면 error 띄움
                throw new IllegalArgumentException("Infix 입력 형식이 올바르지 않습니다.");
            }

            if(infix.charAt(i)=='.'){
                dot++;
            }
        }
        if (dot==0) { // 그리고 checking이 false이면 error 띄움
            throw new IllegalArgumentException("Infix 입력 형식이 올바르지 않습니다.");
        }

        for(int i=0; i<infix.length(); i++){ // 1번, 괄호의 밸런스가 맞는지 check
            if (infix.charAt(i) == '(' || infix.charAt(i) == ' ') { // 띄어쓰기 형식일때는 그냥 Left 변수에 저장해줌
                left++;
            }
            else if (infix.charAt(i) == ')') {
                right++;
            }
        }

        if ((left + right) % 2 != 0) { // 괄호 밸런스 확인
            throw new IllegalArgumentException("Infix 입력 형식의 괄호의 짝이 맞지 않습니다.");
        }
        else if ((left+right)%2==0){ // 맞으면 그냥 넘어감
            ;
        }
        System.out.println("checkInfix 통과!");
        return true; // 맞으면 이쪽으로 넘어옴
    }

    public static String InfixToPostfix(String infix) {
        //char[] infixCheckList = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '(', ')'};
        MyCalculator tmpStack = new MyCalculator(infix.length()); // 괄호와 연산자들을 저장할 stack1을 infix의 길이만큼 할당
        MyCalculator answer = new MyCalculator(infix.length()); // answer을 infix의 길이만큼 할당

        char[] operator = {'+', '-', '*', '/'};
        for(int i=0; i<infix.length(); i++) {
            if(infix.charAt(i)==')'){ // ) 문자면
                if(tmpStack.isEmpty()==false) { // 만약 tmpStack이 비지 않을 경우
                    while (tmpStack.peek() != '(') { // tmpStack의 peek값이 '('일 때까지
                        char tmp = tmpStack.pop(); // pop하고 answer에 push
                        answer.push(tmp);
                        answer.push(' ');
                    }
                    tmpStack.pop(); // ( 문자 pop 해주기
                }
                else{ // tmpStack이 빌 경우
                    throw new EmptyStackException();
                }
            }
            else if(infix.charAt(i)=='('){ // ( 문자면 그냥 stack1에 push
                tmpStack.push(infix.charAt(i));
            }
            else if(infix.charAt(i)=='*'||infix.charAt(i)=='/'){ // infix.charAt(i)의 값이 *나 /이면
                if(tmpStack.peek()=='*'||tmpStack.peek()=='/'){ // tmpStack의 peek값이 *나 /인지 확인하고 그게 맞다면
                    char tmp = tmpStack.pop(); // tmpStack의 peek값을 pop해주고 그 값을 answer에 넣어준 다음
                    answer.push(tmp);
                    answer.push(' ');
                    tmpStack.push(infix.charAt(i)); // infix.charAt(i)를 tmpStack에 push 해줌
                }
                else{ // 아니면 걍 tmpStack에 push
                    tmpStack.push(infix.charAt(i));
                }
            }
            else if(infix.charAt(i)=='+'||infix.charAt(i)=='-'){ // infix.charAt(i)의 값이 +나 -이면
                if(tmpStack.peek()=='*'||tmpStack.peek()=='/'){ // stack1의 peek값이 *나 /인지 확인하고 *,/가 맞다면
                    char tmp = tmpStack.pop(); //  stack1을 pop하고 pop한 것들을 answer에 push한 다음, +,-를 stack1에 push
                    answer.push(tmp);
                    answer.push(' ');
                    tmpStack.push(infix.charAt(i));
                }
                else if(tmpStack.peek()=='+'||tmpStack.peek()=='-'){
                    char tmp = tmpStack.pop(); // 만약 tmpStack의 peek값이 +, -이면 tmpStack peek에 있는 +,-를 pop하고 
                    answer.push(tmp); // infix.charAt(i)의 +,-를 tmpStack에 넣어줌
                    answer.push(' ');
                    tmpStack.push(infix.charAt(i));
                }
                else{ // 그냥 괄호나 그런거면 push
                    tmpStack.push(infix.charAt(i));
                }
            }
            else if(infix.charAt(i+1)=='.'){
                answer.push(infix.charAt(i));
                answer.push(infix.charAt(++i));
                answer.push(infix.charAt(++i));
                answer.push(' ');
            }
            else {// 그냥 숫자면 answer에 push
                answer.push(infix.charAt(i));
            }
        }
        while(tmpStack.isEmpty()!=true){ // 이제 tmpStack을 다 비움
            char tmp=tmpStack.pop();
            answer.push(tmp);
            answer.push(' '); // 나머지를 push할 때 공백문자도 추가
        }

        String answerString = new String(answer.stack); // char[]형인 answer.stack을 String형으로 변환하여 return함.
        return answerString;
    }

    public static double calculatePostfix(String postfix) {
        double answer = 0;
        System.out.println("postfix : " + postfix);
        String[] array = postfix.split(" "); // 공백문자를 기준으로 postfix를 나누어 array배열에 저장함
        MyCalculator calculator = new MyCalculator(array.length);

//        System.out.println("array.length = " + array.length); // array 배열의 길이 확인
//        for (int j = 0; j < array.length; j++) {
//            System.out.print("array[" + j + "] = " + array[j]); // 공백문자 기준으로 잘 쪼개졌는지 확인
//            System.out.println();
//        }

        double tmp1 = 0, tmp2 = 0, tmp3 = 0; // tmp1, tmp2 = 피연산자들, tmp3 = 결과값
        for (int i = 0; i < array.length; i++) { // 배열 길이만큼 for문 돌림
            if (array[i].charAt(0) == '+' || array[i].charAt(0) == '-' || array[i].charAt(0) == '*' || array[i].charAt(0) == '/') { 
                // array[i]값이 operator이면, 
                try { 
                    tmp1 = calculator.doublePop(); // pop해서 각각 tmp1, tmp2에 저장
                    tmp2 = calculator.doublePop();

                    switch (array[i].charAt(0)) { // 각각의 상황마다 연산해줌
                        case '+':
                            tmp3 = tmp2 + tmp1;
                            calculator.doublePush(tmp3);
                            //System.out.println("peek : " + calculator.doublePeek());
                            break;
                        case '-':
                            tmp3 = tmp2 - tmp1;
                            calculator.doublePush(tmp3);
                            //System.out.println("peek : " + calculator.doublePeek());
                            break;
                        case '*':
                            tmp3 = tmp2 * tmp1;
                            calculator.doublePush(tmp3);
                            //System.out.println("peek : " + calculator.doublePeek());
                            break;
                        case '/':
                            try {
                                tmp3 = tmp2 / tmp1;
                                calculator.doublePush(tmp3);
                                //System.out.println("peek : " + calculator.doublePeek());
                            } catch (ArithmeticException e) {
                                throw new ArithmeticException("0으로 나눌 수 없습니다.");
                            } finally {
                                break;
                            }
                    }
                } catch (EmptyStackException e) {
                    break;
                }
            } 
            else { // array[i]가 operator가 아닌 숫자일 경우 
                try {
                    double tmp = Double.parseDouble(array[i]); // String형인 array[i]의 값을 double형으로 형변환해서 
                    calculator.doublePush(tmp); // push해줌
                } catch (NumberFormatException e) {
                    ;
                }
            }
        }
//        System.out.println("calculator.doubleStack.length : " + calculator.doubleStack.length);
//        System.out.println("calculator.doubleManyItems : " + calculator.doubleManyItems);
//        for (int i = 0; i < calculator.doubleStack.length; i++) {
//            System.out.println("calculator.doubleStack[" + i + "] : " + calculator.doubleStack[i]);
//        }

        answer = calculator.doublePop(); // 총 결과값을 pop해서 변수에 저장한다음 return함
        return answer;
    }

    public boolean isEmpty( )
    {
        return (manyItems == 0);
    }

    public char peek( )
    {
        if (manyItems == 0)
            // EmptyStackException is from java.util and its constructor has no argument.
            throw new EmptyStackException( );
        return stack[manyItems-1];
    }

    public char pop( )
    {
        if (manyItems == 0)
            // EmptyStackException is from java.util and its constructor has no argument.
            throw new EmptyStackException( );
        return stack[--manyItems];
    }

    public void push(char item)
    {
        if (manyItems == stack.length)
        {
            ensureCapacity(manyItems*2 + 1);
        }
        stack[manyItems] = item;
        manyItems++;
    }

    public double doublePeek( )
    {
        if (manyItems == 0)
            // EmptyStackException is from java.util and its constructor has no argument.
            throw new EmptyStackException( );
        return doubleStack[doubleManyItems-1];
    }

    public double doublePop( )
    {

        if (doubleManyItems == 0)
            // EmptyStackException is from java.util and its constructor has no argument.
            throw new EmptyStackException( );
        return doubleStack[--doubleManyItems];
    }

    public void doublePush(double item)
    {
        if (doubleManyItems == doubleStack.length)
        {
            doubleEnsureCapacity(doubleManyItems*2 + 1);
        }
        doubleStack[doubleManyItems] = item;
        doubleManyItems++;
    }

    public void doubleEnsureCapacity(int minimumCapacity)
    {
        double biggerArray[ ];

        if (doubleStack.length < minimumCapacity)
        {
            biggerArray = new double[minimumCapacity];
            System.arraycopy(doubleStack, 0, biggerArray, 0, doubleManyItems);
            doubleStack = biggerArray;
        }
    }

    public void ensureCapacity(int minimumCapacity)
    {
        char biggerArray[ ];

        if (stack.length < minimumCapacity)
        {
            biggerArray = new char[minimumCapacity];
            System.arraycopy(stack, 0, biggerArray, 0, manyItems);
            stack = biggerArray;
        }
    }
}
