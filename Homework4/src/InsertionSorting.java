public class InsertionSorting {
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
        for(int i=0; i<array.length; i++) { // 랜덤으로 생성한 배열 출력
            System.out.print(array[i] + "\t");
        }

        insertionSorting(array); // insertionSorting method 실행

        System.out.print("\n최종배열출력 : ");
        for(int i=0; i<array.length; i++){ // insertionSorting method 실행 후 최종배열 출력
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    private static void insertionSorting(int[] array){ // insertionSorting Method
        int index = 1; // 몇단계인지 알려줄 index 변수
        for(int i=0; i< array.length; i++){
            int target = array[i]; // array[i]를 기준으로 삼음
            int j=i-1; // 비교할 대상
            while(j>=0 && target<array[j]){ // array[j]에 있는 값이 target보다 크다면 array[j]값을 array[j+1]로 밀어줌
                array[j+1]=array[j];
                j--;
            }
            array[j+1] = target; // 그리고 array[j+1]을 target으로 설정해줌

            System.out.print("\n"+index +"단계 : "); // 각 단계마다 배열의 상태를 확인해줌
            for(int k=0; k<array.length; k++) {
                System.out.print(array[k]+"\t");
            }
            index++;
        }
    }
}
