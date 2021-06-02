public class SelectionSorting {
    public static void main(String[] args) {
        int[] array = new int[32];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 32); // 0~31까지 배정받아야 하므로 32(최대값-최소값+1 = 31-0+1)로 설정
            for (int j = 0; j < i; j++) { // 중복값 확인: 배열 index j부터, j가 i전일 때까지 비교 반복
                if (array[j] == array[i]) { // index j방의 값과 i방의 값이 값다면
                    i--; // index i 위치의 값을 다시 할당받아야 하기 때문에 i--를 해주고 비교반복문 탈출
                    break;
                }
            }
        }

        System.out.print("배열생성 : "); // 배열 생성 확인
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

        selectionSort(array); //selectionSort method 호출

        System.out.print("\n최종배열출력 : ");
        for(int i=0; i<array.length; i++){ // selectionSorting method 실행 후 최종배열 출력
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    private static void swap(int[] array, int min, int i){ // 두 인덱스 값의 위치를 바꾸어주는 swap method
        int tmp = array[min];
        array[min]=array[i];
        array[i]=tmp;

        for(int k=0; k<array.length; k++){ // swap후 배열 상태 출력
            System.out.print(array[k]+"\t");
        }
        System.out.println();
    }


    private static void selectionSort(int[] array){ // selectionSort method
        int min; // 최소값을 저장해줄 변수
        int index=1; // 몇단계인지 출력할 index 변수
        for(int i=0; i<array.length; i++){
            min=i; // 우선 min값을 i값으로 설정
            for(int j=i+1; j<array.length; j++){ // i+1(j)부터 index i 위치보다 작은 수가 있는 index 탐색
                if(array[j]<array[min]){ // index j 위치의 값이 index min 위치의 값보다 작으면
                    min=j; // min값을 j로 설정
                }
            }
            System.out.print(index+"단계 : ");
            swap(array, min, i); // 그후 swap 함수를 출력해준 뒤 index를 ++해줌
            index++;
        }
    }
}
