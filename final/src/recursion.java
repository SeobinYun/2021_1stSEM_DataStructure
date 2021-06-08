public class recursion {
    public static void main(String[] args){
        pattern(840);
    }

    public static void pattern(int n){
        if(n>0 && n<=8484){
            System.out.println(n);
            pattern(n*2);
            System.out.println(n);
        }
    }
}
