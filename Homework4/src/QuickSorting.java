public class QuickSorting {
    public static void main(String[] args){
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

        quickSort(array, 0, array.length-1);

        System.out.print("\n최종배열출력 : ");
        for(int i=0; i<array.length; i++){ // Quicksorting method 실행 후 최종배열 출력
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
    private static void quickSort(int[] data, int first, int n){

        if(n>1){
            int pivotIndex = partition(data, first, n);
            int n1=pivotIndex - first;
            int n2=n-n1-1;

            quickSort(data, first, n1);
            quickSort(data, pivotIndex+1, n2);


        }
    }

    private static int partition(int[] data, int left, int right){
        int pivot = data[left];
        int lo=left;
        int hi=right;

        while(lo<hi){
            while(data[hi]>pivot && lo<hi){
                hi--;
            }
            swap(data, lo, hi);
        }

        swap(data, left, lo);

        return lo;
    }

    private static void swap(int[] data, int i, int j){
        int tmp = data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
//
//    private static int partition(int[] data, int first, int n){
//        int pivot = data[first];
//        int tooBigIndex = first+1;
//        int tooSmallIndex= first+n-1;
//
//
//    }

//    private static void quickSort(int[] array, int left, int right){
//        if(left>=right){
//            return;
//        }
//        int pivot = partition(array, left, right);
//        System.out.print("\n1단계 : ");
//        for(int i=0; i<array.length; i++){ // selectionSorting method 실행 후 최종배열 출력
//            System.out.print(array[i] + "\t");
//        }
//
//        quickSort(array, left, pivot-1);
//        quickSort(array, pivot+1, right);
//
//
//    }
//
//    private static int partition(int[] array, int left, int right){
//        int a= left;
//        int b= right;
//        int pivot = array[left];
//
//        while(a<b){
//            while(array[b]>pivot && a<b){
//                b--;
//            }
//
//            while(array[a]<=pivot&&a<b){
//                a++;
//            }
//
//            swap(array, a,b);
//        }
//
//        swap(array, left,a);
//
//        return a;
//    }
//
//    private static void swap(int[] array, int a, int b){
//        int tmp = array[a];
//        array[a]=array[b];
//        array[b]=tmp;
//    }

}
