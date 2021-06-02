public class SelectionSorting {
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

        SelectionSort(array);
    }
    private static void swap(int[] array, int min, int i){
        int tmp = array[min];
        array[min]=array[i];
        array[i]=tmp;

        for(int k=0; k<array.length; k++){
            System.out.print(array[k]+"\t");
        }
        System.out.println();
    }


    private static void SelectionSort(int[] array){
        int min;
        int index=1;
        for(int i=0; i<array.length; i++){
            min=i;
            for(int j=i+1; j<array.length; j++){
                if(array[j]<array[min]){
                    min=j;
                }
            }
            System.out.print(index+"단계 : ");
            swap(array, min, i);
            index++;
        }
    }
}
