import java.io.*;
import java.util.*;
import java.lang.Integer;

class Node						              // 노드 정의															
{
    public Object element;
    public Node next;

    public Node(Object e)
    {
        element = e;
    }
    public Node(Object e, Node n)
    {
        element = e;
        next = n;
    }
    public Node()
    {
    }

}


class LinkedList                      //링크드 리스트 정의
{
    public Node first;
    public Node last;
    public Node current;
    public LinkedList()
    {
        first = null;
        current = null;
    }

    public void insertAtBack(Object e)			//링크드리스트의 끝에 새 노드 삽입
    {
        Node newNode = new Node(e);

        if(first==null)
            first = current = last = newNode;
        else
            last.next = newNode;
        last = newNode;
    }




}


class LinkedStack                      //링크드 스택 정의
{
    public Node topNode;				// top
    public LinkedStack()
    {
        topNode = null;
    }
    public boolean empty()				// 스택이 비어있는지 확인
    {
        return topNode == null;
    }

    public Object peek() 				// 스택에서 top의 값 리턴
    {
        if(empty())
        {
            throw new EmptyStackException();
        }
        return topNode.element;

    }

    public void push(Object theElement)					//스택에 push
    {
        topNode = new Node(theElement,topNode);
    }

    public Object pop()									//스택에서 pop
    {
        if(empty())
            throw new EmptyStackException();
        Object topElement = topNode.element;
        topNode = topNode.next;
        return topElement;
    }

}

class StackApp										// 스택을 이용한 연산을 정의
{
    private LinkedStack theStack;
    private LinkedList infixList ;
    private LinkedList postfixList = new LinkedList();

    public LinkedList InToPost(String input)					// infix를 postfix로 변환하는 함수
    {

        infixList = new LinkedList();					// infix 식을 토큰으로 나눠서 저장할 링크드 리스트

        StringTokenizer str = new StringTokenizer(input,"+-*/()",true);	          // string형을 토큰으로 나눔
        while (str.hasMoreTokens())
        {
            infixList.insertAtBack(str.nextToken());								//각각의 토큰을 링크드리스트에 저장
        }

        theStack = new LinkedStack();

        while(infixList.first != null)											// 스택을 이용해서 infix를 postfix로 변환
        {
            char operator = infixList.first.element.toString().charAt(0);      // 한 글자씩 읽어서

            switch(operator)												// 각각 알맞은 연산 수행
            {
                case '(' :
                    theStack.push("(");
                    break;
                case ')' :
                    rparen();
                    break;
                case '+' :
                case '-' :
                    priority(operator,false);
                    break;
                case '*' :
                case '/' :
                    priority(operator,true);
                    break;
                default :
                    postfixList.insertAtBack(infixList.first.element);
            }
            infixList.first = infixList.first.next;
        }
        while(!theStack.empty())				// 스택에 남아있는 것을 모두 pop해서 postfix식의 뒤에 넣는다.
        {
            postfixList.insertAtBack(theStack.pop());
        }
        return postfixList;					    //  변환된 postfix식을 리턴한다.
    }


    public void rparen()					// 오른쪽 괄호를 처리해주는 함수
    {
        while(!(theStack.empty()))
        {
            Object temp = theStack.pop();
            if(temp == "(") break;			// 왼쪽 괄호가 나올때까지 pop한다.
            else
                postfixList.insertAtBack(temp);
        }
    }

    public void priority(char op,boolean pr)
    {
        if(pr)               // '*' '/' 이 input되었을 때
        {
            while(!(theStack.empty()) && theStack.peek().toString().charAt(0) != '(' )
            {
                if(theStack.peek().toString().charAt(0) == '*' || theStack.peek().toString().charAt(0) == '/')
                    postfixList.insertAtBack(theStack.pop());
                else
                    break;
            }
            theStack.push(String.valueOf(op));
        }
        else 									// '+' '-'가 input 되었을 때
        {
            while(!(theStack.empty()) && theStack.peek().toString().charAt(0) != '(' )
                postfixList.insertAtBack(theStack.pop());
            theStack.push(String.valueOf(op));
        }
    }

    public int StToInt(String str)					// String형 숫자를 int형으로 변환하는 함수
    {
        Integer retint = new Integer(str);
        return  retint.intValue();					// int형으로 변환된 값을 리턴
    }

