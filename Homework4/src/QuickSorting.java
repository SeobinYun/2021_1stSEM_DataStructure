public class QuickSorting {
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
        System.out.print("배열생성 : "); // 랜덤으로 생성한 배열 출력
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

        quickSort(array, 0, array.length); // quickSort method 실행

        System.out.print("\n최종배열출력 : ");
        for (int i = 0; i < array.length; i++) { // Quicksort method 실행 후 최종배열 출력
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    private static void quickSort(int[] data, int first, int n) {
        if (n > 1) { // 우선 quickSort를 진행하려면 배열의 길이가 1 이상이어야 하므로 n이 1이상일 때, quickSort 진행
            int pivotIndex = partition(data, first, n); // pivot의 index를 리턴해주는 partition method 진행
            int n1 = pivotIndex - first;
            int n2 = n - n1 - 1;

            quickSort(data, first, n1); // 왼쪽에 대한 quickSort
            quickSort(data, pivotIndex + 1, n2); // 오른쪽에 대한 quickSort
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private static int partition(int[] data, int first, int n) {
        int pivot = data[first];
        int tooBigIndex = first + 1;
        int tooSmallIndex = first + n - 1;
        while (tooBigIndex <= tooSmallIndex) {
            while (tooBigIndex < first + n && data[tooBigIndex] <= pivot) {
                tooBigIndex++;
            }
            while (data[tooSmallIndex] > pivot) {
                tooSmallIndex--;
            }

            if (tooBigIndex < tooSmallIndex) {
                swap(data, tooBigIndex, tooSmallIndex);

                System.out.print("\npartition후 : ");
                for (int i = 0; i < data.length; i++) {
                    System.out.print(data[i] + "\t");
                }
            }
        }

        data[first] = data[tooSmallIndex];
        data[tooSmallIndex] = pivot;
        System.out.print("\ncross 후\t: ");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        return tooSmallIndex;
    }
}
