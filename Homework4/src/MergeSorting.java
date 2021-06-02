public class MergeSorting {
    public static void main(String[] args) {
        int[] array = new int[32];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 32);
            for (int j = 0; j < i; j++) { // 중복값 확인: 배열 index j부터, j가 i전일 때까지 비교 반복
                if (array[j] == array[i]) { // index j방의 값과 i방의 값이 값다면
                    i--; // index i 위치의 값을 다시 할당받아야 하기 때문에 i--를 해주고 비교반복문 탈출
                    break;
                }
            }
        }
        System.out.print("배열생성 : ");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();


        mergeSort(array);
    }

    private static void mergeSort(int[] array, int first, int n){
        if(n>1){
            int n1=n/2;
            int n2=n-n1;
            mergeSort(array, first, n1);
            mergeSort(array,first+n1, n2);
            merge(array, first, n1, n2);
        }
    }

    private static void merge(int[] array, int first, int n1, int n2){
        int[] answer = new int[n1+n2];
        int copied = 0;
        int copied1= 0;
        int copied2= 0;
        while((copied1<n1)&&(copied2<n2)){
            if(array[first+copied1]<array[first+n1+copied]){
                answer[copied++]=array[first+(copied1++)];
            }
            else{
                answer[copied++]=array[first+n1+(copied2++)];
            }
        }
        while(copied1<n1){
            answer[copied++]=array[first+(copied1++)];
        }
        for(int i=0; i<copied; i++){
            array[first+i] = answer[i];
        }
    }
}