    public int value(LinkedList pf)					// postfix형 식을 계산하여 결과값을 리턴하는 함수
    {
        int temp=0,result;
        theStack = new LinkedStack();
        pf.current = pf.first;						// 처음 노드부터 처리

        while(pf.current != null){
            char ch = pf.current.element.toString().charAt(0);	// 한글자씩 떼어내서
            int num1,num2;										// 피연산자를 저장할 변수들
            switch(ch)											// 각각에 알맞은 연산 수행
            {										// 연산자일 때, 두개의 피연산자를 stack에서 pop핵서 계산한 결과를 다시 push
                case '+' :
                    num1 = StToInt(theStack.pop().toString());
                    num2 = StToInt(theStack.pop().toString());
                    temp = num1+num2;
                    theStack.push(String.valueOf(temp));
                    break;
                case '-' :
                    num1 = StToInt(theStack.pop().toString());
                    num2 = StToInt(theStack.pop().toString());
                    temp = num2-num1;
                    theStack.push(String.valueOf(temp));
                    break;
                case '*' :
                    num1 = StToInt(theStack.pop().toString());
                    num2 = StToInt(theStack.pop().toString());
                    temp = num1*num2;
                    theStack.push(String.valueOf(temp));
                    break;
                case '/' :
                    num1 = StToInt(theStack.pop().toString());
                    num2 = StToInt(theStack.pop().toString());
                    temp = num2/num1;
                    theStack.push(String.valueOf(temp));
                    break;

                default :									    // 숫자일때
                    theStack.push(pf.current.element);
                    break;
            }
            pf.current = pf.current.next;						// 다음 노드로 넘김
        }

        result = StToInt(theStack.pop().toString());			// 계산 결과 저장

        return result;											// 계산 결과 리턴
    }

    public String PostToIn(LinkedList postf)			   // Postfix를 우선순위 괄호를 씌운 Infix로 전환
    {
        theStack = new LinkedStack();
        String result = "";
        String temp = "";
        String num1 = "";
        String num2 = "";
        postf.current = postf.first;					// 첫번쩨 노드부터 처리
        while(postf.current != null){
            char ch = postf.current.element.toString().charAt(0);	// 한 글자씩 떼어내서
            switch(ch)									// 각각 알맞은 연산을 수행
            {											// 연산자일 때, 두개의 피연산자를 stack에서 pop해서 연산자로 연결
                case '+' :
                    num1 = theStack.pop().toString();
                    num2 = theStack.pop().toString();
                    temp = num2 + '+' + num1;
                    theStack.push("("+temp+")");
                    break;
                case '-' :
                    num1 = theStack.pop().toString();
                    num2 = theStack.pop().toString();
                    temp = num2 + '-' + num1;
                    theStack.push('('+temp+')');
                    break;
                case '*' :
                    num1 = theStack.pop().toString();
                    num2 = theStack.pop().toString();
                    temp = num2 + '*' + num1;
                    theStack.push('('+temp+')');
                    break;
                case '/' :
                    num1 = theStack.pop().toString();
                    num2 = theStack.pop().toString();
                    temp = num2 + '/' + num1;
                    theStack.push('('+temp+')');
                    break;
                default :											// 숫자일때
                    theStack.push(postf.current.element);
                    break;
            }
            postf.current = postf.current.next;				// 다음 노드로 넘김
        }

        result = theStack.pop().toString();			// 결과 문자열을 저장

        return result;								// 결과를 리턴
    }
}

class InfixToPostfix													// main 클래스...
{
    public static void main(String args[])
            throws IOException
    {
        int cnt;									// 반복문에 쓰일 count 변수
        int num;									// infix식의 개수를 저장할 변수
        String tmp = "";

        FileWriter fw = new FileWriter("output.txt");		// 출력 파일을 연다
        BufferedWriter bw = new BufferedWriter(fw);
        FileInputStream fin = new FileInputStream(args[0]);		// 입력 파일을 연다.
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader br = new BufferedReader(reader);
        LinkedList postfix ;

        tmp = br.readLine();						// input file로부터 한 줄 읽어들임
        num = Integer.parseInt(tmp);				// 읽어들인 문자열은 int형으로 변환해서 infix식의 개수를 나타내는 num에 저장
        bw.write(num+"\r\n");						// num을 output 파일에 씀
        bw.write("\r\n");

        for(cnt=0;cnt<num;cnt++){					// infix식의 수만큼 반복
            String inputStr = "";
            try
            {
                inputStr = br.readLine();			// input file로부터 한 줄 읽어들임
                bw.write("Infix:      "+inputStr+"\r\n");  // 읽어들인 식을 output 파일에 씀
            }catch(IOException e) 	{ 	}
            try{
                StackApp app = new StackApp();			// 스택 생성
                postfix = new LinkedList();
                postfix = app.InToPost(inputStr);		// infix 식을 postfix로 변환
                bw.write("Postfix:    ");				// 변환한 식을 output 파일에 씀
                while(postfix.current != null)			// 파일에 출력
                {
                    bw.write(postfix.current.element+"");	// 한 노드씩 차례로 출력
                    postfix.current = postfix.current.next;
                }
                bw.write("\r\n");
                bw.write("Result:     "+app.value(postfix));	// 계산 결과를 파일에 출력
                bw.write("\r\n");
                bw.write("Infix:      "+app.PostToIn(postfix));	 // 우선순위 괄호를 씌운 식을 파일에 출력
                bw.write("\r\n"+"\r\n");
            }
            catch(EmptyStackException npex){ }
        }
        fin.close();									// 입력 파일 닫음
        bw.close();										// 풀력 파일 닫음
    }
}

	